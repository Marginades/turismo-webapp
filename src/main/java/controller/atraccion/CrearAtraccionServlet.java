package controller.atraccion;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AtraccionService;

@WebServlet("/atraccion/create.do")
public class CrearAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;

	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupoMaximo = Integer.parseInt(req.getParameter("cupoMaximo"));
		String tipo_atraccion = req.getParameter("tipo_atracciones");
		Boolean active = true;
		Atraccion atraccion = atraccionService.create(nombre, descripcion, costo, duracion, cupoMaximo, tipo_atraccion, active);
		if (atraccion.isValid()) {
			resp.sendRedirect("/turismo-webapp/atraccion/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/atraccion/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
