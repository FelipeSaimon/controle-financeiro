package com.saimon.controle_financeiro.audit;

import com.saimon.controle_financeiro.Domain.Enum.LogTipoEvento;
import com.saimon.controle_financeiro.Domain.model.LogAudit;
import com.saimon.controle_financeiro.Domain.model.Usuario;
import com.saimon.controle_financeiro.Domain.repository.LogAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class LoggerServiceImpl implements LoggerServiceInterface {
	private final LogAuditRepository logRepository;

	public LoggerServiceImpl(LogAuditRepository log) {
		this.logRepository = log;
	}


	// Implementation do LoggerServiceInterface
	@Override
	public void logEvento(Usuario usuario, LogTipoEvento evento, String descricao) {

		try {
			if (usuario == null) {
				throw new IllegalArgumentException("Usuario não pode ser nulo");
			}

			LogAudit logAudit = new LogAudit();

			logAudit.setUsuario(usuario);
			logAudit.setTipoEvento(evento);
			logAudit.setDescricao(descricao);
			logAudit.setDataHora(LocalDateTime.now());

			logRepository.save(logAudit);
		}catch (Exception e) {
			throw new RuntimeException("Erro ao registrar log: " + e.getMessage());
		}
	}
}