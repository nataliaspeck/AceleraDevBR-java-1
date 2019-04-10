package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {
	
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long idCapitao;
	private List<Jogador> jogadores = new ArrayList<>();
	
	public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}
	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}
	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}
	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void addJogador(Jogador jogador) {
		jogadores.add(jogador);
	}
	public void removeJogador(Jogador jogador) {
		jogadores.remove(jogador);
	}
	public Long getIdCapitao() {
		return idCapitao;
	}
	public void setIdCapitao(Long idCapitao) {
		this.idCapitao = idCapitao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corUniformePrincipal == null) ? 0 : corUniformePrincipal.hashCode());
		result = prime * result + ((corUniformeSecundario == null) ? 0 : corUniformeSecundario.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		if (corUniformePrincipal == null) {
			if (other.corUniformePrincipal != null)
				return false;
		} else if (!corUniformePrincipal.equals(other.corUniformePrincipal))
			return false;
		if (corUniformeSecundario == null) {
			if (other.corUniformeSecundario != null)
				return false;
		} else if (!corUniformeSecundario.equals(other.corUniformeSecundario))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
