package com.desafio.dto;

import javax.persistence.Column;

public class TipoBebidaDTO {
	
	private String nome;
	private Double capacidadeMaxima;
	
	public TipoBebidaDTO(String nome, Double capacidadeMaxima) {
		this.nome = nome;
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}

	public void setCapacidadeMaxima(Double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	
}
