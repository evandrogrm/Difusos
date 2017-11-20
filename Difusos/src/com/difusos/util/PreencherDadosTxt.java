package com.difusos.util;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.difusos.entity.Ativados;
import com.difusos.entity.Pertinencia;

public class PreencherDadosTxt {
	
	public void preenchePertinencias(List<Pertinencia> listaDePertinencias) {
		try {
			Path pathPertinencia = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro"+"/pertinencias.txt");
			FileWriter fw = new FileWriter(pathPertinencia.toFile().getAbsolutePath());
			for (Pertinencia p : listaDePertinencias) {
				String variavel = p.getVariavel();
				String termo = p.getTermo();
				float pertinencia = p.getPertinencia();
				fw.write(variavel+";"+termo+";"+pertinencia);
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void preencheDadosVariaveis(String variavel, float valor){
		try {
			Path pathDados = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/dadosVariaveis.txt");
			FileWriter fw = new FileWriter(pathDados.toFile().getAbsolutePath(), true);
			fw.write(variavel+";"+valor);
			fw.write(System.getProperty("line.separator"));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void ativaBaseDeConhecimento(List<Ativados> listaDeAtivados) {
		try {
			Path pathAtivacao   = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/ativacao.txt");
			FileWriter fw       = new FileWriter(pathAtivacao.toFile().getAbsolutePath());
			for (Ativados a2 : listaDeAtivados) {
				fw.write(a2.getTermo()+";"+a2.getPertinencia());
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void agregaBaseDeConhecimento(List<Ativados> ativados2) {
		try {
			Path pathAgregacao = Paths.get(System.getenv("APPDATA")+"/DifusosEvandro/agregacao.txt");
			FileWriter fw = new FileWriter(pathAgregacao.toFile().getAbsolutePath());
			for (Ativados a2 : ativados2) {
				fw.write(a2.getTermo()+";"+a2.getPertinencia());
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preencheConfiguracao(boolean selected) {
		try {
			Path pathConfig = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/config.txt");
			FileWriter fw = new FileWriter(pathConfig.toFile().getAbsolutePath());
			if(selected){
				fw.write("agrega;MIN");
			}else{
				fw.write("agrega;MAX");
			}
			fw.write(System.getProperty("line.separator"));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
