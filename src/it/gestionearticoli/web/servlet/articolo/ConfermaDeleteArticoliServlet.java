package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

/**
 * Servlet implementation class ConfermaDeleteArticoliServlet
 */
@WebServlet("/ConfermaDeleteArticoliServlet")
public class ConfermaDeleteArticoliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfermaDeleteArticoliServlet() {
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

		String parametro = request.getParameter("id");
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		Articolo result = new Articolo();

		if (utente == null || utente.getRuolo().equals("GUEST") || utente.getRuolo().equals("OPERATORE")) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		try {
			if (parametro != null && !parametro.isEmpty()) {
				result.setId(Long.parseLong(parametro));
				service.rimuovi(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("ListArticoliServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
