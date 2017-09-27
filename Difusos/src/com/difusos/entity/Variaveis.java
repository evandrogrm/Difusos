package com.difusos.entity;

public class Variaveis {
	
	private String nome, universalIni, universalFim;
		
	public Variaveis(String nome, String universalIni, String universalFim) {
		super();
		this.nome = nome;
		this.universalIni = universalIni;
		this.universalFim = universalFim;
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
	
}
