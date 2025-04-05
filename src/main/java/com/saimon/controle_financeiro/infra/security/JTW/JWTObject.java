package com.saimon.controle_financeiro.infra.security.JTW;

import com.saimon.controle_financeiro.Domain.Enum.UserRole;

import java.util.*;

public class JWTObject {
    private String usuario;
    private Date dataDeCriacao;
    private Date dataDeExpiracao;
    private UserRole role;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = UserRole.USER_ROLE;
    }
}
