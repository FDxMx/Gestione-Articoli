package it.gestionearticoli.service.articolo;

import java.sql.Connection; 
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.articolo.ArticoloDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDao;

	public void setArticoloDao(ArticoloDAO articoloDao) {
		this.articoloDao = articoloDao;
	}

	@Override
	public List<Articolo> selezionaTutti() throws Exception {
		List<Articolo> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// inietto la  connection nel dao
			articoloDao.setConnection(connection);
			// eseguo quello che realmente devo fare
			result = articoloDao.selectAll();
		}
		return result;
	}

	@Override
	public Articolo findById(Long idInput) throws Exception {
		Articolo articolo = new Articolo();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			articoloDao.setConnection(connection);
			articolo = articoloDao.get(idInput);
		}
		return articolo;
	}

	@Override
	public int aggiorna(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			articoloDao.setConnection(connection);
			result = articoloDao.update(input);
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			// inietto la connection nel dao
			articoloDao.setConnection(connection);
			// eseguo quello che realmente devo fare
			result = articoloDao.insert(input);
		}
		return result;
	}

	@Override
	public int rimuovi(Articolo input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			articoloDao.setConnection(connection);
			result = articoloDao.delete(input);
		}
		return result;
	}
	
	public List<Articolo> articoliDaCategoria(Categoria input) throws Exception{
		List<Articolo> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			articoloDao.setConnection(connection);
			result = articoloDao.articoliByCategoria(input);
		}
		return result;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		List<Articolo> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			articoloDao.setConnection(connection);
			result = articoloDao.findByExample(input);
		}
		return result;
	}

}
