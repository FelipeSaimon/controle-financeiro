package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import org.springframework.stereotype.Service;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;

import java.util.NoSuchElementException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario) throws Exception{
        if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null){
            throw new IllegalArgumentException("Campos obrigatórios!");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
}
