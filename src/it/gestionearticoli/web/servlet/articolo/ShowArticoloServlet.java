package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

@WebServlet("/ShowArticoloServlet")
public class ShowArticoloServlet extends HttpServlet {

	public ShowArticoloServlet() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		if (session.getAttribute("utenteAttribute") == null) {
			resp.sendRedirect(req.getContextPath());
			return;
		}

		String parametroId = req.getParameter("idParametro");
		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();
		Articolo result;
		try {
			if (parametroId != null && !parametroId.isEmpty()) {
				result = service.findById(Long.parseLong(parametroId));
				req.setAttribute("articoloParam", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("show.jsp").forward(req, resp);
	}

}
