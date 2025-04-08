package com.saimon.controle_financeiro.infra.security.JTW;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class JWTObject {
    private String usuario;
    private Date dataDeCriacao;
    private Date dataDeExpiracao;
    private List<String> roles;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Date getDataDeExpiracao() {
        return dataDeExpiracao;
    }

    public void setDataDeExpiracao(Date dataDeExpiracao) {
        this.dataDeExpiracao = dataDeExpiracao;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(Object o){
        this.roles = Collections.singletonList(String.valueOf(roles));
    }
}
