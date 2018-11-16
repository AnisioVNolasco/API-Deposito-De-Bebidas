package com.desafio.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="historico")
@Entity
public class HistoricoModel {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="evento")
	private String evento;
	
	@Column(name="responsavel")
	private String responsavel;
	
	@Column(name="id_secao")
	private Integer idSecao;
	
	@Column(name="volume")
	private Double volume;
	
	@Column(name="id_tipo_bebida")
	private Integer idTipoBebida;
	
	@Column(name="dataHora")
	private LocalDateTime dataHora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

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

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
		
}
