package com.saimon.controle_financeiro.controller.DTO;

import java.time.LocalDateTime;

public class MovimentacaoDTO {
    private Long usuario_id;
    private Double valorMovimentacao;

    public MovimentacaoDTO(Long id, double valorMovimentacao, LocalDateTime dataDeCriacao) {

    }

    // Getters e Setters
    public Long getUsuario_id() { return usuario_id; }
    public void setUsuario_id(Long usuario_id) { this.usuario_id = usuario_id; }

    public Double getValorMovimentacao() { return valorMovimentacao; }
    public void setValorMovimentacao(Double valorMovimentacao) { this.valorMovimentacao = valorMovimentacao; }
}
