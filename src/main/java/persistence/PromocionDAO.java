package persistence;

import java.util.List;

import model.Comprable;
import model.Promocion;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public abstract List<Comprable> generadorDeAtracciones(String atracciones_promo);
	
}
