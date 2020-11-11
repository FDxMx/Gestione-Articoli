package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO implements UtenteDAO{

	@Override
	public Utente findUser(String username, String password) throws Exception{
		
		if (isNotActive()) {
			return null;
		}
		String query = "select * from utente where utente.username = ? and utente.password = ?;";
		Utente utente = new Utente();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setRuolo(rs.getString("ruolo"));
			}
		}
		return utente;
	}
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

}
