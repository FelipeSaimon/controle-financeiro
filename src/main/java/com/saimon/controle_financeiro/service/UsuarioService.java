package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.saimon.controle_financeiro.Domain.repository.UsuarioRepository;

import java.util.NoSuchElementException;

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
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    // Criptografia da senha ao criar um usuario.
    public void createUser(Usuario user){
        String senha = user.getSenha();

        // Criptogrando a senha antes de salvá-la no banco
        user.setSenha(encoder.encode(senha));
        usuarioRepository.save(user);
    }
}
