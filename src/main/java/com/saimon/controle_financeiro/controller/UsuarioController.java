package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.model.Usuario;
import com.saimon.controle_financeiro.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // INJEÇÃO DE DEPENDENCIA VIA CONSTRUTOR
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuarioCriado) throws Exception {

        usuarioCriado = usuarioService.save(usuarioCriado);

        return ResponseEntity.ok().body(usuarioCriado);
    }

}
