package controller.atraccion;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comprable;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.CompraService;

@WebServlet("/atraccion/buy.do")
public class ComprarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private CompraService compraService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.compraService = new CompraService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Comprable compra = DAOFactory.getPromocionDAO().find(attractionId);
		
		if (compra.esPromocion()) {
			
		
			try {
			Map<String, String>	errors = compraService.comprarPromocion(user.getId(), attractionId);
			
			Usuario user2 = DAOFactory.getUsuarioDAO().find(user.getId());
			req.getSession().setAttribute("user", user2);
			
			if (errors.isEmpty()) {
				req.setAttribute("flash", "¡Gracias por comprar!");
			} else {
				req.setAttribute("errors", errors);
				req.setAttribute("flash", "No ha podido realizarse la compra");
			}

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/atraccion/index.do");
			dispatcher.forward(req, resp);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		else {
			
			Map<String, String> errors = compraService.comprarAtraccion(user.getId(), attractionId);
			Usuario user2 = DAOFactory.getUsuarioDAO().find(user.getId());
			req.getSession().setAttribute("user", user2);
			
			if (errors.isEmpty()) {
				req.setAttribute("flash", "¡Gracias por comprar!");
			} else {
				req.setAttribute("errors", errors);
				req.setAttribute("flash", "No ha podido realizarse la compra");
			}

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/atraccion/index.do");
			dispatcher.forward(req, resp);
		}
		
	}
}
