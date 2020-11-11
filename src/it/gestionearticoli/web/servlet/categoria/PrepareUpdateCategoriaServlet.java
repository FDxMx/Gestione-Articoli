package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
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
 * Servlet implementation class PrepareUpdateCategoriaServlet
 */
@WebServlet("/PrepareUpdateCategoriaServlet")
public class PrepareUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareUpdateCategoriaServlet() {
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
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		Categoria categoria;
		try {
			if (parametroId != null && !parametroId.isEmpty()) {
				categoria = service.findById(Long.parseLong(parametroId));
				request.setAttribute("idCategoria", categoria.getIdCategoria());
				request.setAttribute("descrizione", categoria.getDescrizione());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("updateCategoria.jsp").forward(request, response);
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
