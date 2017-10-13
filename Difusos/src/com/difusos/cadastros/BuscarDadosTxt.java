package com.difusos.cadastros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BuscarDadosTxt {
	
	public String[] listaDeNomesVariaveis(){
		String[] listaVariaveis = new String[2];
		try {
			int c = 0;
			Path pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
			BufferedReader brVariaveis;
			brVariaveis = new BufferedReader(new FileReader(pathVariaveis.toFile().getAbsolutePath()));
			String linhaVariaveis="";
			String[] strv = new String[4];
			while((linhaVariaveis = brVariaveis.readLine()) != null){
				strv = linhaVariaveis.split(";");
				listaVariaveis[c] = strv[0];
				c++;
			}
			brVariaveis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaVariaveis;
	}
	
	public String[][] listaDeNomesTermos() {
		String[][] listaTermos = new String[2][3];
		int c = 0;
		int cc = 0;
		boolean primeiraVez = true;
		String temp = "-1";
		try {
			Path pathTermos = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/termos.txt");
			BufferedReader brTermos = new BufferedReader(new FileReader(pathTermos.toFile().getAbsolutePath()));
			String linhaTermos = "";
			String[] strt = new String[6];
			while ((linhaTermos = brTermos.readLine()) != null) {
				strt = linhaTermos.split(";");
				if (primeiraVez) {
					listaTermos[c][cc] = strt[1];
					cc++;
					temp = strt[0];
					primeiraVez = false;
				} else {
					if (temp.equals(strt[0])) {
						listaTermos[c][cc] = strt[1];
						cc++;
					} else {
						c++;
						cc=0;
						listaTermos[c][cc] = strt[1];
						temp = strt[0];
						cc++;
					}
				}
			}
			brTermos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTermos;
	}
	
	public String[] listaDeNomesTermos(String variavel) {
		String[] listaTermos = new String[3];
		int c = 0;
		try {
			Path pathTermos = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/termos.txt");
			BufferedReader brTermos = new BufferedReader(new FileReader(pathTermos.toFile().getAbsolutePath()));
			String linhaTermos = "";
			String[] strt = new String[6];
			while ((linhaTermos = brTermos.readLine()) != null) {
				strt = linhaTermos.split(";");
				if (variavel.equals(strt[0])) {
					strt = linhaTermos.split(";");
					listaTermos[c] = strt[1];
					c++;
				}
			}
			brTermos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaTermos;
	}
}
