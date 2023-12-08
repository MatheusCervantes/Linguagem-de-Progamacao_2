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
import br.com.hospital.model.MedicoDTO;

public class MedicoDAO {
	public void cadastrarMedico(MedicoDTO medico) throws SQLException {
		try {
			Connection c = Conexao.getInstancia().abrirConexao();
			String sql = "INSERT INTO Medico (idMedico, nomeMedico, cpfMedico, enderecoMedico, cidadeMedico, estadoMedico, telefoneMedico, numeroMedico, crmMedico) VALUES (null,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, medico.getNomeMedico());
			stmt.setString(2, medico.getCpfMedico());
			stmt.setString(3, medico.getEnderecoMedico());
			stmt.setString(4, medico.getCidadeMedico());
			stmt.setString(5, medico.getEstadoMedico());
			stmt.setString(6, medico.getTelefoneMedico());
			stmt.setString(7, medico.getNumeroMedico());
			stmt.setString(8, medico.getCrmMedico());
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void alterarMedico(int id, MedicoDTO medico) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "UPDATE Medico SET nomeMedico = ?, cpfMedico = ?, enderecoMedico = ?, cidadeMedico = ?, estadoMedico = ?, numeroMedico = ?, telefoneMedico = ?, crmMedico = ? WHERE idMedico = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, medico.getNomeMedico());
			stmt.setString(2, medico.getCpfMedico());
			stmt.setString(3, medico.getEnderecoMedico());
			stmt.setString(4, medico.getCidadeMedico());
			stmt.setString(5, medico.getEstadoMedico());
			stmt.setString(6, medico.getTelefoneMedico());
			stmt.setString(7, medico.getNumeroMedico());
			stmt.setString(8, medico.getCrmMedico());
			stmt.setInt(9, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removerMedico(int id) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "DELETE from Medico WHERE idMedico = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<MedicoDTO> pequisarMedicoTabela() {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		ArrayList<MedicoDTO> medicos = new ArrayList<MedicoDTO>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Medico;");
			while (rs.next()) {
				MedicoDTO medicoDTO = new MedicoDTO();
				medicoDTO.setNomeMedico(rs.getString("nomeMedico"));
				medicoDTO.setCpfMedico(rs.getString("cpfMedico"));
				medicoDTO.setEnderecoMedico(rs.getString("enderecoMedico"));
				medicoDTO.setEstadoMedico(rs.getString("estadoMedico"));
				medicoDTO.setCidadeMedico(rs.getString("cidadeMedico"));
				medicoDTO.setNumeroMedico(rs.getString("numeroMedico"));
				medicoDTO.setIdMedico(rs.getInt("idMedico"));
				medicoDTO.setCrmMedico(rs.getString("crmMedico"));

				medicos.add(new MedicoDTO(medicoDTO.getIdMedico(), medicoDTO.getNomeMedico(), medicoDTO.getCpfMedico(),
						medicoDTO.getEnderecoMedico(), medicoDTO.getCidadeMedico(), medicoDTO.getNumeroMedico(),
						medicoDTO.getEstadoMedico(), medicoDTO.getTelefoneMedico(), medicoDTO.getCrmMedico()));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return medicos;
	}

	public static MedicoDTO pequisarMedico(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		MedicoDTO medicoDTO = new MedicoDTO();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Medico WHERE idMedico = " + id);
			medicoDTO.setNomeMedico(rs.getString("nomeMedico"));
			medicoDTO.setCpfMedico(rs.getString("cpfMedico"));
			medicoDTO.setEnderecoMedico(rs.getString("enderecoMedico"));
			medicoDTO.setEstadoMedico(rs.getString("estadoMedico"));
			medicoDTO.setCidadeMedico(rs.getString("cidadeMedico"));
			medicoDTO.setNumeroMedico(rs.getString("numeroMedico"));
			medicoDTO.setTelefoneMedico(rs.getString("telefoneMedico"));
			medicoDTO.setCrmMedico(rs.getString("crmMedico"));
			medicoDTO.setIdMedico(id);

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		return medicoDTO;
	}
}
