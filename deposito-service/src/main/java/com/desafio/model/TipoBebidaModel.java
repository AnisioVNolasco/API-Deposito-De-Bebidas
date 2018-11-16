package com.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tipo_bebida")
@Entity
public class TipoBebidaModel {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
 
	@Column(name="nome")
	private String nome;
	
	@Column(name="capacidade_maxima")
	private Double capacidadeMaxima;
	
	public TipoBebidaModel() {	}

	public TipoBebidaModel(String nome, Double capacidadeMaxima) {
		this.nome = nome;
		this.capacidadeMaxima = capacidadeMaxima;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
