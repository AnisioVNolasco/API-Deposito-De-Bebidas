package com.desafio.dto;

import java.util.ArrayList;
import java.util.List;

public class DisponibilidadeEstoqueDTO {
	
	private Double disponibilidadeTotal;
	private List<DisponibilidadeSecaoDTO> secoes;
	
	public DisponibilidadeEstoqueDTO() {	
		this.disponibilidadeTotal = 0.0;
		this.secoes = new ArrayList<>();
	}
	
	public Double getDisponibilidadeTotal() {
		return disponibilidadeTotal;
	}
	public void setDisponibilidadeTotal(Double disponibilidadeTotal) {
		this.disponibilidadeTotal = disponibilidadeTotal;
	}
	public void addDisponibilidadeTotal(Double disponibilidadeTotal) {
		this.disponibilidadeTotal += disponibilidadeTotal;
	}
	public List<DisponibilidadeSecaoDTO> getSecoes() {
		return secoes;
	}
	public void setSecoes(List<DisponibilidadeSecaoDTO> secoes) {
		this.secoes = secoes;
	}
	public void addSecao(DisponibilidadeSecaoDTO secao) {
		this.secoes.add(secao);
	}
	
	

}
