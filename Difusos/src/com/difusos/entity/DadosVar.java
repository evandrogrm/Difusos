package com.difusos.entity;

public class DadosVar {
	
	private Variaveis v;
	private float valor;
	
	public DadosVar(Variaveis v, float valor) {
		super();
		this.v = v;
		this.valor = valor;
	}

	public Variaveis getV() {
		return v;
	}

	public void setV(Variaveis v) {
		this.v = v;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}
