package model;

public class PromocionPorcentual extends Promocion {

	private Double descuento;
	private Integer costoConDescuento;
	
	public PromocionPorcentual(int id, String nombre, String descripcion, String tipo_promocion, String tipo_atracciones,
			Integer descuento, String atracciones_promo, Boolean active) {
		
		super(id, nombre, descripcion, tipo_promocion, tipo_atracciones, atracciones_promo, active);
		super.atracciones = super.generadorDeAtracciones();
		this.costoConDescuento = (Integer) Math.round(this.costoSinDescuento * descuento);
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
	public int getCosto() {
		int precio = super.getCosto();
		double descuento = precio * this.descuento;
		return (int) (precio - descuento);
	}

	public boolean esComprablePor(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String getTipo() {
		return super.tipo_atracciones;
	}

	public double getDescuento() {
		return this.descuento;
	}

	public Integer getCostoConDescuento() {
		return costoConDescuento;
	}

}
