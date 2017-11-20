package com.difusos.entity;

public class Condicao {
	
	private String variavelCondicao;
	private String termoCondicao;
	private String tipoAgregacao;
	private String concentracao;
	
	public Condicao(String variavelCondicao, String concentracao, String termoCondicao, String tipoAgregacao) {
		super();
		this.variavelCondicao = variavelCondicao;
		this.termoCondicao = termoCondicao;
		this.tipoAgregacao = tipoAgregacao;
		this.concentracao = concentracao;
	}

	public Condicao() {
	}

	public String getVariavelCondicao() {
		return variavelCondicao;
	}

	public void setVariavelCondicao(String variavelCondicao) {
		this.variavelCondicao = variavelCondicao;
	}

	public String getTermoCondicao() {
		return termoCondicao;
	}

	public void setTermoCondicao(String termoCondicao) {
		this.termoCondicao = termoCondicao;
	}

	public String getTipoAgregacao() {
		return tipoAgregacao;
	}

	public void setTipoAgregacao(String tipoAgregacao) {
		this.tipoAgregacao = tipoAgregacao;
	}

	public String getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}
	
	
	
	
}
