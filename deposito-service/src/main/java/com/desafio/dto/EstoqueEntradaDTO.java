package com.desafio.dto;

public class EstoqueEntradaDTO {
	
	private Integer idSecao;
	private Integer idTipoBebida;
	private Double volumeDeEntrada;
	
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
}
