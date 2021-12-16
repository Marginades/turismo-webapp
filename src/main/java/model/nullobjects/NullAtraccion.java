package model.nullobjects;

import model.Atraccion;

public class NullAtraccion extends Atraccion {

	public static Atraccion build() {
		return new NullAtraccion();
	}

	public NullAtraccion() {
		super(0, "", "", 0, 0.0, 0, "", false);
	}

	public boolean isNull() {
		return true;
	}

}