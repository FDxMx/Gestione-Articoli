package it.gestionearticoli.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	private int idCategoria;
	private String descrizione;
	private List<Articolo> listaArticoli = new ArrayList<>();

	public Categoria() {
	}
	
	public Categoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Categoria(int idCategoria, String descrizione) {
		super();
		this.idCategoria = idCategoria;
		this.descrizione = descrizione;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

	public List<Articolo> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<Articolo> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}

	@Override
	public String toString() {
		return descrizione;
	}

}
