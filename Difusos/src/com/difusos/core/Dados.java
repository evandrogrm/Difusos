package com.difusos.core;

import java.util.ArrayList;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;

public class Dados {
	
	private ArrayList<Variaveis> listaDeVariaveis;
	private ArrayList<Termos> listaDeTermos;
	
	public float calculaPertinencia(Variaveis variavel, float x) {
		for (Termos t : listaDeTermos) {
			if (t.getVariavel() == variavel) {
				if (x >= t.getNucleoIni() && x <= t.getNucleoFim()) {
					return 1;
				} else if (x > t.getSuporteFim() || x < t.getSuporteIni()) {
					return 0;
				} else if (x >= t.getSuporteIni() && x <= t.getNucleoIni()) {
					return (x - t.getSuporteIni()) / (t.getNucleoIni() - t.getSuporteIni());
				} else if (x >= t.getNucleoFim() && x <= t.getSuporteFim()) {
					return (t.getSuporteFim() - x) / (t.getSuporteFim() - t.getNucleoFim());
				}
			}
		}
		return 0;
	}

}
