package com.saimon.controle_financeiro.DTO;

import java.math.BigDecimal;

public class ResumoValoresDTO {
	private BigDecimal totalEntradas;
	private BigDecimal totalSaidas;
	private BigDecimal total;

	public ResumoValoresDTO() {
	}

	public ResumoValoresDTO(BigDecimal totalEntradas, BigDecimal totalSaidas) {
		this.totalEntradas = totalEntradas;
		this.totalSaidas = totalSaidas;
	}

	public ResumoValoresDTO(BigDecimal totalEntradas, BigDecimal totalSaidas, BigDecimal total) {
		this.totalEntradas = totalEntradas;
		this.totalSaidas = totalSaidas;
		this.total = total;
	}

	public BigDecimal getTotalEntradas() {
		return totalEntradas;
	}

	public void setTotalEntradas(BigDecimal totalEntradas) {
		this.totalEntradas = totalEntradas;
	}

	public BigDecimal getTotalSaidas() {
		return totalSaidas;
	}

	public void setTotalSaidas(BigDecimal totalSaidas) {
		this.totalSaidas = totalSaidas;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
