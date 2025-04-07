package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.dto.LoginDTO;
import com.saimon.controle_financeiro.Domain.dto.SessaoDTO;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.infra.security.JTW.JWTCreator;
import com.saimon.controle_financeiro.infra.security.JTW.JWTObject;
import com.saimon.controle_financeiro.infra.security.SecurityConfigurations;
import com.saimon.controle_financeiro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AutenticacaoController {

    private final PasswordEncoder passwordEncoder;
    private final SecurityConfigurations securityConfigurations;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;


    public AutenticacaoController(PasswordEncoder passwordEncoder, SecurityConfigurations securityConfigurations, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.securityConfigurations = securityConfigurations;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios/login")
    public SessaoDTO logar(@RequestBody @Valid LoginDTO login) {

//        if (login.getEmail().isBlank() || login.getSenha().isBlank()) {
//            throw new RuntimeException("Email e senha são obrigatórios");
//        }

        if (login.getEmail() == null || login.getEmail().isBlank() ||
                login.getSenha() == null || login.getSenha().isBlank()) {
            throw new RuntimeException("Email e senha são obrigatórios");
        }

        Usuario usuario = usuarioRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + login.getEmail()));

//        if(usuario != null){
//            boolean senhaVerificada = passwordEncoder.matches(login.getSenha(), usuario.getSenha());
//
//            System.out.println(senhaVerificada);
//            if (!senhaVerificada){
//                throw new RuntimeException("Senha inválida, verifique sua ultima senha! " + login.getEmail());
//            }


            SessaoDTO sessao = new SessaoDTO();
        sessao.setLogin(usuario.getEmail());


        // Criando o objeto de token JWT
        JWTObject jwtObject = new JWTObject();

        jwtObject.setUsuario(usuario.getEmail()); // <--- isso estava faltando
        jwtObject.setDataDeCriacao(new Date(System.currentTimeMillis()));
        jwtObject.setDataDeExpiracao(new Date(System.currentTimeMillis() + SecurityConfigurations.getEXPIRACAO()));
        jwtObject.setRoles(usuario.getRole());

        sessao.setToken(JWTCreator.create(SecurityConfigurations.PREFIXO, SecurityConfigurations.CHAVE, jwtObject));
            return sessao;

//        } else {
//            throw new RuntimeException("Erro ao logar!");
//        }
    }
}
