package com.saimon.controle_financeiro.Domain.repository;

import com.saimon.controle_financeiro.Domain.model.LogAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAuditRepository extends JpaRepository<LogAudit, Long> {
}
