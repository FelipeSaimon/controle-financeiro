package com.saimon.controle_financeiro.Domain.model;

import com.saimon.controle_financeiro.Domain.Enum.UserRole;
import com.saimon.controle_financeiro.Domain.Enum.UsuarioDelete;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE usuario SET usuario_delete = 'INATIVO' WHERE id = ?")
@Where(clause = "usuario_delete = 'ATIVO'")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 60, unique = true, nullable = false)
    private String email;

    @Column(length = 110, nullable = false)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Movimentacao> movimentacoes;

    @Column(name = "usuario_delete")
    @Enumerated(EnumType.STRING)
    @NonNull
    private UsuarioDelete usuarioDeleteEnum = UsuarioDelete.ATIVO;

    @Column(name = "role_id")
    private UserRole role = UserRole.USER_ROLE;

    public Usuario(){}

    public Usuario(String email){
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public UsuarioDelete getUsuarioDeleteEnum() {
        return usuarioDeleteEnum;
    }

    public void setUsuarioDeleteEnum(UsuarioDelete usuarioDeleteEnum) {
        this.usuarioDeleteEnum = usuarioDeleteEnum;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    // IMPLEMENTACAO DE UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
