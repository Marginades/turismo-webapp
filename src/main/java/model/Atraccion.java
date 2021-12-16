package model;

import java.util.HashMap;
import java.util.Map;

public class Atraccion implements Comprable {
	private Integer id_atraccion;
	private String nombre;
	private String descripcion;
	private Integer costo;
	private Double duracion;
	private String tipo;
	private Integer entradasVendidas = 0;
	private Integer cupoMaximo;
	private Boolean active;
	
	
	private Map<String, String> errors;
	
	public Atraccion(int id_atraccion, String nombre, String descripcion, Integer costo, Double duracion, Integer cupo, String tipo, Boolean active){
		this.id_atraccion = id_atraccion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoMaximo = cupo;
		this.tipo = tipo;
		this.active = active;
		
	}

	// Metodos Overrride de Comprable
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCosto() {
		return this.costo;
	}
	
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public Double getDuracion() {
		return this.duracion;
	}
	
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo.toLowerCase();
	}

	public Integer getCupo() {
		return this.cupoMaximo;
	}
	
	public void setCupo(Integer cupo) {
		this.cupoMaximo = cupo;
	}

	public Integer getEntradasVendidas() {
		return this.entradasVendidas;
	}

	public int getId() {
		return id_atraccion;
	}
	
	public Boolean getActive() {
		return this.active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duracion <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (cupoMaximo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean canHost(int i) {
		return this.cupoMaximo >= i;
	}

	public void host(int i) {
		this.cupoMaximo -= i;
	}

	
	
	public Boolean hayCupo() {
		return this.entradasVendidas < this.cupoMaximo;
	}
	


	
	// Modifica el atributo entradasVendidas
	public void comprarLugar() throws Exception {
		if (!this.hayCupo())
			throw new Exception("No Hay Mas Lugar");
		this.entradasVendidas += 1;
	}

	public Boolean esOContiene(Comprable c) {
		return this.equals(c);
	}

	public Boolean esComprablePor(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	public Boolean esPromocion() {
		return false;
	}
	

	// Imprimir
	@Override
	public String toString() {
		return "Llev� " + this.nombre + " por " + this.costo + " monedas de oro";
	}

}
