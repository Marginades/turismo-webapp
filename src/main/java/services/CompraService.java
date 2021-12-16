package services;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class CompraService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

	public Map<String, String> comprarAtraccion(Integer userId, Integer attractionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = usuarioDAO.find(userId);
		Atraccion attraction = atraccionDAO.find(attractionId);

		if (!attraction.canHost(1)) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!user.canAfford(attraction)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(attraction)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(attraction);
			attraction.host(1);

			// no grabamos para no afectar la base de pruebas
			atraccionDAO.update(attraction);
			usuarioDAO.update(user);
		}

		return errors;

	}

	public Map<String, String> comprarPromocion(Integer userId, Integer promocionId) throws Exception {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario user = usuarioDAO.find(userId);
		Promocion promocion = promocionDAO.find(promocionId);

		if (!promocion.hayCupo()) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!user.canAfford(promocion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(promocion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(promocion);
			promocion.comprarLugar();

			// no grabamos para no afectar la base de pruebas
			promocionDAO.update(promocion);
			usuarioDAO.update(user);
		}

		return errors;

	}
}