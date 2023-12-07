package br.com.hospital.model;

public class MedicoDTO {
	private int idMedico;
	private String nomeMedico;
	private String cpfMedico;
	private String enderecoMedico;
	private String cidadeMedico;
	private String numeroMedico;
	private String estadoMedico;
	private String telefoneMedico;
	private String crmMedico;
	
	
	
	public MedicoDTO(int idMedico, String nomeMedico, String cpfMedico, String enderecoMedico, String cidadeMedico,
			String numeroMedico, String estadoMedico, String telefoneMedico, String crmMedico) {
		super();
		this.idMedico = idMedico;
		this.nomeMedico = nomeMedico;
		this.cpfMedico = cpfMedico;
		this.enderecoMedico = enderecoMedico;
		this.cidadeMedico = cidadeMedico;
		this.numeroMedico = numeroMedico;
		this.estadoMedico = estadoMedico;
		this.telefoneMedico = telefoneMedico;
		this.crmMedico = crmMedico;
	}



	public MedicoDTO() {
		
	}



	public int getIdMedico() {
		return idMedico;
	}



	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}



	public String getNomeMedico() {
		return nomeMedico;
	}



	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}



	public String getCpfMedico() {
		return cpfMedico;
	}



	public void setCpfMedico(String cpfMedico) {
		this.cpfMedico = cpfMedico;
	}



	public String getEnderecoMedico() {
		return enderecoMedico;
	}



	public void setEnderecoMedico(String enderecoMedico) {
		this.enderecoMedico = enderecoMedico;
	}



	public String getCidadeMedico() {
		return cidadeMedico;
	}



	public void setCidadeMedico(String cidadeMedico) {
		this.cidadeMedico = cidadeMedico;
	}



	public String getNumeroMedico() {
		return numeroMedico;
	}



	public void setNumeroMedico(String numeroMedico) {
		this.numeroMedico = numeroMedico;
	}



	public String getEstadoMedico() {
		return estadoMedico;
	}



	public void setEstadoMedico(String estadoMedico) {
		this.estadoMedico = estadoMedico;
	}



	public String getTelefoneMedico() {
		return telefoneMedico;
	}



	public void setTelefoneMedico(String telefoneMedico) {
		this.telefoneMedico = telefoneMedico;
	}



	public String getCrmMedico() {
		return crmMedico;
	}



	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}
	
	
	
}
