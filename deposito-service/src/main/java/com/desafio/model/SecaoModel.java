package com.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="secao")
@Entity
public class SecaoModel {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="id_tipo_bebida")
	private Integer idTipoBebida;
	
	@Column(name="volume")
	private Double volume;
	
	@Transient
	private TipoBebidaModel tipoBebida;
	
	public SecaoModel() {	}

	public SecaoModel(String nome, Double volume) {	
		this.nome = nome;
		this.volume = volume;
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

	public Integer getIdTipoBebida() {
		return idTipoBebida;
	}

	public void setIdTipoBebida(Integer idTipoBebida) {
		this.idTipoBebida = idTipoBebida;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public TipoBebidaModel getTipoBebida() {
		return tipoBebida;
	}

	public void setTipoBebida(TipoBebidaModel tipoBebida) {
		this.tipoBebida = tipoBebida;
	}
	
	
}
