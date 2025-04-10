package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    // INJEÇÃO DE DEPENDENCIA VIA CONSTRUTOR
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    // CADASTRAR USUARIO
    @CrossOrigin(origins = { "http://localhost:5173" })
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuarioCriado) throws Exception {
        usuarioCriado = usuarioService.save(usuarioCriado);
        return ResponseEntity.ok().body(usuarioCriado);
    }

    /*
    * DELETAR USUARIO
    *
    * 1 - Pega o email do token de autenticação, pode ser o padrão do spring security
    * 2 - Busca o usuario cadastrado no banco com o email autenticado
    * 3 - Verifica se é nulo
    * 4 - Deleta esse usuario pelo id
    * 5 - Retorna status 204.
    */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarUsuarioLogado(Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioService.findByEmail(email);

        if (usuario == null) {
            // Vale a pena exceção personalizada aqui?
            return ResponseEntity.notFound().build();
        }

        usuarioService.delete(usuario.getId());
        return ResponseEntity.noContent().build();
    }

}
