package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.utente.UtenteService;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Utente utente;
		try {
			utente = service.cercaUtente(username, password);
			if (utente == null || username.equals("") || password.equals("") || username == null || password == null) {
				response.sendRedirect(request.getContextPath());
			} else {
				session.setAttribute("utenteAttribute", utente);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
