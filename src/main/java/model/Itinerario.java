package model;

import java.util.LinkedList;

public class Itinerario {
	private LinkedList<Comprable> itinerario;

	public Itinerario() {
		this.itinerario = new LinkedList <Comprable>();
	}
	
	public LinkedList<Comprable> getItinerario() {
		return itinerario;
	}
	
	public boolean agregarAlItinerario(Comprable c) {
		if (!this.itinerario.contains(c)) {
			this.itinerario.add(c);
			return true;
		}else return false;
	}

}
