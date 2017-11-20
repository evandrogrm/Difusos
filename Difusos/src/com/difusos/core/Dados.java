package com.difusos.core;

import com.difusos.entity.Termos;

public class Dados {

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
