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
 * Servlet implementation class PrepareUpdateArticoloServlet
 */
@WebServlet("/PrepareUpdateArticoloServlet")
public class PrepareUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareUpdateArticoloServlet() {
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

		if (utente == null || utente.getRuolo().equals("GUEST")) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		String parametroId = request.getParameter("idParametro");
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		Articolo result;
		try {
			if (parametroId != null && !parametroId.isEmpty()) {
				result = service.findById(Long.parseLong(parametroId));
				request.setAttribute("id", result.getId());
				request.setAttribute("codice", result.getCodice());
				request.setAttribute("descrizione", result.getDescrizione());
				request.setAttribute("prezzo", result.getPrezzo());
//				request.setAttribute("articoloParam", result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("update.jsp").forward(request, response);
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
