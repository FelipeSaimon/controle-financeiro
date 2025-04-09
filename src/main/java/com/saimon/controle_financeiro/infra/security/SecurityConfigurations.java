package com.saimon.controle_financeiro.infra.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// ESTRUTURA BASE PARA UM TOKEN
@Configuration
public class SecurityConfigurations {
    public static String PREFIXO;
    public static String CHAVE;
    public static Long EXPIRACAO = 1000L * 60 * 60 * 24;

    @Value("${security.config.prefixo}")
    private String prefixo;

    @Value("${security.config.chave}")
    private String chave;

    @Value("${security.config.expiracao}")
    private Long expiracao;

    public static Long getEXPIRACAO() {
        return EXPIRACAO;
    }

    @PostConstruct
    public void init() {
        PREFIXO = prefixo;
        CHAVE = chave;
        EXPIRACAO = expiracao;
    }
}