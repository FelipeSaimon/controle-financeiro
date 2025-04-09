package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;
import com.saimon.controle_financeiro.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario save(Usuario usuario) throws Exception{
        if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null){
            throw new IllegalArgumentException("Campos obrigatórios!");
        }
        String senha = usuario.getSenha();

        // Criptogrando a senha antes de salvá-la no banco
        usuario.setSenha(encoder.encode(senha));
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
}
