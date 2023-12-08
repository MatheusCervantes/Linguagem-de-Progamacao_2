package br.com.hospital.model;

import java.util.ArrayList;

public class ConsultaDTO {
	private int idConsulta, idMedico, idPessoa;
	private String dataConsulta;
	private String horaConsulta;
	private String descricaoConsulta;

	public ConsultaDTO(int idConsulta, int idMedico, int idPessoa, String dataConsulta, String horaConsulta,
			String descricaoConsulta) {
		super();
		this.idConsulta = idConsulta;
		this.idMedico = idMedico;
		this.idPessoa = idPessoa;
		this.dataConsulta = dataConsulta;
		this.horaConsulta = horaConsulta;
		this.descricaoConsulta = descricaoConsulta;
	}

	public ConsultaDTO() {

	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public String getDescricaoConsulta() {
		return descricaoConsulta;
	}

	public void setDescricaoConsulta(String descricaoConsulta) {
		this.descricaoConsulta = descricaoConsulta;
	}

}
