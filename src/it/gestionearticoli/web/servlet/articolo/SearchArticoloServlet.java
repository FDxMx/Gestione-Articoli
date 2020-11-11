package it.gestionearticoli.web.servlet.articolo;

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
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class SearchArticoloServlet
 */
@WebServlet("/SearchArticoloServlet")
public class SearchArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchArticoloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Utente utente = (Utente) session.getAttribute("utenteAttribute");

		if (utente == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		CategoriaService catService = MyServiceFactory.getCategoriaServiceInstance();
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();

		String codiceInput = request.getParameter("codice");
		String descrizioneInput = request.getParameter("descrizione");
		String prezzoInput = request.getParameter("prezzo");
		String categoriaInput = request.getParameter("categoria");

		Integer prezzo = !prezzoInput.isEmpty() ? Integer.parseInt(prezzoInput) : null;
		Categoria categoriaInstance = new Categoria();
		try {
			categoriaInstance = catService.findById(Long.parseLong(categoriaInput));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Articolo articoloInput = new Articolo(codiceInput, descrizioneInput, prezzo, categoriaInstance);
		List<Articolo> listaArticoli = new ArrayList<>();
		try {
			listaArticoli = service.findByExample(articoloInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaArticoli", listaArticoli);
		request.getRequestDispatcher("risultatiRicercaArticolo.jsp").forward(request, response);
	}

}
