package com.desafio.dto;

public class EspacoSecaoDTO {
	
	private Integer idSecao;
	private String nomeSecao;
	private Double espaco;
	
	
	
	public EspacoSecaoDTO(Integer idSecao, String nomeSecao, Double espaco) {
		this.idSecao = idSecao;
		this.nomeSecao = nomeSecao;
		this.espaco = espaco;
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
	public Double getEspaco() {
		return espaco;
	}
	public void setEspaco(Double espaco) {
		this.espaco = espaco;
	}
}
