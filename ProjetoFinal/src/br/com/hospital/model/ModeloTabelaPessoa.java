package br.com.hospital.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaPessoa extends AbstractTableModel {

	private static final String[] colunas = { "ID", "NOME", "CPF" };
	private ArrayList<PessoaDTO> pessoas;
	
	public ModeloTabelaPessoa(ArrayList<PessoaDTO> pessoas) {
		super();
		this.pessoas = pessoas;
	}

	@Override
	public int getRowCount() {
		return pessoas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PessoaDTO pessoa = pessoas.get(rowIndex);
		if (columnIndex == 0) {
			return pessoa.getIdPessoa();
		} else if (columnIndex == 1) {
			return pessoa.getNomePessoa();
		} else if (columnIndex == 2) {
			return pessoa.getCpfPessoa();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}
	
	public void atualizarDados(ArrayList<PessoaDTO> novasPessoas) {
        this.pessoas = novasPessoas;
        fireTableDataChanged(); // Notificar a tabela sobre a mudança nos dados
    }
	
}
