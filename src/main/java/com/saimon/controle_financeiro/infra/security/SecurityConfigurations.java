package com.saimon.controle_financeiro.infra.security;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConfigurationProperties(prefix = "security.config")
//public class SecurityConfigurations {
//
//    public  String PREFIXO;
//
//    public  String CHAVE;
//
//    public  Long EXPIRACAO = 1000L * 60 * 60 * 24;
//
//
//    public  Long getEXPIRACAO() {
//        return EXPIRACAO;
//    }
//
//    @Value("${security.config.expiracao}")
//    public  void setEXPIRACAO(Long EXPIRACAO) {
//        SecurityConfigurations.EXPIRACAO = EXPIRACAO;
//    }
//
//    @Value("${security.config.prefixo}")
//    public  void setPREFIXO(String PREFIXO) {
//        SecurityConfigurations.PREFIXO = PREFIXO;
//    }
//
//    @Value("${security.config.chave}")
//    public void setCHAVE(String CHAVE) {
//        SecurityConfigurations.CHAVE = CHAVE;
//    }
//}

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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