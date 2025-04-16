package com.saimon.controle_financeiro.Domain.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoMovimentacao {
    ENTRADA, SAIDA;

    @JsonCreator
    public static TipoMovimentacao fromString(String value) {
        return TipoMovimentacao.valueOf(value.toUpperCase());
    }
}
