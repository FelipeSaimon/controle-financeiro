package com.saimon.controle_financeiro.audit;

import com.saimon.controle_financeiro.Domain.Enum.LogTipoEvento;
import com.saimon.controle_financeiro.Domain.model.Usuario;

public interface LoggerServiceInterface {
	void logEvento(Usuario usuario, LogTipoEvento evento, String descricao);
}
