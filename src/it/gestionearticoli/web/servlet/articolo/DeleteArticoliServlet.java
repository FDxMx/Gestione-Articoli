package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;


@WebServlet("/DeleteArticoliServlet")
public class DeleteArticoliServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Utente utente = (Utente)session.getAttribute("utenteAttribute");

		if (utente == null || utente.getRuolo().equals("GUEST") || utente.getRuolo().equals("OPERATORE")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		String parametro = req.getParameter("idParametro");
		if(parametro != null && !parametro.isEmpty()) {
			req.setAttribute("id", parametro);
			req.getRequestDispatcher("delete.jsp").forward(req, resp);
		}
	}
}
