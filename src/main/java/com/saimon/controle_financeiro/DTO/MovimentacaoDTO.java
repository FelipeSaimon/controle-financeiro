package com.saimon.controle_financeiro.DTO;

import com.saimon.controle_financeiro.Domain.Enum.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentacaoDTO {
    private String email;

    @NotNull
    @NotBlank
    private BigDecimal valorMovimentacao;

    @NotNull
    @NotBlank
    private TipoMovimentacao tipo;

    public MovimentacaoDTO() {
    }

    public MovimentacaoDTO(String email, BigDecimal valorMovimentacao, LocalDateTime dataDeCriacao, TipoMovimentacao tipo) {
        this.valorMovimentacao = valorMovimentacao;
        this.email = email;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getValorMovimentacao() { return valorMovimentacao; }
    public void setValorMovimentacao(BigDecimal valorMovimentacao) { this.valorMovimentacao = valorMovimentacao; }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
}
