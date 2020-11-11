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
 * Servlet implementation class ExecuteUpdateArticoloServlet
 */
@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateArticoloServlet() {
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
		
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			String codiceInputParam = request.getParameter("codice");
			String descrizioneInputParam = request.getParameter("descrizione");
			Integer prezzoInputParam = Integer.parseInt(request.getParameter("prezzo"));
			
			if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzoInputParam < 1) {
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("update.jsp").forward(request, response);
				return;
			}
			
			Articolo articolo = new Articolo(codiceInputParam, descrizioneInputParam, prezzoInputParam);
			articolo.setId(id);
			service.aggiorna(articolo);
			
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().selezionaTutti());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

}
