package it.gestionearticoli.service.categoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.categoria.CategoriaDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {
	
	private CategoriaDAO categoriaDao;

	@Override
	public void setCategoriaDao(CategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	@Override
	public List<Categoria> selezionaTutti() throws Exception {
		List<Categoria> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			categoriaDao.setConnection(connection);
			result = categoriaDao.selectAll();
		}
		return result;
	}

	@Override
	public Categoria findById(Long idInput) throws Exception {
		Categoria categoria = new Categoria();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			categoriaDao.setConnection(connection);
			categoria = categoriaDao.get(idInput);
		}
		return categoria;
	}

	@Override
	public int aggiorna(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)){
			categoriaDao.setConnection(connection);
			result = categoriaDao.update(input);
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			categoriaDao.setConnection(connection);
			result = categoriaDao.insert(input);
		}
		return result;
	}

	@Override
	public int rimuovi(Categoria input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			categoriaDao.setConnection(connection);
			result = categoriaDao.delete(input);
		}
		return result;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		List<Categoria> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			categoriaDao.setConnection(connection);
			result = categoriaDao.findByExample(input);
		}
		return result;
	}

}
