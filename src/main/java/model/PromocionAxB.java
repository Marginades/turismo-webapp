package model;

import java.util.List;

public class PromocionAxB extends Promocion {

	protected Comprable atraccionGratis;
	protected Integer costoConDescuento;
	
	public PromocionAxB(int id, String nombre, String descripcion, String tipo_promocion, String tipo_atracciones,
			 String atracciones_promo, Boolean active) {
		super(id,nombre, descripcion, tipo_promocion, tipo_atracciones, atracciones_promo, active);
		super.atracciones = super.generadorDeAtracciones();
		
		this.atraccionGratis = atracciones.get(atracciones.size());
		this.costoConDescuento = (int) (super.costoSinDescuento.doubleValue() - atraccionGratis.getCosto().doubleValue()) ;
		
	}

	public Comprable getAtraccionGratis() {
		return this.atraccionGratis;
	}

	@Override
	public List<Comprable> getAtracciones() {
		return super.getAtracciones();
	}

	@Override
	public String toString() {
		String impresion = "Pack " + this.nombre + ", comprando:\n";
		for (Comprable a : atracciones) {
			impresion += a.getNombre() + "\n";
		}
		impresion += this.atraccionGratis.getNombre() + " es gratis\nTotal: " + this.getCosto();
		return impresion;
	}

	@Override
	public Boolean hayCupo() {
		return super.hayCupo() && atraccionGratis.hayCupo();
	}
	
	public Integer getCostoConDescuento() {
		return (Integer) this.costoConDescuento;
	}

	@Override
	public Double getDuracion() {
		return super.getDuracion() + atraccionGratis.getDuracion();
	}

	@Override
	public Boolean esOContiene(Comprable atraccion) {
		if (!atraccion.equals(this.atraccionGratis)) {

			for (Comprable a : this.atracciones) {
				if (atraccion.equals(a)) {
					return true;
				}
			}

			return false;
		} else {
			return true;
		}
	}

	public Boolean esComprablePor(Usuario user) {
		return false;
	}

	@Override
	public void comprarLugar() throws Exception {
		for (Comprable a : this.atracciones) {
			a.comprarLugar();
		}
		this.atraccionGratis.comprarLugar();
	}

	@Override
	public Integer getEntradasVendidas() {
		int entradas = 0;
		for (Comprable atraccion : this.atracciones) {
			entradas += atraccion.getEntradasVendidas();
		}
		return entradas + atraccionGratis.getEntradasVendidas();
	}


	@Override
	public String getTipo() {
		return super.getTipo_atracciones();
	}

	@Override
	public Integer getCosto() {
		// TODO Auto-generated method stub
		return null;
	}
	


}
