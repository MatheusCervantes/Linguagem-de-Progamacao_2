package br.com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.hospital.ctr.Conexao;
import br.com.hospital.model.ConsultaDTO;
import br.com.hospital.model.MedicoDTO;


public class ConsultaDAO {
	public void cadastrarConsulta(ConsultaDTO consultaDTO) throws SQLException {
		try {
			Connection c = Conexao.getInstancia().abrirConexao();
			String sql = "INSERT INTO Consulta (idConsulta, idMedico, idPessoa, dataConsulta, horaConsulta, descricaoConsulta) VALUES (null,?,?,?,?,?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, consultaDTO.getIdMedico());
			stmt.setInt(2, consultaDTO.getIdPessoa());
			stmt.setString(3, consultaDTO.getDataConsulta());
			stmt.setString(4, consultaDTO.getHoraConsulta());
			stmt.setString(5, consultaDTO.getDescricaoConsulta());
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void alterarConsulta(int id, ConsultaDTO consultaDTO) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "UPDATE Consulta SET idMedico = ?, idPessoa = ?, dataConsulta = ?, horaConsulta = ?, descricaoConsulta = ? WHERE idConsulta = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, consultaDTO.getIdMedico());
			stmt.setInt(2, consultaDTO.getIdPessoa());
			stmt.setString(3, consultaDTO.getDataConsulta());
			stmt.setString(4, consultaDTO.getHoraConsulta());
			stmt.setString(5, consultaDTO.getDescricaoConsulta());
			stmt.setInt(6, id);
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removerConsulta(int id) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "DELETE from Consulta WHERE idConsulta = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<ConsultaDTO> pequisarConsultaTabela() {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		ArrayList<ConsultaDTO> consultas = new ArrayList<ConsultaDTO>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select idConsulta, Consulta.idMedico, Consulta.idPessoa, dataConsulta, horaConsulta from Consulta join Medico on Medico.idMedico = Consulta.idMedico join Pessoa on Pessoa.idPessoa = Consulta.idPessoa");
			while (rs.next()) {
				ConsultaDTO consultaDTO = new ConsultaDTO();
					consultaDTO.setIdConsulta(rs.getInt("idConsulta"));
					consultaDTO.setIdMedico(rs.getInt("idMedico"));
					consultaDTO.setIdPessoa(rs.getInt("idPessoa"));
					consultaDTO.setDataConsulta(rs.getString("dataConsulta"));
					consultaDTO.setHoraConsulta(rs.getString("horaConsulta"));
				
				consultas.add(new ConsultaDTO(consultaDTO.getIdConsulta(), consultaDTO.getIdMedico(), consultaDTO.getIdPessoa(), consultaDTO.getDataConsulta(), consultaDTO.getHoraConsulta(), consultaDTO.getDescricaoConsulta()));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return consultas;
	}

	public ConsultaDTO pequisaConsulta(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		ConsultaDTO consultaDTO = new ConsultaDTO();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Consulta WHERE idConsulta = " + id);
			consultaDTO.setIdMedico(rs.getInt("idMedico"));
			consultaDTO.setIdPessoa(rs.getInt("idPessoa"));
			consultaDTO.setDataConsulta(rs.getString("dataConsulta"));
			consultaDTO.setHoraConsulta(rs.getString("horaConsulta")); 
			consultaDTO.setDescricaoConsulta(rs.getString("descricaoConsulta"));
			consultaDTO.setIdConsulta(rs.getInt("idConsulta"));
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return consultaDTO;
	}
	
	public String pesquisaNomeMedicoConsulta(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		String nomeMedico="";
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select nomeMedico from Medico m "
					+ "join Consulta c on m.idMedico = c.idMedico  "
					+ "join Pessoa p on p.idPessoa = c.idPessoa "
					+ "where idConsulta = " + id);
			nomeMedico = rs.getString("nomeMedico");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		return nomeMedico;
	}
	
	public String pesquisaNomePessoaConsulta(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		String nomePessoa="";
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select nomePessoa from Medico m "
					+ "join Consulta c on m.idMedico = c.idMedico  "
					+ "join Pessoa p on p.idPessoa = c.idPessoa "
					+ "where idConsulta = " + id);
			nomePessoa = rs.getString("nomePessoa");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		return nomePessoa;
	}
}
