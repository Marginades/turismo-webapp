package persistence.commons;

import persistence.UsuarioDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static AtraccionDAOImpl getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
}
