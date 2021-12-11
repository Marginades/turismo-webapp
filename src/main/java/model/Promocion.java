package model;

import java.util.List;

import persistence.commons.DAOFactory;

public abstract class Promocion implements Comprable {
	protected int id_promo;
	protected String nombre;
	protected String tipo_promocion;
	protected String descripcion;
	protected String tipo_atracciones;
	protected Integer costoSinDescuento;
	protected Double duracion;
	protected String atracciones_promo;
	protected Boolean active;
	protected List<Comprable> atracciones;

	
	public Promocion(int id_promo, String nombre, String tipo_promocion, String descripcion, String tipo_atracciones, 
			String atracciones_promo, Boolean active) {
		this.id_promo = id_promo;
		this.nombre = nombre;
		this.tipo_promocion = tipo_promocion;
		this.descripcion = descripcion;
		this.tipo_atracciones = tipo_atracciones;
		this.active = active;
		this.atracciones_promo = atracciones_promo;
		this.costoSinDescuento = calculadorDeCostoTotal(generadorDeAtracciones());
		
	}
	
	public List<Comprable> generadorDeAtracciones() {
		String listaTemporal[] = this.atracciones_promo.split("-");
		
		for (int i = 1; i <= listaTemporal.length; i++) {
			atracciones.add(DAOFactory.getAtraccionDAO().find(Integer.parseInt(listaTemporal[i])));
			}
		return atracciones;
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
	

	public String getTipo_promocion() {
		return this.tipo_promocion;
	}
	
	
	public void setTipo_promocion(String tipo_promocion) {
		this.tipo_promocion = tipo_promocion;
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
	
	public void setTipo_atracciones(String tipo_atracciones) {
		this.tipo_atracciones = tipo_atracciones;
	}
	

	public Double getDuracion() {
		double duracion = 0;
		for (Comprable a : this.atracciones) {
			duracion += a.getDuracion();
		}
		return duracion;
	}

	public List<Comprable> getAtracciones() {
		return this.generadorDeAtracciones();
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

	public Integer getEntradasVendidas() {
		int entradas = 0;
		for (Comprable atraccion : this.atracciones) {
			entradas += atraccion.getEntradasVendidas();
		}
		return entradas;
	}

	public void comprarLugar() throws Exception {
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
