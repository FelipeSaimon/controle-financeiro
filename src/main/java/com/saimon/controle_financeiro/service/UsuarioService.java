package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.model.Usuario;
import org.springframework.stereotype.Service;
import com.saimon.controle_financeiro.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario) throws Exception{
        if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null){
            throw new IllegalArgumentException("Usuario já cadastrado!");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow();
    }
}
