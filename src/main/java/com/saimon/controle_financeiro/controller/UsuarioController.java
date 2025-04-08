package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // INJEÇÃO DE DEPENDENCIA VIA CONSTRUTOR
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @CrossOrigin(origins = { "http://localhost:5173" })
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuarioCriado) throws Exception {
        usuarioCriado = usuarioService.save(usuarioCriado);
        return ResponseEntity.ok().body(usuarioCriado);
    }

    @CrossOrigin(origins = { "http://localhost:5173" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        var usuario = usuarioService.findById(id);
        usuarioService.delete(usuario.getId());

        return ResponseEntity.noContent().build();
    }
}
