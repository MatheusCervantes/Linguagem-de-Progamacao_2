package br.com.hospital.ctr;

import java.sql.*;

public class Conexao {
	private static Conexao instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/hospital.db";
	private static Connection conexao;

	private Conexao() {

	}

	public static Conexao getInstancia() {
		if (instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}

	public Connection abrirConexao() {
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(BD);
			conexao.setAutoCommit(true);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao conectar no banco: " + e.getMessage());
		}
		return conexao;
	}

	public void fecharConexao() {
		try {
			if (conexao != null && conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao fechar o banco: " + e.getMessage());
		} finally {
			conexao = null;
		}
	}
}
