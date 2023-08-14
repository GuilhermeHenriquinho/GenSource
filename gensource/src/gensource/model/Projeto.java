package gensource.model;

import java.util.List;

public class Projeto {
	private String nomeProjeto;
	private String diretorioProjeto;
	private Boolean projetoWeb;
	public String getDiretorioProjeto() {
		return diretorioProjeto;
	}
	public void setDiretorioProjeto(String caminhoProjeto) {
		this.diretorioProjeto = caminhoProjeto;
	}
	private List<Classe> classes;
	private Conexao conexao;
	
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public List<Classe> getClasses() {
		return classes;
	}
	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}
	public Conexao getConexao() {
		return conexao;
	}
	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}
	public Boolean getProjetoWeb() {
		return projetoWeb;
	}
	public void setProjetoWeb(Boolean projetoWeb) {
		this.projetoWeb = projetoWeb;
	}
	
}
