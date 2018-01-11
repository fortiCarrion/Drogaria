package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into fabricante ");
		sql.append("(descricao) ");
		sql.append("values (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, f.getDescricao());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from fabricante ");
		sql.append("where codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setLong(1, f.getCodigo());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();

	}

	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update fabricante ");
		sql.append("set descricao = ? ");
		sql.append("where codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, f.getDescricao());
		stmt.setLong(2, f.getCodigo());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();

	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao ");
		sql.append("from fabricante ");
		sql.append("where codigo = ?;");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setLong(1, f.getCodigo());

		ResultSet result = stmt.executeQuery();

		Fabricante retorno = null;

		if (result.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(result.getLong("codigo"));
			retorno.setDescricao(result.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select  codigo, descricao ");
		sql.append("from fabricante ");
		sql.append("order by descricao asc;");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select  codigo, descricao ");
		sql.append("from fabricante ");
		sql.append("where descricao like ? ");
		sql.append("order by descricao asc;");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);

		}
		return lista;
	}

	public static void main(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("something");
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("something 2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f1); fdao.salvar(f2); System.out.println("sucess"); } catch
		 * (SQLException e) { e.printStackTrace(); System.out.println("not sucess"); }
		 *
		 * -----
		 *
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(2L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.excluir(f1); fdao.excluir(f2);
		 * 
		 * System.out.println("sucess"); } catch (SQLException e) { e.printStackTrace();
		 * System.out.println("not sucess"); }
		 *
		 * ----------
		 *
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L); Fabricante f2 = new
		 * Fabricante(); f2.setCodigo(2L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.buscarPorCodigo(f1); Fabricante f4 =
		 * fdao.buscarPorCodigo(f2);
		 * 
		 * System.out.println("result 1: " + f3); System.out.println("result 2: " + f4);
		 * } catch (SQLException e) { e.printStackTrace(); }
		 */

		FabricanteDAO fdao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = fdao.listar();

			for (Fabricante f : lista) {
				System.out.println(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("not sucess");
		}

	}
}
