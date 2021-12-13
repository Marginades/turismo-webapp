package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PromocionTest2 {

	PromocionAxB aventura;
	PromocionPorcentual degustacion;
	PromocionAbsoluta paisaje;
	
	@Before
	public void setUp() throws Exception {

			aventura = new PromocionAxB(1, "Aventura en las montañas", "LALALALA" , "AXB", "Aventura", "21-22-20", true);
			degustacion = new PromocionPorcentual(1, "Paladares elficos", "LALALALA", "POR","Degustacion", 0.1 , "28-26", true);
			paisaje = new PromocionAbsoluta(1, "Tarde Diferente","LALALA", "ABS","Paisajismo", 60.0, "30-32", true);	

		
	}

	@Test
	public void PromocionAbsolutaTest() {
		
		assertEquals(40, paisaje.getCostoConDescuento(), 0.1);
		
	}
	@Test
	public void PromocionAxBTest() {
		
		assertEquals("Montañas de Angmar", aventura.getAtraccionGratis().getNombre());
		assertEquals(50, aventura.getCostoConDescuento(), 0.1);
		assertEquals(2, aventura.getAtracciones().size());
	}
	
	@Test
	public void PromocionPorcentualTest() {
		assertEquals(36, degustacion.getCosto(), 0.1);
	}
	
	
	

}
