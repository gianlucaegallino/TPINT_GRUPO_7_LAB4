package entidades;

public class TipoCuenta {
	private int id;
	private String nombre;
	
	
	public TipoCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoCuenta(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "TipoCuenta [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
