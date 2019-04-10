package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	
	Map<Long, Time> times = new HashMap<>();
	Map<Long,Jogador> jogadores = new TreeMap<Long, Jogador>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
			if (times.containsKey(id)) {
				throw new IdentificadorUtilizadoException();
			}else {
				times.put(id, new Time (id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
			}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		verificarTime(idTime);
		if(jogadores.containsKey(id)) {
			throw new IdentificadorUtilizadoException();
		}else {
			jogadores.put(id, new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
		}	
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		verificarJogador(idJogador);
		times.get(jogadores.get(idJogador).getIdTime()).setIdCapitao(idJogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		verificarTime(idTime);
		if (times.get(idTime).getIdCapitao() == null) {
			throw new CapitaoNaoInformadoException();
		}
		return times.get(idTime).getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		verificarJogador(idJogador);
		return jogadores.get(idJogador).getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		verificarTime(idTime);
		return times.get(idTime).getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificarTime(idTime);
		return jogadores.values().stream().filter(x -> x.getIdTime().equals(idTime)).collect(Collectors.toList()).stream().map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificarTime(idTime);
		return jogadores.values().stream().filter(x -> x.getIdTime().equals(idTime)).max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		verificarTime(idTime);
		return jogadores.values().stream().filter(x -> x.getIdTime().equals(idTime)).min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		if (times.size() == 0) {
			return new ArrayList<Long>();
		}
		List<Long> idsTimesOrdenado = new ArrayList<Long>(times.keySet());
		Collections.sort(new ArrayList<Long>(times.keySet()));
		return idsTimesOrdenado;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificarTime(idTime);
		return jogadores.values().stream().filter(x -> x.getIdTime().equals(idTime)).sorted(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId)).findFirst().get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		verificarJogador(idJogador);
		return jogadores.get(idJogador).getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (jogadores.size() == 0) {
			return new ArrayList<Long>(); 
		}
		List<Jogador> jogadoresOrdenados = new ArrayList<Jogador>(jogadores.values());
		Collections.sort(jogadoresOrdenados, Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId)); 
		List<Long> idsOrdenados = new ArrayList<Long>();
		for (int i = 0; i < top; i++) { 
			idsOrdenados.add(jogadoresOrdenados.get(i).getId()); 
		} 
		return idsOrdenados;
	}
	
	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (!(times.containsKey(timeDaCasa)) || !(times.containsKey(timeDeFora))) {
			throw new TimeNaoEncontradoException();
		}
		if (times.get(timeDaCasa).getCorUniformePrincipal().equals(times.get(timeDeFora).getCorUniformePrincipal())) {
			return times.get(timeDeFora).getCorUniformeSecundario();
		}else {
			return times.get(timeDeFora).getCorUniformePrincipal();
		}
	}

	public void verificarJogador(Long idJogador) {
		if (!(jogadores.containsKey(idJogador))) {
			throw new JogadorNaoEncontradoException();
		}
	}
	
	public void verificarTime(Long idTime) {
		if (!(times.containsKey(idTime))) {
			throw new TimeNaoEncontradoException();
		}
	}
}