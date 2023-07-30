package gensource.model;

import java.util.List;

public class Atributo {
	
	private String nomeAtributo;
	private String tipoAtributo;
	private Boolean isObrigatorio;
	private Boolean isRelacionamento;
	private Boolean consultaPor;
	private Boolean apareceNaConsulta;
	private List<String> anotacao;
	
	public String getNomeAtributo() {
		return nomeAtributo;
	}
	
	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}
	
	public String getTipoAtributo() {
		return tipoAtributo;
	}
	
	public void setTipoAtributo(String tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}

	public List<String> getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(List<String> anotacao) {
		this.anotacao = anotacao;
	}

	public Boolean getIsObrigatorio() {
		return isObrigatorio;
	}

	public void setIsObrigatorio(Boolean isObrigatorio) {
		this.isObrigatorio = isObrigatorio;
	}

	public Boolean getIsRelacionamento() {
		return isRelacionamento;
	}

	public void setIsRelacionamento(Boolean isRelacionamento) {
		this.isRelacionamento = isRelacionamento;
	}

	public Boolean getConsultaPor() {
		return consultaPor;
	}

	public void setConsultaPor(Boolean consultaPor) {
		this.consultaPor = consultaPor;
	}

	public Boolean getApareceNaConsulta() {
		return apareceNaConsulta;
	}

	public void setApareceNaConsulta(Boolean apareceNaConsulta) {
		this.apareceNaConsulta = apareceNaConsulta;
	}
	
}
