package com.desafio.util;

public enum Evento {
	ENTRADA("entrada"),
	SAIDA("saida");
	
	private String evento;
	
	Evento(String evento){
		this.evento = evento;
	}
	
	public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
}
