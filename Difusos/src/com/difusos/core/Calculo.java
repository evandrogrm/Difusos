package com.difusos.core;

import com.difusos.entity.Termos;

public class Calculo {
	
	public float muito(float x){
		return (float) Math.pow(x, 2);
	}
	
	public float algo(float x){
		return (float) Math.pow(x, 0.5);
	}
	
	public float deFato(float x) {
		if (x <= 0.5) {
			x = (float) Math.pow(x, 2);
			x = 2 * x;
		} else {
			x = 1 - x;
			x = (float) Math.pow(x, 2);
			x = 2 * x;
			x = 1 - x;
		}
		return x;
	}
	
	public float muitoMuito(float x){
		return (float) Math.pow(x, x);
	}
	
	public float min(float x, float y) {
		if (x <= y) {
			return x;
		} else {
			return y;
		}
	}
	
	public float max(float x, float y) {
		if (x >= y) {
			return x;
		} else {
			return y;
		}
	}
	
	public float calculaPertinencia(Termos t, float x) {
		if (x >= t.getNucleoIni() && x <= t.getNucleoFim()) {
			return 1;
		} else if (x > t.getSuporteFim() || x < t.getSuporteIni()) {
			return 0;
		} else if (x >= t.getSuporteIni() && x <= t.getNucleoIni()) {
			return (x - t.getSuporteIni()) / (t.getNucleoIni() - t.getSuporteIni());
		} else if (x >= t.getNucleoFim() && x <= t.getSuporteFim()) {
			return (t.getSuporteFim() - x) / (t.getSuporteFim() - t.getNucleoFim());
		}
		return 0;
	}
}
