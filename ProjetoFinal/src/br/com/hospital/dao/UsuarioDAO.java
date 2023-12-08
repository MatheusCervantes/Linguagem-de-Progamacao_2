package br.com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.hospital.dao.*;
import br.com.hospital.ctr.Conexao;
import br.com.hospital.model.UsuarioDTO;

public class UsuarioDAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/hospital.db";

	private static String CONSULTAR_USUARIO = "SELECT idlogin, usuarioLogin, senhaLogin, tipousuarioLogin FROM Login WHERE usuarioLogin = ? AND senhaLogin = ?";

	public UsuarioDAO() {

	}

	public static UsuarioDTO consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		UsuarioDTO usuario = null;
		String query = CONSULTAR_USUARIO;

		try {
			preparedStatement = connection.prepareStatement(query);

			int i = 1;

			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, senhaCriptografada);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				usuario = new UsuarioDTO(resultSet.getInt("idlogin"), resultSet.getString("usuarioLogin"),
						resultSet.getString("senhaLogin"), resultSet.getString("tipousuarioLogin"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			fecharConexao();
		}
		Conexao.getInstancia().fecharConexao();
		return usuario;
	}

	private static void fecharConexao() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
