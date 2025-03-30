package com.saimon.controle_financeiro.repository;

import com.saimon.controle_financeiro.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
