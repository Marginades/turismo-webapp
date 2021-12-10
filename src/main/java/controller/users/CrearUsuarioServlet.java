package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UsuarioService;

@WebServlet("/usuario/create.do")
public class CrearUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/usuario/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Integer presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
		Double disponibilidad = Double.parseDouble(req.getParameter("disponibilidad"));
		String preferencia = req.getParameter("preferencia");
		Boolean admin = Boolean.valueOf("admin");
		
		Usuario tmp_user = usuarioService.create(username, password, presupuesto, disponibilidad, preferencia, admin);
		
		if (tmp_user.isValid()) {
			resp.sendRedirect("/turismo-webapp/usuario/index.do");
		} else {
			req.setAttribute("tmp_user", tmp_user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/usuario/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
