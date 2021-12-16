package controller.atraccion;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comprable;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/atraccion/index.do")
public class ListarComprableServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private AtraccionService atraccionService;
	private PromocionService promocionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Comprable> comprables = new LinkedList<Comprable>();
		comprables.addAll(promocionService.list());
		comprables.addAll(atraccionService.list());
		req.setAttribute("comprables", comprables);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atraccion/index.jsp");
		dispatcher.forward(req, resp);

	}

}
