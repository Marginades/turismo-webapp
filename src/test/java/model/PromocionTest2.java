package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import services.PromocionService;

public class PromocionTest2 {

	PromocionAxB aventura;
	PromocionPorcentual degustacion;
	PromocionAbsoluta paisaje;
	PromocionService promocionService;

		
	@Before
	public void setUp() throws Exception {

		aventura = new PromocionAxB(1, "Aventura en las montañas", "LALALALA", "AXB", "Aventura", "21-22-20", true);
		degustacion = new PromocionPorcentual(1, "Paladares elficos", "LALALALA", "POR", "Degustacion", 0.1, "28-26", true);
		paisaje = new PromocionAbsoluta(1, "Tarde Diferente", "LALALA", "ABS", "Paisajismo", 60.0, "30-32", true);
		promocionService = new PromocionService();

	}
;
	@Test
	public void PromocionAbsolutaTest() {

		assertEquals(40, paisaje.getCostoConDescuento(), 0.1);
		assertEquals(7, paisaje.getDuracion(), 1);

	}
	
	@Test
	public void PromocionAxBTest() {

		assertEquals("Montañas de Angmar", aventura.getAtraccionGratis().getNombre());
		assertEquals(50, aventura.getCostoConDescuento(), 0.1);
		assertEquals(3, aventura.getAtracciones().size());
	}

	@Test
	public void PromocionPorcentualTest() {
		assertEquals(36, degustacion.getCosto(), 0.1);
	}

	@Test
	public void generadorDeAtracciones() {
		String[] lista_atracciones = { "55", "35", "35" };
		assertEquals(25, promocionService.procesadorDeArrays(lista_atracciones));

	}

	@Test
	public void getDuracionTest() {
		assertEquals(5, aventura.getDuracion(), 0.1);

	}

	@Test
	public void getDuracionTest2() {
		assertEquals(5, degustacion.getDuracion(), 0.1);
	}

	@Test
	public void getDuracionTest3() {
		assertEquals(5, paisaje.getDuracion(), 0.1);
	}
}
