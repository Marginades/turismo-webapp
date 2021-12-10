package model;

public class PromocionAbsoluta extends Promocion {

	protected Integer descuento;
	private Integer costoConDescuento;
	

	public PromocionAbsoluta(int id, String nombre, String descripcion, String tipo_promocion, String tipo_atracciones,
			Integer descuento, String atracciones_promo, Boolean active) {
		super(id, nombre, descripcion, tipo_promocion, tipo_atracciones, atracciones_promo, active);
		this.descuento = descuento;
		this.costoConDescuento = super.costoSinDescuento - descuento;
		super.atracciones = super.generadorDeAtracciones();
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

	@Override
	public int getCosto() {
		int precio = super.getCosto();
		return precio - descuento;
	}

	public boolean esComprablePor(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTipo() {
		return super.getTipo_atracciones();
	}
	
	public Integer getCostoConDescuento() {
		return costoConDescuento;
	}

}
