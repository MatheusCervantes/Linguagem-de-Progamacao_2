package br.com.hospital.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaMedico extends AbstractTableModel {

	private static final String[] colunas = { "ID", "NOME", "CRM", "CPF" };
	private ArrayList<MedicoDTO> medicos;

	public ModeloTabelaMedico(ArrayList<MedicoDTO> medicos) {
		super();
		this.medicos = medicos;
	}

	@Override
	public int getRowCount() {
		return medicos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MedicoDTO medico = medicos.get(rowIndex);
		if (columnIndex == 0) {
			return medico.getIdMedico();
		} else if (columnIndex == 1) {
			return medico.getNomeMedico();
		} else if (columnIndex == 2) {
			return medico.getCrmMedico();
		} else if (columnIndex == 3) {
			return medico.getCpfMedico();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

	public void atualizarDados(ArrayList<MedicoDTO> novosMedicos) {
		this.medicos = novosMedicos;
		fireTableDataChanged();
	}

}
