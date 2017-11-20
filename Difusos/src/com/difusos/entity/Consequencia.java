package com.difusos.entity;

public class Consequencia {
	
	private String variavelConsequencia;
	private String termoConsequencia;
	
	public Consequencia(String variavelConsequencia, String termoConsequencia) {
		super();
		this.variavelConsequencia = variavelConsequencia;
		this.termoConsequencia = termoConsequencia;
	}

	public Consequencia() {
		super();
	}

	public String getVariavelConsequencia() {
		return variavelConsequencia;
	}

	public void setVariavelConsequencia(String variavelConsequencia) {
		this.variavelConsequencia = variavelConsequencia;
	}

	public String getTermoConsequencia() {
		return termoConsequencia;
	}

	public void setTermoConsequencia(String termoConsequencia) {
		this.termoConsequencia = termoConsequencia;
	}
	
	
	
	
	
	
}
