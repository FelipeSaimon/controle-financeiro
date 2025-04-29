package com.saimon.controle_financeiro.controller;

import com.saimon.controle_financeiro.Domain.model.LogAudit;
import com.saimon.controle_financeiro.Domain.repository.LogAuditRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class LogAuditoriaController {

	private final LogAuditRepository logAuditRepository;

	public LogAuditoriaController(LogAuditRepository logAuditRepository) {
		this.logAuditRepository = logAuditRepository;
	}

	 @GetMapping
	 public ResponseEntity<List<LogAudit>> getAllLogs() {
	     List<LogAudit> logs = logAuditRepository.findAll();
	     return ResponseEntity.ok(logs);
	 }

}
