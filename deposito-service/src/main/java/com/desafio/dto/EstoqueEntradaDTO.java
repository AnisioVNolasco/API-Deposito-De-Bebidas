package com.desafio.dto;

public class EstoqueEntradaDTO {
	
	private Integer idSecao;
	private Integer idTipoBebida;
	private Double volumeDeEntrada;
	private String responsavel;
	
	public Integer getIdSecao() {
		return idSecao;
	}
	public void setIdSecao(Integer idSecao) {
		this.idSecao = idSecao;
	}
	public Integer getIdTipoBebida() {
		return idTipoBebida;
	}
	public void setIdTipoBebida(Integer idTipoBebida) {
		this.idTipoBebida = idTipoBebida;
	}
	public Double getVolumeDeEntrada() {
		return volumeDeEntrada;
	}
	public void setVolumeDeEntrada(Double volumeDeEntrada) {
		this.volumeDeEntrada = volumeDeEntrada;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}	
}
