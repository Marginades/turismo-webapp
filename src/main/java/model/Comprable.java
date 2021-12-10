package model;

public interface Comprable {
	
	public int getId();
	
	public String getNombre();
	
	public int getCosto();

	public double getDuracion();

	public String getTipo();
	
	public int getEntradasVendidas();
	
	public boolean esComprablePor(Usuario user);
	
	public boolean esOContiene(Comprable c);
	
	public boolean hayCupo();

	public void comprarLugar() throws Exception;

	public boolean esPromocion();
	
	public String toString();

	



}
