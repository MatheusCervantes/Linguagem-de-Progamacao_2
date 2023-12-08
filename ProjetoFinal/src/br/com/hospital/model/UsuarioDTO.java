package br.com.hospital.model;

public class UsuarioDTO {
	private int id;
	private String nome;
	private String senha;
	private String tipoUsuario;

	public UsuarioDTO(int id, String nome, String senha, String tipoUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
