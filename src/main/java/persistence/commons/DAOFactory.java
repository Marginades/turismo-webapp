package persistence.commons;

import persistence.PromocionDAO;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {
	
	public static UsuarioDAOImpl getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static AtraccionDAOImpl getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
}
