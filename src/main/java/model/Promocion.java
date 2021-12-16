package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistence.commons.DAOFactory;

public abstract class Promocion implements Comprable {
	protected int id_promo;
	protected String nombre;
	protected String tipo_promociones;
	protected String descripcion;
	protected String tipo_atracciones;
	protected Integer costoSinDescuento;
	protected Double duracion;
	protected List<Integer> cupoPromocion;
	protected String atracciones_promo;
	protected Boolean active;
	protected List<Comprable> atracciones;

	private Map<String, String> errors;
	
	public Promocion(int id_promo, String nombre, String descripcion, String tipo_promociones, String tipo_atracciones, 
			String atracciones_promo, Boolean active) {
		this.id_promo = id_promo;
		this.nombre = nombre;
		this.tipo_atracciones = tipo_atracciones;
		this.tipo_promociones = tipo_promociones;
		this.descripcion = descripcion;
		this.active = active;
		this.atracciones = DAOFactory.getPromocionDAO().generadorDeAtracciones(atracciones_promo);
		this.costoSinDescuento = calculadorDeCostoTotal(this.atracciones);
	}
	
	public Integer calculadorDeCostoTotal(List<Comprable> atraccionesDeLaPromo) {
			int costoTotal = 0;
		for (Comprable atraccion : atraccionesDeLaPromo) {
			costoTotal += atraccion.getCosto();
				
		}
		return costoTotal;
		}
	
	public String getAtraccionesPlanas() {
		return this.atracciones_promo;
	}
		
	
	public Double getDuracion() {
		Double duracionTotal =  0.0;
		for (Comprable atraccion : this.atracciones) {
			duracionTotal += atraccion.getDuracion();
		}
		return duracionTotal;
	}
	
	public int getId() {
		return this.id_promo;
	}
	
	public void setId (int id_promo) {
		this.id_promo = id_promo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public String getTipo_promociones() {
		return this.tipo_promociones;
	}
	
	
	public void setTipo_promociones(String tipo_promociones) {
		this.tipo_promociones = tipo_promociones;
	}
	
	public void setAtracciones_promo(String atracciones_promo) {
		this.atracciones_promo = atracciones_promo;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Double getDescuento() {
		return null;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getTipo_atracciones() {
		return this.tipo_atracciones;
	}
	
	public Boolean getActive() {
		return this.active;
	}
	public void setActive(Boolean active) {
		 this.active = active;
	}
	
	public void setTipo_atracciones(String tipo_atracciones) {
		this.tipo_atracciones = tipo_atracciones;
	
	}
	

	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (getCosto() <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (getDuracion() <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (this.atracciones.isEmpty()) {
			errors.put("capacity", "Debe tener atracciones");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	
	public List<Comprable> getAtracciones() {
		return this.atracciones;
	}




	public String getTipo_atraccion() {
		return tipo_atracciones;
	}

	public Boolean hayCupo() {
		for (Comprable a : this.atracciones) {
			if (!a.hayCupo()) {
				return false;
			}
		}
		return true;
	}
	

	public Integer getCupo() {
		Integer sumaDeCupos = 0;
		for (Comprable a : this.atracciones) {
			sumaDeCupos += a.getCupo();
			}
		
	
		return sumaDeCupos;
			
	}

	public Integer getCosto() {
		return costoSinDescuento;
	}

	public void setCosto(Integer costoSinDescuento) {
		this.costoSinDescuento = costoSinDescuento;
	}

	public Integer getEntradasVendidas() {
		int entradas = 0;
		for (Comprable atraccion : this.atracciones) {
			entradas += atraccion.getEntradasVendidas();
		}
		return entradas;
	}

	public void comprarLugar() throws Exception  {
		for (Comprable a : this.atracciones) {
			a.comprarLugar(); // este metodo ya captura la excepcion en la clase atraccion
		}
	}

	public Boolean esPromocion() {
		return true;
	}

	public Boolean esOContiene(Comprable atraccion) {
		for (Comprable a : this.atracciones) {
			if (atraccion.equals(a)) {
				return true;
			}
		}

		return false;
	}

	// Imprimir
	@Override
	public String toString() {
		return "Llev� " + this.getNombre() + " por " + this.getCosto() + " monedas de oro";
	}

}
