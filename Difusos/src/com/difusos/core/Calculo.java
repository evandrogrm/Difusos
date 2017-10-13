package com.difusos.core;

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
}
