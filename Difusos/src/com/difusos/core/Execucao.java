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
	Calculo calc = new Calculo();

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

	public boolean agregar() {
		List<Ativados> listaAtivados    = txtBusca.getAtivados();
		List<Ativados> listaClone 	    = new ArrayList<>();
		List<Ativados> listaDuplicados  = new ArrayList<>();
		boolean        contemDuplicados = false;
		for (Ativados a : listaAtivados) {
			contemDuplicados = false;
			if(listaDuplicados.contains(a)){
				continue;
			}
			for (Ativados a2 : listaAtivados) {
				boolean iguais		 = a == a2;
				boolean termosIguais = a.getTermo().equals(a2.getTermo());
				boolean contemA      = listaDuplicados.contains(a); 
				boolean contemA2     = listaDuplicados.contains(a2); 
				if(!iguais && termosIguais){
					contemDuplicados = true;
					if(!contemA)
						listaDuplicados.add(a);
					if(!contemA2)
						listaDuplicados.add(a2);
				}
			}
			if(!contemDuplicados){
				listaClone.add(a);
			}
		}
		if (!listaDuplicados.isEmpty()) {
			boolean min = txtBusca.getAgregacaoConfigMinimo();
			List<String> termosDuplicados = new ArrayList<>();
			for (Ativados d : listaDuplicados) {
				for (Ativados d2 : listaDuplicados) {
					boolean iguais = d == d2;
					boolean termosIguais = d.getTermo().equals(d2.getTermo());
					boolean contemNaLista = termosDuplicados.contains(d.getTermo());
					if(contemNaLista){
						break;
					}
					if(!iguais && termosIguais){
						termosDuplicados.add(d.getTermo());
						break;
					}
				}
			}
			List<Ativados> duplicadosUmTermo = new ArrayList<>();
			for (String t : termosDuplicados) {
				for (Ativados a : listaDuplicados) {
					if(a.getTermo().equals(t)){
						duplicadosUmTermo.add(a);
					}
				}
				Ativados a2 = duplicadosUmTermo.get(0);
				for (Ativados d2 : duplicadosUmTermo) {
					if(a2 != d2){
						a2 = removeDuplicados(a2, d2, min);
					}
				}
				duplicadosUmTermo = new ArrayList<>();
				listaClone.add(a2);
			}
		}
		txtPreenche.agregaBaseDeConhecimento(listaClone);
		return true;
	}

	private Ativados removeDuplicados(Ativados d, Ativados d2, boolean min) {
		float x = d.getPertinencia();
		float y = d2.getPertinencia();
		if (min) {
			if (x <= y) {
				return d;
			}
		} else {
			if (x >= y) {
				return d;
			}
		}
		return d2;
	}

	public void pertinencias() {
		List<Variaveis> listaDeVariaveis = txtBusca.listaDeVariaveis();
		List<Termos> listaDeTermos = txtBusca.listaDeTermos();
		List<Pertinencia> listaDePertinencias = new ArrayList<>();
		BuscarDadosTxt txtBusca = new BuscarDadosTxt();
		List<DadosVar> dadosVariaveis = txtBusca.listadeDados();
		float valorVariavel = 0;
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
					float pertinencia = calc.calculaPertinencia(t, valorVariavel);
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
				String concentracao = condicao.getConcentracao();
				if(concentracao.equals("MUITO")){
					pertinencia = calc.muito(pertinencia);
				} else if(concentracao.equals("ALGO")){
					pertinencia = calc.algo(pertinencia);
				} else if(concentracao.equals("DE FATO")){
					pertinencia = calc.deFato(pertinencia);
				} else if(concentracao.equals("MUITO MUITO")){
					pertinencia = calc.algo(pertinencia);
				} 
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
