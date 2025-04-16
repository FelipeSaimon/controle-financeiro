package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.DTO.LoginDTO;
import com.saimon.controle_financeiro.DTO.SessaoDTO;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.exceptions.CamposObrigatorios;
import com.saimon.controle_financeiro.exceptions.CredenciaisInvalidas;
import com.saimon.controle_financeiro.infra.security.JTW.JWTCreator;
import com.saimon.controle_financeiro.infra.security.JTW.JWTObject;
import com.saimon.controle_financeiro.infra.security.SecurityConfigurations;
import com.saimon.controle_financeiro.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

// SERIA O MIDDLEWARE DE AUTENTICAÇÃO?
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoController(PasswordEncoder passwordEncoder, SecurityConfigurations securityConfigurations, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public SessaoDTO logar(@RequestBody @Valid LoginDTO login, HttpServletResponse httpServletResponse) {
        if (login.getEmail() == null || login.getEmail().isBlank() ||
                login.getSenha() == null || login.getSenha().isBlank()) {
            throw new CamposObrigatorios();
        }

        Usuario usuario = usuarioRepository.findByEmail(login.getEmail())
                .orElseThrow(CredenciaisInvalidas::new);

        boolean passwordOk =  passwordEncoder.matches(login.getSenha(), usuario.getSenha());
        if (!passwordOk) {
            throw new CredenciaisInvalidas();
        }

        SessaoDTO sessao = new SessaoDTO();
        sessao.setLogin(usuario.getEmail());

        // Criando o objeto de token JWT
        JWTObject jwtObject = new JWTObject();
        jwtObject.setUsuario(usuario.getEmail()); // <--- isso estava faltando
        jwtObject.setDataDeCriacao(new Date(System.currentTimeMillis()));
        jwtObject.setDataDeExpiracao(new Date(System.currentTimeMillis() + SecurityConfigurations.getEXPIRACAO()));
        jwtObject.setRoles(usuario.getRole());

        sessao.setToken(JWTCreator.create(SecurityConfigurations.PREFIXO, SecurityConfigurations.CHAVE, jwtObject));

        // Criando o reflesh token
        JWTObject refleshObject = new JWTObject();
        refleshObject.setUsuario(usuario.getEmail());
        refleshObject.setDataDeCriacao(new Date(System.currentTimeMillis()));
        refleshObject.setDataDeExpiracao(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 1000));
        refleshObject.setRoles(usuario.getRole());
        
        String refleshToken = JWTCreator.create(SecurityConfigurations.PREFIXO, SecurityConfigurations.CHAVE, refleshObject);

        // Criando objeto de cookie para reflesh token
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("refleshToken", refleshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/refresh");
        cookie.setMaxAge(7 * 60 * 60);
        httpServletResponse.addCookie(cookie);

        return sessao;
    }

    // Criando o endpoint de reflesh do token
    @PostMapping("/refresh")
    public SessaoDTO refreshToken(HttpServletRequest httpServletRequest){
        String token = null;

        if (httpServletRequest.getCookies() != null){
            for (jakarta.servlet.http.Cookie cookie : httpServletRequest.getCookies()){
                if (cookie.getName().equals("refreshToken")){
                    token = cookie.getValue();
                }
            }
        }

        if (token == null){
            throw new RuntimeException("Refresh token não encontrado");
        }

        JWTObject jwtObject = JWTCreator.createJWTObject(SecurityConfigurations.CHAVE, token);
        if (jwtObject.getDataDeExpiracao().before(new Date())){
            throw new RuntimeException("refresh token expirado");
        }

        // Criar novo access token
        JWTObject novoAccessObject = new JWTObject();
        novoAccessObject.setUsuario(jwtObject.getUsuario());
        novoAccessObject.setDataDeCriacao(new Date());
        novoAccessObject.setDataDeExpiracao(new Date(System.currentTimeMillis() + SecurityConfigurations.getEXPIRACAO())); // 15min
        novoAccessObject.setRoles(jwtObject.getRoles());

        SessaoDTO sessaoRefresh = new SessaoDTO();
        sessaoRefresh.setLogin(jwtObject.getUsuario());
        sessaoRefresh.setToken(JWTCreator.create("",SecurityConfigurations.CHAVE, novoAccessObject));

        return sessaoRefresh;
    }
}
