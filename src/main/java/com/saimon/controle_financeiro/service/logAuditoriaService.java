package com.saimon.controle_financeiro.service;

import com.saimon.controle_financeiro.Domain.model.LogAudit;
import com.saimon.controle_financeiro.Domain.repository.LogAuditRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class logAuditoriaService {

	private final LogAuditRepository logAuditRepository;

	public logAuditoriaService(LogAuditRepository logAuditRepository) {
		this.logAuditRepository = logAuditRepository;
	}

	// Método para criar um log de auditoria
	public ResponseEntity<LogAudit> createLog(LogAudit logAudit) {
		LogAudit savedLog = logAuditRepository.save(logAudit);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLog);
	}

	// Exemplo de método para buscar todos os logs de auditoria
	 public ResponseEntity<List<LogAudit>> getAllLogs() {
	     List<LogAudit> logs = logAuditRepository.findAll();
	     return ResponseEntity.ok(logs);
	 }

}
