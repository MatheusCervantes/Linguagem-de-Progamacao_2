package br.com.hospital.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import br.com.hospital.dao.ConsultaDAO;

public class ModeloTabelaConsulta extends AbstractTableModel {

	private static final String[] colunas = { "ID", "PESSOA", "MEDICO", "DATA", "HORA"};
	private ArrayList<ConsultaDTO> consultas = new ArrayList<ConsultaDTO>();
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	
	public ModeloTabelaConsulta(ArrayList<ConsultaDTO> consultas) {
		super();
		this.consultas = consultas;
	}

	@Override
	public int getRowCount() {
		return consultas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ConsultaDTO consulta = consultas.get(rowIndex);
		if (columnIndex == 0) {
			return consulta.getIdConsulta();
		} else if (columnIndex == 1) {
			return consultaDAO.pesquisaNomePessoaConsulta(consulta.getIdConsulta());
		} else if (columnIndex == 2) {
			return consultaDAO.pesquisaNomeMedicoConsulta(consulta.getIdConsulta());
		} else if (columnIndex == 3) {
			return consulta.getDataConsulta();
		} else if (columnIndex == 4) {
			return consulta.getHoraConsulta();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}
	
	public void atualizarDados(ArrayList<ConsultaDTO> novasConsultas) {
        this.consultas = novasConsultas;
        fireTableDataChanged(); // Notificar a tabela sobre a mudança nos dados
    }
	
}
