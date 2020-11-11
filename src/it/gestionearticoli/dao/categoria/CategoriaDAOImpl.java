package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO{

	@Override
	public List<Categoria> selectAll() throws Exception {
		if (isNotActive()) {
			return null;
		}
		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria");
			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setDescrizione(rs.getString("descrizione"));
				categoriaTemp.setIdCategoria(rs.getInt("id_categoria"));
				result.add(categoriaTemp);
			}
		}
		
		return result;
	}

	@Override
	public Categoria get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		Categoria categoria = new Categoria();
		String query = "select c.* from categoria c where c.id_categoria = ?;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				categoria.setIdCategoria(resultSet.getInt("id_categoria"));
				categoria.setDescrizione(resultSet.getString("descrizione"));
			}
		}
		return categoria;
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		String query = "UPDATE categoria SET descrizione = ? WHERE id_categoria = ?";
		int result = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, input.getDescrizione());
			preparedStatement.setInt(2, input.getIdCategoria());
			result = preparedStatement.executeUpdate();
		}
		return result;
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		int result = 0;
		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO categoria (descrizione) VALUES (?);")) {
			ps.setString(1, input.getDescrizione());
			result = ps.executeUpdate();
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {
		if (isNotActive()) {
			return -1;
		}
		String query = "DELETE FROM categoria WHERE id_categoria = ?";
		int result = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getIdCategoria());
			result = preparedStatement.executeUpdate();
		}
		return result;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return null;
		}
		String query = "select c.* from categoria c where 1=1 ";
		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;
		
		if(input.getDescrizione() != null && !input.getDescrizione().equals("")) {
			query += " and c.descrizione like '%" + input.getDescrizione() + "%' ";
		}
		
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setDescrizione(rs.getString("descrizione"));
				categoriaTemp.setIdCategoria(rs.getInt("id_categoria"));
				result.add(categoriaTemp);
			}
		}
		
		return result;
	}
	

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;		
	}

}
