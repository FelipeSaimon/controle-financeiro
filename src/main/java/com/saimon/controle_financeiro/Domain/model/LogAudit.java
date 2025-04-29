package com.saimon.controle_financeiro.Domain.model;

import com.saimon.controle_financeiro.Domain.Enum.LogTipoEvento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_audit")
public class LogAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	private String descricao;
	private LocalDateTime dataHora;

	@Enumerated(EnumType.STRING)
	private LogTipoEvento tipoEvento;

	public LogAudit() {
	}

	public LogAudit(Long id, Usuario usuario, String descricao, LocalDateTime dataHora, LogTipoEvento tipoEvento) {
		this.id = id;
		this.usuario = usuario;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.tipoEvento = tipoEvento;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogTipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(LogTipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
