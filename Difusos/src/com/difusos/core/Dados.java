package com.difusos.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;

public class Dados {
	
	private ArrayList<Variaveis> listaDeVariaveis;
	private ArrayList<Termos> listaDeTermos;
	
	public float calculaPertinencia(Variaveis variavel, float x) {
		preencheVariaveis();
		for (Termos t : listaDeTermos) {
			if (t.getVariavel().getNome().equals(variavel.getNome())) {
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

	private void preencheVariaveis() {
		try {
			listaDeVariaveis = new ArrayList<>();
			Path pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
			BufferedReader brVariaveis = new BufferedReader(new FileReader(pathVariaveis.toFile().getAbsolutePath()));
			String linhaVariaveis="";
			String[] strv = new String[4];
			while((linhaVariaveis = brVariaveis.readLine()) != null){
				strv = linhaVariaveis.split(";");
				listaDeVariaveis.add(new Variaveis(strv[0],strv[1],strv[2]));
			}
			listaDeTermos = new ArrayList<>();
			Path pathTermos = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/termos.txt");
			BufferedReader brTermos = new BufferedReader(new FileReader(pathTermos.toFile().getAbsolutePath()));
			String linhaTermos="";
			String[] strt = new String[4];
			while((linhaTermos = brTermos.readLine()) != null){
				strt = linhaTermos.split(";");
				listaDeTermos.add(new Termos(buscaVariavel(strt[0]), strt[1], Float.parseFloat(strt[2]),
						Float.parseFloat(strt[3]), Float.parseFloat(strt[4]), Float.parseFloat(strt[5])));
			}
			brVariaveis.close();
			brTermos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Variaveis buscaVariavel(String string) {
		try {
			for (Variaveis v : listaDeVariaveis) {
				if(v.getNome().equals(string)){
					return v;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Variável não encontrada");
		return null;
	}
	
}
