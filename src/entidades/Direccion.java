package entidades;

public class Direccion {
	private String direccion;
	private Localidad idLocalidad;
	
	
	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Direccion(String direccion, Localidad idLocalidad) {
		super();
		this.direccion = direccion;
		this.idLocalidad = idLocalidad;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(Localidad idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	@Override
	public String toString() {
		return "Direccion [direccion=" + direccion + ", idLocalidad=" + idLocalidad + "]";
	}
	
	
}
