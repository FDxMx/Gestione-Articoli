package it.gestionearticoli.service.utente;

import java.sql.Connection;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public class UtenteServiceImpl implements UtenteService {
	
	private UtenteDAO utenteDao;

	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	@Override
	public Utente cercaUtente(String username, String password) throws Exception {
		Utente utente;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			utenteDao.setConnection(connection);
			utente = utenteDao.findUser(username, password);
			if(utente.getUsername() == null || utente.getPassword() == null) {
				utente = null;
			}
		}
		return utente;
	}

}
