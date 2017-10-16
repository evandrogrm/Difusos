package com.difusos.bc;

import com.difusos.entity.Variaveis;

public class Resultado {
	
	private Variaveis variavelObjetivo;
	private String resultado;
	
	public Resultado(Variaveis variavelObjetivo, String resultado) {
		super();
		this.variavelObjetivo = variavelObjetivo;
		this.resultado = resultado;
	}
	
	public Resultado() {}
	
	public Variaveis getVariavelObjetivo() {
		return variavelObjetivo;
	}
	public void setVariavelObjetivo(Variaveis variavelObjetivo) {
		this.variavelObjetivo = variavelObjetivo;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
