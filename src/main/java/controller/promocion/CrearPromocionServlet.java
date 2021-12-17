package controller.promocion;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/promocion/create.do")
public class CrearPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;

	private PromocionService promocionService;
	private AtraccionService atraccionService;
	private String tipo_Atraccion;
	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
		this.atraccionService = new AtraccionService();
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		tipo_Atraccion = req.getParameter("tipo_atracciones");
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		atracciones.addAll(atraccionService.listarPorTipo(tipo_Atraccion));
		req.setAttribute("atracciones", atracciones);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promocion/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		
		String nombre = req.getParameter("nombre");
		String tipo_promociones = req.getParameter("tipo_promociones");
		String descripcion = req.getParameter("descripcion");
		Double descuento = Double.valueOf(req.getParameter("descuentoInput"));
		String tipo_atracciones = tipo_Atraccion;
		String atracciones_promo = req.getParameter("atracciones_promo");
		Boolean active = true;
		Promocion promocion = promocionService.create(nombre, tipo_promociones,descripcion, tipo_atracciones, atracciones_promo, descuento, active);
		if (promocion.isValid()) {
			resp.sendRedirect("/turismo-webapp/atraccion/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promocion/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
	

}
