package gensource.model;

import java.util.List;

public class Classe {
	
	private String nomeClasse;
	private List<Atributo> atributos;
	private String diretorioClasse;
	private Boolean gerada;
	
	public String getNomeClasse() {
		return nomeClasse;
	}
	
	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}
	
	public List<Atributo> getAtributos() {
		return atributos;
	}
	
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public String getDiretorioClasse() {
		return diretorioClasse;
	}

	public void setDiretorioClasse(String diretorioClasse) {
		this.diretorioClasse = diretorioClasse;
	}

	public Boolean getGerada() {
		return gerada;
	}

	public void setGerada(Boolean gerada) {
		this.gerada = gerada;
	}
}