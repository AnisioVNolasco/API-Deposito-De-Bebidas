package com.desafio.dto;

import java.util.ArrayList;
import java.util.List;

public class EspacoEstoqueDTO {
	
	private String mensagem;
	private Double espacoTotal;
	private List<EspacoSecaoDTO> espacos;
		
	public EspacoEstoqueDTO() {	
		this.espacoTotal = 0.0;
		this.espacos = new ArrayList<>();
	}	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Double getEspacoTotal() {
		return espacoTotal;
	}
	public void setEspacoTotal(Double espacoTotal) {
		this.espacoTotal = espacoTotal;
	}
	public void addEspacoTotal(Double espacoTotal) {
		this.espacoTotal += espacoTotal;
	}
	public List<EspacoSecaoDTO> getEspacos() {
		return espacos;
	}
	public void setEspacos(List<EspacoSecaoDTO> espacos) {
		this.espacos = espacos;
	}
	public void addEspaco(EspacoSecaoDTO espaco) {
		this.espacos.add(espaco);
	}
	
}
