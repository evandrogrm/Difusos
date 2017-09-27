package com.difusos.entity;

public class Termos {
	
	private Variaveis variavel;
	private float nucleoIni,nucleoFim, suporteIni, suporteFim;
	
	public Termos(Variaveis variavel, float nucleoIni, float nucleoFim, float suporteIni, float suporteFim) {
		super();
		this.variavel = variavel;
		this.nucleoIni = nucleoIni;
		this.nucleoFim = nucleoFim;
		this.suporteIni = suporteIni;
		this.suporteFim = suporteFim;
	}
	public float getNucleoIni() {
		return nucleoIni;
	}
	public void setNucleoIni(float nucleoIni) {
		this.nucleoIni = nucleoIni;
	}
	public float getNucleoFim() {
		return nucleoFim;
	}
	public void setNucleoFim(float nucleoFim) {
		this.nucleoFim = nucleoFim;
	}
	public float getSuporteIni() {
		return suporteIni;
	}
	public void setSuporteIni(float suporteIni) {
		this.suporteIni = suporteIni;
	}
	public float getSuporteFim() {
		return suporteFim;
	}
	public void setSuporteFim(float suporteFim) {
		this.suporteFim = suporteFim;
	}
	public Variaveis getVariavel() {
		return variavel;
	}
	public void setVariavel(Variaveis variavel) {
		this.variavel = variavel;
	}
	
	
}
