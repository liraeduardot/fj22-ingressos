package br.com.caelum.ingresso.validacao;

import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	
	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
		this.sessoesDaSala = sessoesDaSala;
	}
	
	private boolean horarioIsConflitante(Sessao sessaoNova, Sessao sessaoExistente) {
		boolean sessaoNovaTerminaAntes = sessaoNova.getHorarioTermino().isBefore(sessaoExistente.getHorario());
		boolean sessaoNovaComecaDepois =sessaoNova.getHorario().isAfter(sessaoExistente.getHorarioTermino());
		
		if(sessaoNovaTerminaAntes || sessaoNovaComecaDepois) {
			return false; //Não tem conflito
		}
		return true; //Tem conflito
	}
	
	public boolean cabe(Sessao sessaoNova) {
		return sessoesDaSala.stream().noneMatch(sessaoExistente ->
							horarioIsConflitante(sessaoNova, sessaoExistente));
	}

}
