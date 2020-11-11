package it.gestionearticoli.dao.utente;

import java.sql.Connection;

import it.gestionearticoli.model.Utente;

public interface UtenteDAO {
	
	public Utente findUser(String username, String password) throws Exception;

	public void setConnection(Connection connection);

}
