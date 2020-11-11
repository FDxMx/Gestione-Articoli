package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class ShowArticoliPerCategoriaServlet
 */
@WebServlet("/ShowArticoliPerCategoriaServlet")
public class ShowArticoliPerCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowArticoliPerCategoriaServlet() {
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

		if (session.getAttribute("utenteAttribute") == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		String parametroId = request.getParameter("idParametro");
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService catService = MyServiceFactory.getCategoriaServiceInstance();
		List<Articolo> result = new ArrayList<>();
		Categoria input = new Categoria();
		try {
			if (parametroId != null && !parametroId.isEmpty()) {
				input = catService.findById(Long.parseLong(parametroId));
				result = service.articoliDaCategoria(input);
				input.setListaArticoli(result);
				request.setAttribute("categoria", input);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("showArticoliByCategoria.jsp").forward(request, response);
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
