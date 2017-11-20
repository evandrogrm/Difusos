package com.difusos.core;

import java.util.ArrayList;
import java.util.List;

import com.difusos.entity.Agregados;
import com.difusos.entity.Ativados;
import com.difusos.entity.Condicao;
import com.difusos.entity.DadosVar;
import com.difusos.entity.Pertinencia;
import com.difusos.entity.Regras;
import com.difusos.entity.Termos;
import com.difusos.entity.Variaveis;
import com.difusos.util.BuscarDadosTxt;
import com.difusos.util.PreencherDadosTxt;

public class Execucao {
	
	BuscarDadosTxt txtBusca = new BuscarDadosTxt();
	PreencherDadosTxt txtPreenche = new PreencherDadosTxt();
	Dados dados;

	public Execucao() {
	}

	public float calcularCentroide() {
		List<Agregados> listaDeAgregados = txtBusca.getAgregados();
		List<Termos> listaDeTermos = txtBusca.listaDeTermos();
		float pertinenciaTermo = 0;
		float nucleoInicial = 0;
		float nucleoFinal = 0;
		float result = 0;
		float resultDivisao = 0;
		
		for (Agregados agregados : listaDeAgregados) {
			for (Termos termos : listaDeTermos) {
				if(termos.getVariavel().getIsObjetivo().equals("1") && termos.getNome().equals(agregados.getTermo())){
					nucleoInicial   = termos.getNucleoIni();
					nucleoFinal     = termos.getNucleoFim();
					pertinenciaTermo = agregados.getPertinencia();
					break;
				}
			}
			result += somarTermo(nucleoInicial, nucleoFinal, pertinenciaTermo);
			resultDivisao += dividirTermo(nucleoInicial, nucleoFinal, pertinenciaTermo);
		}
		result /= resultDivisao;
		
		return result;
	}

	private float dividirTermo(float nucletoInicial, float nucleoFinal, float pertinenciaTermo) {
		float retorno = 0;
		for (float i = nucletoInicial; i <= nucleoFinal; i += 5) {
			retorno += 1;
		}
		retorno *= pertinenciaTermo;
		return retorno;
	}

	private float somarTermo(float nucletoInicial, float nucleoFinal, float pertinenciaTermo) {
		float retorno = 0;
		for (float i = nucletoInicial; i <= nucleoFinal; i += 5) {
			retorno += i;
		}
		retorno *= pertinenciaTermo;
		return retorno;
	}

	public void agregar() {
		//TODO AQUI
		List<Ativados> listaAtivados = txtBusca.getAtivados();
		List<Ativados> listaClone = new ArrayList<>();
		boolean min = txtBusca.getAgregacaoConfigMinimo();
		boolean entrou = false;
		for (Ativados a : listaAtivados) {
			entrou = false;
			for (Ativados a2 : listaAtivados) {
				if(a != a2 && a.getTermo().equals(a2.getTermo())){
					entrou = true;
					float x = a.getPertinencia();
					float y = a2.getPertinencia();
					if(min){
						if(x < y){
							listaClone.add(a);
						}else{
							listaClone.add(a2);
						}
					}else{
						if(x > y){
							listaClone.add(a);
						}else{
							listaClone.add(a2);
						}
					}
				}
			}
			if(!entrou){
				listaClone.add(a);
			}
		}
		for (Ativados a1 : listaClone) {
			for (Ativados a2 : listaClone) {
				if(a1 != a2 && a1.getTermo().equals(a2.getTermo())){
					agregar();
				}
			}
		}
		
		txtPreenche.agregaBaseDeConhecimento(listaClone);
	}

	public void pertinencias() {
		List<Variaveis> listaDeVariaveis = txtBusca.listaDeVariaveis();
		List<Termos> listaDeTermos = txtBusca.listaDeTermos();
		List<Pertinencia> listaDePertinencias = new ArrayList<>();
		BuscarDadosTxt txtBusca = new BuscarDadosTxt();
		List<DadosVar> dadosVariaveis = txtBusca.listadeDados();
		float valorVariavel = 0;
		dados = new Dados();
		for (Variaveis v : listaDeVariaveis) {
			if(v.getIsObjetivo().equals("1")){
				break;
			}
			for (DadosVar d : dadosVariaveis) {
				if (d.getV().getNome().equals(v.getNome())) {
					valorVariavel = d.getValor();
					break;
				}
			}
			for (Termos t : listaDeTermos) {
				if (t.getVariavel().getNome().equals(v.getNome())) {
					String variavel = v.getNome();
					String termo = t.getNome();
					float pertinencia = dados.calculaPertinencia(t, valorVariavel);
					listaDePertinencias.add(new Pertinencia(variavel,termo,pertinencia));
				}
			}
		}
		txtPreenche.preenchePertinencias(listaDePertinencias);
		
	}

	public void ativar() {
		Calculo calculo     = new Calculo();
		List<Regras> regras = txtBusca.listadeRegras();
		List<Float> pertinencias = new ArrayList<>();
		List<Ativados> listaDeAtivados = new ArrayList<>();
		float pertinencia = 0;
		boolean E = true;
		for (Regras r : regras) {
			List<Condicao> condicoes = r.getC();
			for (Condicao condicao : condicoes) {
				String nomeTermo    = condicao.getTermoCondicao();
				String nomeVariavel = condicao.getVariavelCondicao();
				Termos t            = txtBusca.getTermo(nomeTermo, nomeVariavel);
				pertinencia         = txtBusca.getPertinencia(t);
				pertinencias.add(pertinencia);
				if(condicao.getTipoAgregacao().equals("E")){
					E = true;
				}else{
					E = false;
				}
			}
			for (Float f : pertinencias) {
				if(E){
					pertinencia = calculo.min(f, pertinencia);
				}else{
					pertinencia = calculo.max(f, pertinencia);
				}
			}
			if (pertinencia > 0) {
				listaDeAtivados.add(new Ativados(r.getCq().getTermoConsequencia(),pertinencia));
			}
			pertinencias = new ArrayList<>();
		}
		
		txtPreenche.ativaBaseDeConhecimento(listaDeAtivados);
		
	}

}
