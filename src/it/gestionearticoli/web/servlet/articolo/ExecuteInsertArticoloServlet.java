package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;  

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

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Utente utente = (Utente)session.getAttribute("utenteAttribute");

		if (utente == null || utente.getRuolo().equals("GUEST")) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String categoriaInputParam = request.getParameter("categoria");
		Integer prezzo = !prezzoInputStringParam.isEmpty() ? Integer.parseInt(prezzoInputStringParam) : 0;
		
		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1 || categoriaInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}
		Categoria categoriaInstance = new Categoria(Integer.parseInt(categoriaInputParam));
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoriaInstance);
		try {
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().selezionaTutti());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("results.jsp").forward(request, response);

	}

}
