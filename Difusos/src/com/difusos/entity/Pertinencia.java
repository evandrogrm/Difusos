package com.difusos.entity;

public class Pertinencia {
	String variavel;
	String termo;
	float pertinencia;
	
	public Pertinencia(String variavel, String termo, float pertinencia) {
		super();
		this.variavel = variavel;
		this.termo = termo;
		this.pertinencia = pertinencia;
	}

	public String getVariavel() {
		return variavel;
	}

	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}

	public String getTermo() {
		return termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}

	public float getPertinencia() {
		return pertinencia;
	}

	public void setPertinencia(float pertinencia) {
		this.pertinencia = pertinencia;
	}
	
}
