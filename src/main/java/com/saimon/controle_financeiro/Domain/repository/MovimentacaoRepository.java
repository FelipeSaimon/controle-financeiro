package com.saimon.controle_financeiro.Domain.repository;

import com.saimon.controle_financeiro.Domain.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
