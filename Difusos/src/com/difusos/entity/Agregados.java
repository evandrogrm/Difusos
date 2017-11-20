package com.difusos.entity;

public class Agregados {
	
	private String termo;
	private float pertinencia;
	
	public Agregados(String termo, float pertinencia) {
		super();
		this.setTermo(termo);
		this.pertinencia = pertinencia;
	}


	public float getPertinencia() {
		return pertinencia;
	}

	public void setPertinencia(float pertinencia) {
		this.pertinencia = pertinencia;
	}


	public String getTermo() {
		return termo;
	}


	public void setTermo(String termo) {
		this.termo = termo;
	}

	
	
	
}
