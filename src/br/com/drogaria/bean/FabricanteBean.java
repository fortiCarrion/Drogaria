package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.model.ListDataModel;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;


import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public void clearAllFilters() {

	    DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:tblFabricantes");
	    if (!dataTable.getFilters().isEmpty()) {
	        dataTable.reset();
	        
	        RequestContext requestContext = RequestContext.getCurrentInstance();
	        requestContext.update("form:tblFabricantes");
	    }
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.listar();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);

			itens = dao.listar();
			//itens = new ListDataModel<Fabricante>(lista);
			JSFUtil.adicionarMensagemSucesso("Fabricante salvo");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
/*
	public void prepararExcluir() {
		 getRowData sabe qual linha que foi "clickado", ou seja qual fabricante foi
		 clickado, getRowData só funciona no dataModel
		 fabricante = itens.getRowData();
		
	}
*/
	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);

			//ArrayList<Fabricante> lista = fdao.listar();
			//itens = new ListDataModel<Fabricante>(lista);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());

		}
	}
/*
	public void prepararEditar() {
		fabricante = itens.getRowData();
	}
*/
	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);

			//ArrayList<Fabricante> lista = fdao.listar();
			//itens = new ListDataModel<Fabricante>(lista);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}
}
