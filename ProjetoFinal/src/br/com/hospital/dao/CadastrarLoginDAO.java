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
import br.com.hospital.ctr.Criptografia;
import br.com.hospital.model.CadastrarLoginDTO;
import br.com.hospital.model.PessoaDTO;

public class CadastrarLoginDAO {
	public void cadastrarLogin(CadastrarLoginDTO loginDTO) throws SQLException {
		try {
			Criptografia criptografia = new Criptografia(null, "SHA-256");
			criptografia.setInformacao(loginDTO.getSenhaLogin());
			Connection c = Conexao.getInstancia().abrirConexao();
			String sql = "INSERT INTO Login (idLogin, usuarioLogin, senhaLogin, nomeLogin, cpfLogin, tipousuarioLogin, dtanascLogin) VALUES (null,?,?,?,?,?,?)";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, loginDTO.getUsuarioLogin());
			stmt.setString(2, criptografia.criptografar());
			stmt.setString(3, loginDTO.getNomeLogin());
			stmt.setString(4, loginDTO.getCpfLogin());
			stmt.setString(5, loginDTO.getTipousuarioLogin());
			stmt.setString(6, loginDTO.getDtanascLogin());
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void alterarLoginSenha(int id, CadastrarLoginDTO loginDTO) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			Criptografia criptografia = new Criptografia(null, "SHA-256");
			criptografia.setInformacao(loginDTO.getSenhaLogin());
			String sql = "UPDATE Login SET usuarioLogin = ?, senhaLogin = ?, nomeLogin = ?, cpfLogin = ?, tipousuarioLogin = ?, dtanascLogin = ? WHERE idLogin = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, loginDTO.getUsuarioLogin());
			stmt.setString(2, criptografia.criptografar());
			stmt.setString(3, loginDTO.getNomeLogin());
			stmt.setString(4, loginDTO.getCpfLogin());
			stmt.setString(5, loginDTO.getTipousuarioLogin());
			stmt.setString(6, loginDTO.getDtanascLogin());
			stmt.setInt(7, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void alterarLogin(int id, CadastrarLoginDTO loginDTO) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "UPDATE Login SET usuarioLogin = ?, nomeLogin = ?, cpfLogin = ?, tipousuarioLogin = ?, dtanascLogin = ? WHERE idLogin = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, loginDTO.getUsuarioLogin());
			stmt.setString(2, loginDTO.getNomeLogin());
			stmt.setString(3, loginDTO.getCpfLogin());
			stmt.setString(4, loginDTO.getTipousuarioLogin());
			stmt.setString(5, loginDTO.getDtanascLogin());
			stmt.setInt(6, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removerLogin(int id) throws SQLException {
		Connection c = Conexao.getInstancia().abrirConexao();
		try {
			String sql = "DELETE from Login WHERE idLogin = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<CadastrarLoginDTO> pequisarLoginTabela() {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		ArrayList<CadastrarLoginDTO> logins = new ArrayList<CadastrarLoginDTO>();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Login;");
			while (rs.next()) {
				CadastrarLoginDTO loginDTO = new CadastrarLoginDTO();
				loginDTO.setNomeLogin(rs.getString("nomeLogin"));
				loginDTO.setCpfLogin(rs.getString("cpfLogin"));
				loginDTO.setUsuarioLogin(rs.getString("usuarioLogin"));
				loginDTO.setIdLogin(rs.getInt("idLogin"));
				loginDTO.setDtanascLogin(rs.getString("dtanascLogin"));
				loginDTO.setTipousuarioLogin(rs.getString("tipousuarioLogin"));

				logins.add(new CadastrarLoginDTO(loginDTO.getIdLogin(), loginDTO.getUsuarioLogin(),
						loginDTO.getSenhaLogin(), loginDTO.getNomeLogin(), loginDTO.getCpfLogin(),
						loginDTO.getDtanascLogin(), loginDTO.getTipousuarioLogin()));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return logins;
	}

	public static CadastrarLoginDTO pequisarLogin(int id) {
		Connection c = Conexao.getInstancia().abrirConexao();
		Statement stmt = null;
		CadastrarLoginDTO loginDTO = new CadastrarLoginDTO();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Login WHERE idLogin = " + id);
			loginDTO.setUsuarioLogin(rs.getString("usuarioLogin"));
			loginDTO.setNomeLogin(rs.getString("nomeLogin"));
			loginDTO.setTipousuarioLogin(rs.getString("tipousuarioLogin"));
			loginDTO.setDtanascLogin(rs.getString("dtanascLogin"));
			loginDTO.setCpfLogin(rs.getString("cpfLogin"));
			loginDTO.setSenhaLogin(rs.getString("senhaLogin"));
			loginDTO.setIdLogin(id);

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!", "Dialog", JOptionPane.ERROR_MESSAGE);
		}
		return loginDTO;
	}
}
