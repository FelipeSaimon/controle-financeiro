package com.saimon.controle_financeiro.infra.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfigurations {

    public static String PREFIXO;
    public static String CHAVE;
    public static Long EXPIRACAO;

    public static Long getEXPIRACAO() {
        return EXPIRACAO;
    }

    public static void setEXPIRACAO(Long EXPIRACAO) {
        SecurityConfigurations.EXPIRACAO = EXPIRACAO;
    }

    public static void setPREFIXO(String PREFIXO) {
        SecurityConfigurations.PREFIXO = PREFIXO;
    }

    public static void setCHAVE(String CHAVE) {
        SecurityConfigurations.CHAVE = CHAVE;
    }
}
