package it.gestionearticoli.dao.articolo;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> selectAll() throws Exception {
		if (isNotActive()) {
			return null;
		}
		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select a.*, c.descrizione as descrCat from articolo a left join categoria c on c.id_categoria = a.id_categoria_fk;");
			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				articoloTemp.setCategoria(new Categoria(rs.getInt("id_categoria_fk"), rs.getString("descrCat")));
				result.add(articoloTemp);
			}
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive()) {
			return null;
		}
		Articolo articolo = new Articolo();
		String query = "select a.*, c.descrizione as descrCat from articolo a left join categoria c on c.id_categoria = a.id_categoria_fk where a.id = ?;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				articolo.setId(resultSet.getLong("id"));
				articolo.setCodice(resultSet.getString("codice"));
				articolo.setDescrizione(resultSet.getString("descrizione"));
				articolo.setPrezzo(resultSet.getInt("prezzo"));
				articolo.setCategoria(new Categoria(resultSet.getInt("id_categoria_fk"), resultSet.getString("descrCat")));
			}
		}
		return articolo;
	}
	
	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		String query = "UPDATE articolo SET codice = ?, descrizione = ?, prezzo = ? WHERE id = ?"; //id_categoria_fk = ?
		int result = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, input.getCodice());
			preparedStatement.setString(2, input.getDescrizione());
			preparedStatement.setInt(3, input.getPrezzo());
//			preparedStatement.setInt(4, input.getCategoria().getIdCategoria());
			preparedStatement.setLong(4, input.getId());
			result = preparedStatement.executeUpdate();
		}
		return result;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		int result = 0;
		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO articolo (codice, descrizione, prezzo, id_categoria_fk) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setInt(4, input.getCategoria().getIdCategoria());
			result = ps.executeUpdate();
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive()) {
			return -1;
		}
		String query = "DELETE FROM articolo WHERE id = ?";
		int result = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());
			result = preparedStatement.executeUpdate();
		}
		return result;
	}
	
	public List<Articolo> articoliByCategoria(Categoria input) throws Exception{
		if (isNotActive()) {
			return null;
		}
		String query = "select a.* from articolo a where a.id_categoria_fk = ?;";
		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, input.getIdCategoria());
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				result.add(articoloTemp);
			}
		}
		return result;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return null;
		}
		String query = "select a.*, c.descrizione as descrCat from articolo a left join categoria c on c.id_categoria = a.id_categoria_fk where 1=1 ";
		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;
		
		if(input.getCodice() != null && !input.getCodice().equals("")){
			query += " and a.codice like '%" + input.getCodice() + "%' ";
		}
		
		if(input.getDescrizione() != null && !input.getDescrizione().equals("")) {
			query += " and a.descrizione like '%" + input.getDescrizione() + "%' ";
		}
		
		if(input.getPrezzo() != null) {
			query += " and a.prezzo like '%" + input.getPrezzo() + "%' ";
		}
		
		if(input.getCategoria() != null && input.getCategoria().getIdCategoria() != 0) {
			query += " and a.id_categoria_fk like '%" + input.getCategoria().getIdCategoria() + "%' ";
		}
	
		try (Statement s = connection.createStatement()) {
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("CODICE"));
				articoloTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				articoloTemp.setPrezzo(rs.getInt("PREZZO"));
				articoloTemp.setId(rs.getLong("ID"));
				articoloTemp.setCategoria(new Categoria(rs.getInt("id_categoria_fk"), rs.getString("descrCat")));
				result.add(articoloTemp);
			}
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

}
