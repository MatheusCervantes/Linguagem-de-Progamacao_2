package br.com.hospital.model;

public class PessoaDTO {
	private int idPessoa;
	private String nomePessoa;
	private String cpfPessoa;
	private String enderecoPessoa;
	private String cidadePessoa;
	private String estadoPessoa;
	private String numeroPessoa;
	private String telefonePessoa;

	public PessoaDTO(int idPessoa, String nomePessoa, String cpfPessoa, String enderecoPessoa, String cidadePessoa,
			String estadoPessoa, String numeroPessoa, String telefonePessoa) {
		super();
		this.idPessoa = idPessoa;
		this.nomePessoa = nomePessoa;
		this.cpfPessoa = cpfPessoa;
		this.enderecoPessoa = enderecoPessoa;
		this.cidadePessoa = cidadePessoa;
		this.estadoPessoa = estadoPessoa;
		this.numeroPessoa = numeroPessoa;
		this.telefonePessoa = telefonePessoa;
	}

	public PessoaDTO() {

	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpfPessoa() {
		return cpfPessoa;
	}

	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}

	public String getEnderecoPessoa() {
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(String enderecoPessoa) {
		this.enderecoPessoa = enderecoPessoa;
	}

	public String getCidadePessoa() {
		return cidadePessoa;
	}

	public void setCidadePessoa(String cidadePessoa) {
		this.cidadePessoa = cidadePessoa;
	}

	public String getEstadoPessoa() {
		return estadoPessoa;
	}

	public void setEstadoPessoa(String estadoPessoa) {
		this.estadoPessoa = estadoPessoa;
	}

	public String getNumeroPessoa() {
		return numeroPessoa;
	}

	public void setNumeroPessoa(String numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}

	public String getTelefonePessoa() {
		return telefonePessoa;
	}

	public void setTelefonePessoa(String telefonePessoa) {
		this.telefonePessoa = telefonePessoa;
	}

}
