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

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class SearchCategoriaServlet
 */
@WebServlet("/SearchCategoriaServlet")
public class SearchCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCategoriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Utente utente = (Utente) session.getAttribute("utenteAttribute");

		if (utente == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		String descrizioneInput = request.getParameter("descrizione");
		Categoria categoriaInput = new Categoria(descrizioneInput);
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		List<Categoria> listaCategorie = new ArrayList<>();
		try {
			listaCategorie = service.findByExample(categoriaInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaCategorie", listaCategorie);
		request.getRequestDispatcher("risultatiRicercaCategoria.jsp").forward(request, response);
	}

}
