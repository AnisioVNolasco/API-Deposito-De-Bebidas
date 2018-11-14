package com.desafio.dto;

public class TotalBebidaDTO {	
	private String tipoBebida;
	private Double total;
	
	public TotalBebidaDTO(String tipoBebida) {	
		this.tipoBebida = tipoBebida;
		this.total = 0.0;
	}

	public String getTipoBebida() {
		return tipoBebida;
	}

	public void setTipoBebida(String tipoBebida) {
		this.tipoBebida = tipoBebida;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void addTotal(Double total) {
		this.total += total;
	}
		
}
