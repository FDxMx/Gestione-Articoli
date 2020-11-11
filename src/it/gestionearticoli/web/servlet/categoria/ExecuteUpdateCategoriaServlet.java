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
 * Servlet implementation class ExecuteUpdateCategoriaServlet
 */
@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateCategoriaServlet() {
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

		Utente utente = (Utente)session.getAttribute("utenteAttribute");

		if (utente == null || utente.getRuolo().equals("GUEST")) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();
		try {
			int id = Integer.parseInt(request.getParameter("idCategoria"));
			String descrizioneInputParam = request.getParameter("descrizione");
			
			if (descrizioneInputParam.isEmpty() || id < 1) {
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("insertCategoria.jsp").forward(request, response);
				return;
			}
			
			Categoria categoria = new Categoria(descrizioneInputParam);
			categoria.setIdCategoria(id);
			service.aggiorna(categoria);
			
			request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().selezionaTutti());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("resultsCategorie.jsp").forward(request, response);

	}

}
