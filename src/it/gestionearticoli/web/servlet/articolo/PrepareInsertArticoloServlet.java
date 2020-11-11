package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/PrepareInsertArticoloServlet")
public class PrepareInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareInsertArticoloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Utente utente = (Utente)session.getAttribute("utenteAttribute");

		if (utente == null || utente.getRuolo().equals("GUEST")) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		try {
			request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().selezionaTutti());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("insert.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
