package com.desafio.dto;

public class EstoqueSaidaDTO {
	private Integer idSecao;	
	private Double volumeDeSaida;
	private String responsavel;
	
	public Integer getIdSecao() {
		return idSecao;
	}
	public void setIdSecao(Integer idSecao) {
		this.idSecao = idSecao;
	}
	public Double getVolumeDeSaida() {
		return volumeDeSaida;
	}
	public void setVolumeDeSaida(Double volumeDeSaida) {
		this.volumeDeSaida = volumeDeSaida;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}	
}
