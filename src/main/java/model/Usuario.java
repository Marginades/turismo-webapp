package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.Crypt;

public class Usuario {
	private Integer id_usuario;
	private String username, password;
	private int presupuesto;
	private double disponibilidad;
	private String preferencia;
	private Boolean admin;
	private Boolean active;
	private List<Comprable> itinerario;
	private HashMap<String, String> errors;

	public Usuario(int id_usuario, String username, String password, int presupuesto, double disponibilidad,
			String preferencia, Boolean admin) {
		this.id_usuario = id_usuario;
		this.username = username;
		this.password = password;
		this.presupuesto = presupuesto;
		this.disponibilidad = disponibilidad;
		this.preferencia = preferencia;
		this.admin = admin;
		this.active = true;
		this.itinerario = new LinkedList<Comprable>();
	}

	public void comprar(Comprable producto) throws Exception {
		if (this.puedeComprar(producto)) {
			this.presupuesto -= producto.getCosto();
			this.disponibilidad -= producto.getDuracion();
			producto.comprarLugar();
			this.agregarAlItinerario(producto);
		}
	}

	private void agregarAlItinerario(Comprable producto) {
		if (producto.esPromocion()) {
			this.itinerario.addAll(((Promocion) producto).getAtracciones());
		} else if (!producto.esPromocion()) {
			this.itinerario.add(producto);
		}
	}

	public boolean puedeComprar(Comprable producto) {
		return this.presupuesto >= producto.getCosto() && this.disponibilidad >= producto.getDuracion()
				&& !this.yaCompro(producto);
	}
	public void addToItinerary(Atraccion attraction) {
		this.presupuesto -= attraction.getCosto();
		this.disponibilidad -= attraction.getDuracion();
		// TODO agregar a su lista
	}

	public boolean canAfford(Atraccion attraction) {
		return attraction.getCosto() <= this.presupuesto;
	}

	public boolean canAttend(Atraccion attraction) {
		return attraction.getDuracion() <= this.disponibilidad;
	}


	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public boolean yaCompro(Comprable producto) {
		boolean contiene = false;
		if (producto.esPromocion()) {
			for (Comprable atraccion : ((Promocion) producto).getAtracciones()) {
				if (this.itinerario.contains(atraccion)) {
					contiene = true;
				}
			}

		} else if (!producto.esPromocion()) {
			contiene = this.itinerario.contains(producto);
		}
		return contiene;
	}

	public boolean estaEnCero() {
		return this.disponibilidad == 0 || this.presupuesto == 0;
	}

	/*
	 * VIEJO METODO TO STRING
	 * 
	 * 
	 * @Override public String toString() { String excursiones = ""; Double duracion
	 * = 0.0; int gastado = 0;
	 * 
	 * for (Comprable p : itinerario) { excursiones += p.getNombre() + "\n";
	 * duracion += p.getDuracion(); gastado += p.getCosto(); }
	 * 
	 * return "Itinerario de " + this.username + ":\n" + excursiones +
	 * "\nTiempo total: " + duracion + "\nGasto: " + gastado; }
	 */

	public Integer getId() {
		return id_usuario;
	}

	public void setId(Integer id) {
		this.id_usuario = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public Integer getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Integer presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Double getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Double time) {
		this.disponibilidad = time;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (presupuesto < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (disponibilidad < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}
	
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id_usuario + ", username=" + username + ", password=" + password + ", admin=" + admin
				+ "]";
	}

}