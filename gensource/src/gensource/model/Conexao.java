package gensource.model;

public class Conexao {
	private String nomeConexao;
	private String url;
	private String dialect;
	private String driver;
	private String usuario;
	private String senha;
	public String getNomeConexao() {
		return nomeConexao;
	}
	public void setNomeConexao(String nomeConexao) {
		this.nomeConexao = nomeConexao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
