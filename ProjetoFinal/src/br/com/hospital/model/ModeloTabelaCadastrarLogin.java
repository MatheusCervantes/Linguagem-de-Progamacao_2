package br.com.hospital.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import br.com.hospital.dao.ConsultaDAO;

public class ModeloTabelaCadastrarLogin extends AbstractTableModel {

	private static final String[] colunas = { "ID", "USUARIO", "NOME", "CPF" };
	private ArrayList<CadastrarLoginDTO> logins = new ArrayList<CadastrarLoginDTO>();

	public ModeloTabelaCadastrarLogin(ArrayList<CadastrarLoginDTO> logins) {
		super();
		this.logins = logins;
	}

	@Override
	public int getRowCount() {
		return logins.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CadastrarLoginDTO cadastrar = logins.get(rowIndex);
		if (columnIndex == 0) {
			return cadastrar.getIdLogin();
		} else if (columnIndex == 1) {
			return cadastrar.getUsuarioLogin();
		} else if (columnIndex == 2) {
			return cadastrar.getNomeLogin();
		} else if (columnIndex == 3) {
			return cadastrar.getCpfLogin();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

	public void atualizarDados(ArrayList<CadastrarLoginDTO> novasUsuarios) {
		this.logins = novasUsuarios;
		fireTableDataChanged();
	}

}
