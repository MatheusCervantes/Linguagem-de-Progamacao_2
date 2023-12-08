package br.com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.hospital.ctr.Conexao;
import br.com.hospital.model.PessoaDTO;

public class PessoaDAO {

	public void cadastrarPessoa(PessoaDTO pessoa) throws SQLException {
		try {
			Connection c = Conexao.getInstancia().abrirConexao();
			String sql = "INSERT INTO PESSOA (idPessoa, nomePessoa, cpfPessoa, enderecoPessoa, cidadePessoa, estadoPessoa, telefonePessoa, numeroPessoa) VALUES (null,?,?,?,?,?,?,?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpfPessoa());
			stmt.setString(3, pessoa.getEnderecoPessoa());
			stmt.setString(4, pessoa.getCidadePessoa());
			stmt.setString(5, pessoa.getEstadoPessoa());
			stmt.setString(6, pessoa.getTelefonePessoa());
			stmt.setString(7, pessoa.getNumeroPessoa());
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void alterarPessoa(int id, PessoaDTO pessoa) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "UPDATE PESSOA SET nomePessoa = ?, cpfPessoa = ?, enderecoPessoa = ?, cidadePessoa = ?, estadoPessoa = ?, numeroPessoa = ?, telefonePessoa = ? WHERE idPessoa = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpfPessoa());
			stmt.setString(3, pessoa.getEnderecoPessoa());
			stmt.setString(4, pessoa.getCidadePessoa());
			stmt.setString(5, pessoa.getEstadoPessoa());
			stmt.setString(6, pessoa.getTelefonePessoa());
			stmt.setString(7, pessoa.getNumeroPessoa());
			stmt.setInt(8, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removerPessoa(int id) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "DELETE from PESSOA WHERE idPessoa = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<PessoaDTO> pequisarPessoaTabela() {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		ArrayList<PessoaDTO> pessoas = new ArrayList<PessoaDTO>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PESSOA;");
			while (rs.next()) {
				PessoaDTO pessoaDTO = new PessoaDTO();
				pessoaDTO.setNomePessoa(rs.getString("nomePessoa"));
				pessoaDTO.setCpfPessoa(rs.getString("cpfPessoa"));
				pessoaDTO.setEnderecoPessoa(rs.getString("enderecoPessoa"));
				pessoaDTO.setEstadoPessoa(rs.getString("estadoPessoa"));
				pessoaDTO.setCidadePessoa(rs.getString("cidadePessoa"));
				pessoaDTO.setNumeroPessoa(rs.getString("numeroPessoa"));
				pessoaDTO.setIdPessoa(rs.getInt("idPessoa"));

				pessoas.add(new PessoaDTO(pessoaDTO.getIdPessoa(), pessoaDTO.getNomePessoa(), pessoaDTO.getCpfPessoa(),
						pessoaDTO.getEnderecoPessoa(), pessoaDTO.getCidadePessoa(), pessoaDTO.getEstadoPessoa(),
						pessoaDTO.getNumeroPessoa(), pessoaDTO.getTelefonePessoa()));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pessoas;
	}

	public static PessoaDTO pequisarPessoa(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		PessoaDTO pessoaDTO = new PessoaDTO();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PESSOA WHERE idPessoa = " + id);
			pessoaDTO.setNomePessoa(rs.getString("nomePessoa"));
			pessoaDTO.setCpfPessoa(rs.getString("cpfPessoa"));
			pessoaDTO.setEnderecoPessoa(rs.getString("enderecoPessoa"));
			pessoaDTO.setEstadoPessoa(rs.getString("estadoPessoa"));
			pessoaDTO.setCidadePessoa(rs.getString("cidadePessoa"));
			pessoaDTO.setNumeroPessoa(rs.getString("numeroPessoa"));
			pessoaDTO.setTelefonePessoa(rs.getString("telefonePessoa"));
			pessoaDTO.setIdPessoa(id);

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		return pessoaDTO;
	}
}
