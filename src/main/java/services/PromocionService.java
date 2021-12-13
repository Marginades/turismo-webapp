package services;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromocionService {
	
	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}
	

	
	public Promocion create(String nombre, String tipo_promociones, String descripcion, String tipo_atracciones, String atracciones_promo, Double descuento, Boolean active) {
				
			return this.constructorPromociones(nombre, tipo_promociones, descripcion, tipo_atracciones, atracciones_promo, descuento, active);
			
			}

	public Promocion update(Integer id, String nombre, String tipo_promociones, String descripcion, String tipo_atracciones, String atracciones_promo, Double descuento, Boolean active) {
		

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);	
		PromocionAbsoluta promo1 = null;
		PromocionAxB promo2 = null;
		PromocionPorcentual promo3 = null;
		
		if (promocion.getTipo_promociones().equals("ABS")) {
			
			promo1 = (PromocionAbsoluta)promocionDAO.find(id);
			promo1.setNombre(nombre);
			promo1.setTipo_promociones(tipo_promociones);
			promo1.setDescripcion(descripcion);
			promo1.setTipo_atracciones(tipo_atracciones);
			promo1.setAtracciones_promo(atracciones_promo);
			promo1.setDescuento(descuento);
			promo1.setActive(active);
			
			promocion = promo1;
			
		}
		
		if (promocion.getTipo_promociones().equals("AXB")) {
			
			promo2 = (PromocionAxB)promocionDAO.find(id);
			promo2.setNombre(nombre);
			promo2.setTipo_promociones(tipo_promociones);
			promo2.setDescripcion(descripcion);
			promo2.setTipo_atracciones(tipo_atracciones);
			promo2.setAtracciones_promo(atracciones_promo);
			promo2.setActive(active);
			
			promocion = promo2;
			
		}
		
		if (promocion.getTipo_promociones().equals("POR")) {
			
			promo3 = (PromocionPorcentual)promocionDAO.find(id);
			promo3.setNombre(nombre);
			promo3.setTipo_promociones(tipo_promociones);
			promo3.setDescripcion(descripcion);
			promo3.setTipo_atracciones(tipo_atracciones);
			promo3.setAtracciones_promo(atracciones_promo);
			promo3.setDescuento(descuento);
			promo3.setActive(active);
			
			promocion = promo3;
			
		}
		
		return promocion;
	}

	public Promocion constructorPromociones(String nombre, String tipo_promociones, String descripcion, String tipo_atracciones, String atracciones_promo, Double descuento, Boolean active) {
	
		Promocion promocion = null;	
		
		if (tipo_promociones.equals("ABS")) {
			
				promocion = new PromocionAbsoluta (-1, nombre, tipo_promociones, descripcion, tipo_atracciones, descuento, atracciones_promo, active );
		
		}
		
		if (tipo_promociones.equals("AXB")) {
			
			promocion = new PromocionAxB (-1, nombre, tipo_promociones, descripcion, tipo_atracciones, atracciones_promo, active );
				
				}
		
		if (tipo_promociones.equals("POR")) {
			
			promocion = new PromocionPorcentual (-1, nombre, tipo_promociones, descripcion, tipo_atracciones, descuento, atracciones_promo, active );
				
				}

	if (promocion.isValid()) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.insert(promocion);
		// XXX: si no devuelve "1", es que hubo más errores
	}

	return promocion;
}
	

	public void delete(Integer id) {
		Promocion promocion = 	this.constructorPromociones(null, null, null, null, null, null, null);
		promocion.setId(id);
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Atraccion find(Integer id) {
		AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		return attractionDAO.find(id);
	}

}


