package model;

public interface Comprable {
	
	public int getId();
	
	public String getNombre();
	
	public Integer getCosto();

	public Double getDuracion();

	public String getTipo();
	
	public Integer getEntradasVendidas();
	
	public Boolean esComprablePor(Usuario user);
	
	public Boolean esOContiene(Comprable c);
	
	public Boolean hayCupo();

	public void comprarLugar() throws Exception;

	public Boolean esPromocion();
	
	public String toString();

	



}
