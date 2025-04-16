package com.saimon.controle_financeiro.Domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saimon.controle_financeiro.Domain.Enum.TipoMovimentacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor_movimentacao;
    private LocalDateTime dataDeCriacao;
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorMovimentacao() {
        return this.valor_movimentacao;
    }

    public void setValorMovimentacao(BigDecimal valorMovimentacao) {
        this.valor_movimentacao = valorMovimentacao;
    }
//
//    public BigDecimal getValor_movimentacao() {
//        return valor_movimentacao;
//    }
//
//    public void setValor_movimentacao(BigDecimal valor_movimentacao) {
//        this.valor_movimentacao = valor_movimentacao;
//    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}
