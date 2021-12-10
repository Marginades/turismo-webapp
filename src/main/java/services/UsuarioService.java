package services;

import java.util.List;

import model.Usuario;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario create(String username, String password, Integer presupuesto, Double disponibilidad, String preferencia, Boolean admin) {
		Usuario user = new Usuario(-1, username, password, presupuesto, disponibilidad, preferencia, false);
		user.setPassword(password);

		if (user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}
}
