package com.difusos.entity;

import java.util.List;

public class Regras {
	
	private List<Condicao> c;
	private Consequencia cq;

	public Regras() {
		super();
	}
	public Regras(List<Condicao> c, Consequencia cq) {
		super();
		this.c = c;
		this.cq = cq;
	}
	
	public List<Condicao> getC() {
		return c;
	}
	public void setC(List<Condicao> c) {
		this.c = c;
	}
	public Consequencia getCq() {
		return cq;
	}
	public void setCq(Consequencia cq) {
		this.cq = cq;
	}
	
	
}
