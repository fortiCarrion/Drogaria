package br.com.drogaria.factory;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoFactory {
	private static final String DATABASE = "drogaria";
	private static final String USUARIO = "carrion";
	private static final String SENHA = "123";
	private static final String URL = "jdbc:mysql://localhost:3306/"  + DATABASE;

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection con = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
		return con;
	}

	public static void main(String[] args) {
		try {
			Connection con = ConexaoFactory.conectar();
			System.out.println("sucess");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("not sucess");
		}
	}
}
