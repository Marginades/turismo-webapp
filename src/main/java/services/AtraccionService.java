package services;

import java.util.List;

import model.Atraccion;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AtraccionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}
	
	public List<Atraccion>listarPorTipo(String tipo){
		return DAOFactory.getAtraccionDAO().buscarPorTipo(tipo);
	}
	
	public Atraccion create(String name, String descripcion, Integer costo, Double duracion, Integer cupoMaximo, String tipo, Boolean active) {

		Atraccion attraction = new Atraccion(-1, name, descripcion, costo, duracion, cupoMaximo, tipo, active);

		if (attraction.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Atraccion update(Integer id, String nombre, String descripcion, Integer costo, Double duracion, Integer cupoMaximo, String tipo, Boolean active) {

		AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = attractionDAO.find(id);

		atraccion.setNombre(nombre);
		atraccion.setDescripcion(descripcion);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);;
		atraccion.setCupo(cupoMaximo);
		atraccion.setTipo(tipo);
		atraccion.setActive(active);

		if (atraccion.isValid()) {
			attractionDAO.update(atraccion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atraccion;
	}

	public void delete(Integer id) {
		Atraccion atraccion = new Atraccion(id, null, null, null, null, null, null, null);
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion find(Integer id) {
		AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
		return attractionDAO.find(id);
	}

}
