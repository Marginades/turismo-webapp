package model;

public class PromocionAbsoluta extends Promocion {

	protected Double descuento;
	private Integer costoConDescuento;
	
	
	public PromocionAbsoluta(int id, String nombre, String descripcion, String tipo_promociones, String tipo_atracciones,
			Double descuento, String atracciones_promo, Boolean active) {
		super(id, nombre, descripcion, tipo_promociones, tipo_atracciones, atracciones_promo, active);
		this.descuento = descuento;
		this.costoConDescuento =  (int) (super.costoSinDescuento -  descuento);
		this.atracciones = super.atracciones;
	
	}

	@Override
	public String toString() {
		String impresion = "Pack " + this.nombre + ", llev�:\n";
		for (Comprable a : atracciones) {
			impresion += a.getNombre() + "\n";
		}
		impresion += "a tan solo " + this.getCosto();
		return impresion;
	}

	
	public Integer getCostoConDescuento() {
		return costoConDescuento;
	}

	public Boolean esComprablePor(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTipo() {
		return super.getTipo_atracciones();
	}
	
	public Double getDescuento( ) {
		return this.descuento;
	}

	@Override
	public Integer getCosto() {
		return this.costoConDescuento;
	}


	public void setDescuento(Double descuento) {
		this.descuento = descuento;
		
	}
	



}
