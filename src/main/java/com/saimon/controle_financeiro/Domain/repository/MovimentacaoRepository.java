package com.saimon.controle_financeiro.Domain.repository;

import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findAllByUsuarioEmail(String email);
}
