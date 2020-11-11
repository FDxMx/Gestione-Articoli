package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class ConfermaDeleteCategoriaServlet
 */
@WebServlet("/ConfermaDeleteCategoriaServlet")
public class ConfermaDeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfermaDeleteCategoriaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Utente utente = (Utente) session.getAttribute("utenteAttribute");

		if (utente == null || utente.getRuolo().equals("GUEST") || utente.getRuolo().equals("OPERATORE")) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		String parametro = request.getParameter("idCategoria");
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria result = new Categoria();
		try {
			if (parametro != null && !parametro.isEmpty()) {
				result.setIdCategoria(Integer.parseInt(parametro));
				service.rimuovi(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "Attenzione non è possibile eliminare!");
			request.getRequestDispatcher("ListCategorieServlet").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ListCategorieServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
