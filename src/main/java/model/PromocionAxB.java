package model;

import java.util.List;

public class PromocionAxB extends Promocion {

	protected Comprable atraccionGratis;
	protected Integer costoConDescuento;
	
	public PromocionAxB(int id, String nombre, String tipo_promocion,String descripcion, String tipo_atracciones,
			 String atracciones_promo, Boolean active) {
		super(id,nombre, tipo_promocion, descripcion, tipo_atracciones, atracciones_promo, active);
		this.atracciones = super.atracciones;
		this.atraccionGratis = atracciones.remove(atracciones.size() -1);

	
	}

	public Comprable getAtraccionGratis() {
		return this.atraccionGratis;
	}

	@Override
	public List<Comprable> getAtracciones() {
		atracciones = super.getAtracciones();
		atracciones.add(atraccionGratis);
		return atracciones;
		
	}
	
	@Override
	public Double getDuracion() {
		return super.getDuracion() + atraccionGratis.getDuracion();
	}
	
	
	public Integer getCostoConDescuento() {
		return this.calculadorDeCostoTotal(this.atracciones);
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
	 return	this.getCostoConDescuento();
	}

	public Integer getCupo() {
		
	Integer sumaDeCupos = super.getCupo() + atraccionGratis.getCupo(); 

	return sumaDeCupos;
	
			
	}
	


}
