package com.difusos.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.difusos.entity.Agregados;
import com.difusos.entity.Ativados;
import com.difusos.entity.Condicao;
import com.difusos.entity.Consequencia;
import com.difusos.entity.DadosVar;
import com.difusos.entity.Regras;
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
	
	public List<DadosVar> listadeDados(){
		List<Variaveis> variaveis = listaDeVariaveis();
		List<DadosVar> listadeDados = new ArrayList<>();
		try {
			Path pathDadosVar = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/dadosVariaveis.txt");
			BufferedReader brDados = new BufferedReader(new FileReader(pathDadosVar.toFile().getAbsolutePath()));
			String linhaDados = "";
			String[] strt = new String[2];
			while ((linhaDados = brDados.readLine()) != null) {
				strt = linhaDados.split(";");
				for (Variaveis v : variaveis) {
					if(v.getNome().equals(strt[0])){
						DadosVar d = new DadosVar(v, Float.parseFloat(strt[1]));
						listadeDados.add(d);
					}
				}
			}
			brDados.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadeDados;
	}
	
	public float pertinenciaDaVariavel(Termos t){
		
		return 0;
	}

	public List<Regras> listadeRegras() {
		List<Regras> listadeRegras = new ArrayList<>();
		List<Condicao> condicoes = new ArrayList<>();
		try {
			Path pathBaseConhecimento = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/bc.txt");
			if(pathBaseConhecimento.toFile().exists()){
				BufferedReader brBC = new BufferedReader(new FileReader(pathBaseConhecimento.toFile().getAbsolutePath()));
				String linha = "";
				String[] str = new String[5];
				while ((linha = brBC.readLine()) != null) {
					str = linha.split(";");
					Condicao c = null;
					Consequencia cq = null;
					if(linha.startsWith("00")){
						c = new Condicao(str[1], str[2], str[3], str[4]);
						condicoes.add(c);
					}else if(linha.startsWith("05")){
						cq = new Consequencia(str[1], str[2]);
						Regras r = new Regras(condicoes,cq);
						listadeRegras.add(r);
						condicoes = new ArrayList<>();
					}else{
						JOptionPane.showMessageDialog(null, "Erro!");
					}
					
				}
				brBC.close();
			}else{
				JOptionPane.showMessageDialog(null, "Base de conhecimento não incluida na pasta\n"+
													pathBaseConhecimento.toFile().getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadeRegras;
	}
	
	public float getPertinencia(Termos t){
		try {
			float pertinencia = 0;
			Path pathPertinencia = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/pertinencias.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathPertinencia.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[4];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				boolean variavelIgual = t.getVariavel().getNome().equals(str[0]);
				boolean termoIgual = t.getNome().equals(str[1]);
				if(variavelIgual && termoIgual){
					pertinencia = Float.parseFloat(str[2]);
					br.close();
					return pertinencia;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Termos getTermo(String nomeTermo, String nomeVariavel){
		try {
			Path pathTermos = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/termos.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathTermos.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[6];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				boolean variavelIgual = nomeVariavel.equals(str[0]);
				boolean termoIgual    = nomeTermo.equals(str[1]);
				if(variavelIgual && termoIgual){
					Variaveis v = getVariavel(nomeVariavel);
					Termos t = new Termos(v, str[1], Float.parseFloat(str[2]), Float.parseFloat(str[3]), Float.parseFloat(str[4]),
							Float.parseFloat(str[5]));
					br.close();
					return t;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Variaveis getVariavel(String nomeVariavel){
		try {
			Path pathVariaveis = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/variaveis.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathVariaveis.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[4];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				boolean variavelIgual = nomeVariavel.equals(str[0]);
				if(variavelIgual){
					Variaveis v = new Variaveis(str[0], str[1], str[2], str[3]);
					br.close();
					return v;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean getAgregacaoConfigMinimo() {
		try {
			Path pathConfig = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/config.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathConfig.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[2];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				if(linha.startsWith("agrega;")){
					if(str[1].equalsIgnoreCase("MAX")){
						br.close();
						return false;
					}else if(str[1].equalsIgnoreCase("MIN")){
						br.close();
						return true;
					}else{
						JOptionPane.showMessageDialog(null, "ERRO");
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<Ativados> getAtivados() {
		List<Ativados> lista = new ArrayList<>();
		try {
			Path pathAtivados = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/ativacao.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathAtivados.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[2];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				String termo      = str[0];
				float pertinencia = Float.parseFloat(str[1]);
				Ativados a        = new Ativados(termo, pertinencia);
				lista.add(a);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Agregados> getAgregados() {
		List<Agregados> listadeAgregados = new ArrayList<>();
		try {
			Path pathAgregados = Paths.get(System.getenv("APPDATA") + "/DifusosEvandro/agregacao.txt");
			BufferedReader br = new BufferedReader(new FileReader(pathAgregados.toFile().getAbsolutePath()));
			String linha = "";
			String[] str = new String[2];
			while ((linha = br.readLine()) != null) {
				str = linha.split(";");
				String termo      = str[0];
				float pertinencia = Float.parseFloat(str[1]);
				Agregados a       = new Agregados(termo, pertinencia);
				listadeAgregados.add(a);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listadeAgregados;
	}

}
