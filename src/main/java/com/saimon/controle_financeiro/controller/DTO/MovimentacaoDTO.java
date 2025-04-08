package com.saimon.controle_financeiro.controller.DTO;

import java.time.LocalDateTime;

public class MovimentacaoDTO {
    private String email;
    private Double valorMovimentacao;

    public MovimentacaoDTO(String email, double valorMovimentacao, LocalDateTime dataDeCriacao) {
        this.valorMovimentacao = valorMovimentacao;
        this.email = email;
    }

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getValorMovimentacao() { return valorMovimentacao; }
    public void setValorMovimentacao(Double valorMovimentacao) { this.valorMovimentacao = valorMovimentacao; }
}
