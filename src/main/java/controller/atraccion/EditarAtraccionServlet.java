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

@WebServlet("/atraccion/edit.do")
public class EditarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Atraccion atraccion = atraccionService.find(id);
		req.setAttribute("attraction", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Integer costo = req.getParameter("costo").trim() == "" ? null : Integer.parseInt(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupoMaximo = Integer.parseInt(req.getParameter("cupoMaximo"));
		String tipo = req.getParameter("tipo");
		Boolean active = Integer.parseInt(req.getParameter("active")) == 1;

		Atraccion atraccion = atraccionService.update(id, nombre, descripcion, costo, duracion, cupoMaximo, tipo, active);

		if (atraccion.isValid()) {
			resp.sendRedirect("/turismo-webapp/atraccion/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
