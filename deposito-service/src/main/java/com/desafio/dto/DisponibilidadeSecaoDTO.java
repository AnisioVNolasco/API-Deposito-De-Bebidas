package com.desafio.dto;

public class DisponibilidadeSecaoDTO {
	
	private Integer idSecao;
	private String nomeSecao;
	private Double volume;
		
	public DisponibilidadeSecaoDTO(Integer idSecao, String nomeSecao, Double volume) {
		this.idSecao = idSecao;
		this.nomeSecao = nomeSecao;
		this.volume = volume;
	}
	public Integer getIdSecao() {
		return idSecao;
	}
	public void setIdSecao(Integer idSecao) {
		this.idSecao = idSecao;
	}
	public String getNomeSecao() {
		return nomeSecao;
	}
	public void setNomeSecao(String nomeSecao) {
		this.nomeSecao = nomeSecao;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
}
