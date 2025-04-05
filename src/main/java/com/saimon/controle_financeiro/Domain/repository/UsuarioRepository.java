package com.saimon.controle_financeiro.Domain.repository;

import com.saimon.controle_financeiro.Domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByEmail(String email);
}
