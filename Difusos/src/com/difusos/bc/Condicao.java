package com.difusos.bc;

import java.util.List;

import javax.swing.JComboBox;

import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;

public class Condicao {
	private Variaveis variaveis;
	private String concentracao;
	private Termos termos;
	private String TP;
	private List<Variaveis> listaVariaveis;
	private List<Termos> listaTermos;
	private JComboBox<Object> comboVariaveis;
	private JComboBox<Object> comboTermos;
	
	public Condicao(Variaveis variaveis, String concentracao, Termos termos, String tP) {
		super();
		this.variaveis = variaveis;
		this.concentracao = concentracao;
		this.termos = termos;
		this.TP = tP;
	}
	
	public Condicao(List<Variaveis> listaVariaveis, String concentracao, List<Termos> listaTermos, String tP) {
		super();
		this.listaVariaveis = listaVariaveis;
		this.concentracao = concentracao;
		this.listaTermos = listaTermos;
		this.TP = tP;
	}
	
	public Condicao(JComboBox<Object> comboVariaveis, String concentracao, JComboBox<Object> comboTermos, String tP) {
		super();
		this.comboVariaveis = comboVariaveis;
		this.concentracao = concentracao;
		this.comboTermos = comboTermos;
		this.TP = tP;
	}
	
	public Condicao(){}

	public Variaveis getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(Variaveis variaveis) {
		this.variaveis = variaveis;
	}

	public String getConcentracao() {
		return concentracao;
	}

	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}

	public Termos getTermos() {
		return termos;
	}

	public void setTermos(Termos termos) {
		this.termos = termos;
	}

	public String getTP() {
		return TP;
	}

	public void setTP(String tP) {
		TP = tP;
	}

	public List<Variaveis> getListaVariaveis() {
		return listaVariaveis;
	}

	public void setListaVariaveis(List<Variaveis> listaVariaveis) {
		this.listaVariaveis = listaVariaveis;
	}

	public List<Termos> getListaTermos() {
		return listaTermos;
	}

	public void setListaTermos(List<Termos> listaTermos) {
		this.listaTermos = listaTermos;
	}

	public JComboBox<Object> getComboVariaveis() {
		return comboVariaveis;
	}

	public void setComboVariaveis(JComboBox<Object> comboVariaveis) {
		this.comboVariaveis = comboVariaveis;
	}

	public JComboBox<Object> getComboTermos() {
		return comboTermos;
	}

	public void setComboTermos(JComboBox<Object> comboTermos) {
		this.comboTermos = comboTermos;
	}
	
	
}
