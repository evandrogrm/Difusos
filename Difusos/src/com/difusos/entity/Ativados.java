package com.difusos.entity;

public class Ativados {
	private String termo;
	private float pertinencia;
	public Ativados(String termo, float pertinencia) {
		super();
		this.termo = termo;
		this.pertinencia = pertinencia;
	}
	public Ativados() {
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