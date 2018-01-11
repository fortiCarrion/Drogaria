package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into produto ");
		sql.append("(descricao,preco,quantidade,fabricante_codigo) ");
		sql.append("values (?, ?, ?, ?);");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setString(1, p.getDescricao());
		stmt.setDouble(2, p.getPreco());
		stmt.setLong(3, p.getQuantidade());
		stmt.setLong(4, p.getFabricante().getCodigo());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();

	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.codigo, p.descricao,p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("from fabricante f ");
		sql.append("inner join produto p on(f.codigo = p.fabricante_codigo);");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<Produto> itens = new ArrayList<Produto>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			Produto p = new Produto();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFabricante(f);

			itens.add(p);
		}
		stmt.close();
		conexao.close();

		return itens;
	}

	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from produto ");
		sql.append("where codigo = ? ;");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setLong(1, p.getCodigo());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();

	}

	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update produto ");
		sql.append("set descricao = ?, preco = ?, quantidade = ?, fabricante_codigo = ? ");
		sql.append("where codigo = ? ;");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, p.getDescricao());
		stmt.setDouble(2, p.getPreco());
		stmt.setLong(3, p.getQuantidade());
		stmt.setLong(4, p.getFabricante().getCodigo());

		stmt.executeUpdate();

		stmt.close();
		conexao.close();

	}
}
