package model;

import persistence.commons.DAOFactory;

public class PromocionPorcentual extends Promocion {

	private Double descuento;
	private Integer costoConDescuento;
	
	public PromocionPorcentual(int id, String nombre, String tipo_promocion, String descripcion, String tipo_atracciones,
			Double descuento, String atracciones_promo, Boolean active) {
		
		super(id, nombre, tipo_promocion, descripcion, tipo_atracciones, atracciones_promo, active);
		this.atracciones = DAOFactory.getPromocionDAO().generadorDeAtracciones(atracciones_promo);
		this.costoSinDescuento = super.costoSinDescuento;
		this.costoConDescuento = (int) (this.costoSinDescuento - (Math.round(this.costoSinDescuento * descuento)));

	}


	@Override
	public String toString() {
		String impresion = "Pack " + this.nombre + ", llevando:\n";
		for (Comprable a : atracciones) {
			impresion += a.getNombre() + "\n";
		}
		impresion += "Ten�s un " + this.descuento * 100 + "% de descuento\nTOTAL: " + this.getCosto()
				+ " monedas de oro";
		return impresion;
	}

	@Override
	public Integer getCosto() {
	return (Integer) this.costoConDescuento;
	}

	public Boolean esComprablePor(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String getTipo() {
		return super.tipo_atracciones;
	}

	public Double getDescuento() {
		return this.descuento;
	}


	public String getTipo_promo () {
		return "POR";
	}



	public void setDescuento(Double descuento) {
		this.descuento = descuento;
		
	}
	
}
