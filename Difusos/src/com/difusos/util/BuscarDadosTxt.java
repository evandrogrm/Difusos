package com.difusos.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;

public class BuscarDadosTxt {
	
	public List<Variaveis> listaDeVariaveis(){
		List<Variaveis> variaveis = new ArrayList<>();
		try {
			Path pathVariaveis = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/variaveis.txt");
			BufferedReader brVariaveis;
			brVariaveis = new BufferedReader(new FileReader(pathVariaveis.toFile().getAbsolutePath()));
			String linhaVariaveis="";
			String[] strv = new String[4];
			while((linhaVariaveis = brVariaveis.readLine()) != null){
				strv = linhaVariaveis.split(";");
				Variaveis v = new Variaveis(strv[0], strv[1], strv[2], strv[3]);
				variaveis.add(v);
			}
			brVariaveis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return variaveis;
	}
	
	public List<Termos> listaDeTermos(){
		List<Variaveis> variaveis = listaDeVariaveis();
		List<Termos> termos = new ArrayList<>();
		try {
			Path pathTermos = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/termos.txt");
			BufferedReader brTermos = new BufferedReader(new FileReader(pathTermos.toFile().getAbsolutePath()));
			String linhaTermos = "";
			String[] strt = new String[6];
			while ((linhaTermos = brTermos.readLine()) != null) {
				strt = linhaTermos.split(";");
				for (Variaveis v : variaveis) {
					if(v.getNome().equals(strt[0])){
						Termos t = new Termos(v, strt[1],Float.parseFloat(strt[2]), Float.parseFloat(strt[3]), Float.parseFloat(strt[4]),
								Float.parseFloat(strt[5]));
						termos.add(t);
						break;
					}
				}
			}
			brTermos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termos;
	}

}
