package br.com.hospital.model;

public class CadastrarLoginDTO {
	private int idLogin;
	private String usuarioLogin, senhaLogin, nomeLogin, cpfLogin, dtanascLogin, tipousuarioLogin;

	public CadastrarLoginDTO(int idLogin, String usuarioLogin, String senhaLogin, String nomeLogin, String cpfLogin,
			String dtanascLogin, String tipousuarioLogin) {
		super();
		this.idLogin = idLogin;
		this.usuarioLogin = usuarioLogin;
		this.senhaLogin = senhaLogin;
		this.nomeLogin = nomeLogin;
		this.cpfLogin = cpfLogin;
		this.dtanascLogin = dtanascLogin;
		this.tipousuarioLogin = tipousuarioLogin;
	}

	public CadastrarLoginDTO() {

	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getTipousuarioLogin() {
		return tipousuarioLogin;
	}

	public void setTipousuarioLogin(String tipousuarioLogin) {
		this.tipousuarioLogin = tipousuarioLogin;
	}

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}

	public String getCpfLogin() {
		return cpfLogin;
	}

	public void setCpfLogin(String cpfLogin) {
		this.cpfLogin = cpfLogin;
	}

	public String getDtanascLogin() {
		return dtanascLogin;
	}

	public void setDtanascLogin(String dtanascLogin) {
		this.dtanascLogin = dtanascLogin;
	}

}
