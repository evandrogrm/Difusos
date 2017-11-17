package com.difusos.entity;

public class Variaveis {
	
	private String nome, universalIni, universalFim, isObjetivo;
		
	public Variaveis(String nome, String universalIni, String universalFim, String isObjetivo) {
		super();
		this.nome = nome;
		this.universalIni = universalIni;
		this.universalFim = universalFim;
		this.isObjetivo = isObjetivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUniversalIni() {
		return universalIni;
	}

	public void setUniversalIni(String universalIni) {
		this.universalIni = universalIni;
	}

	public String getUniversalFim() {
		return universalFim;
	}

	public void setUniversalFim(String universalFim) {
		this.universalFim = universalFim;
	}

	public String getIsObjetivo() {
		return isObjetivo;
	}

	public void setIsObjetivo(String isObjetivo) {
		this.isObjetivo = isObjetivo;
	}
	
}
