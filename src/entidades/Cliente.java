package entidades;

import java.sql.Date;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String apellido;
	private String dni;
	private String cuil;
	private Sexo sexo;
	private Nacionalidad nacionalidad;
	private Date fecha_nacimiento;
	private Direccion direccion;
	private String correo_electronico;
	private String telefono;
	private String estado;
	private int idUsuario;
	
	// CONSTRUCTORS
	public Cliente() {
		super();
	}
	public Cliente(int idCliente) {
		super();
		this.idCliente = idCliente;
	}
	public Cliente(int idCliente, String nombre, String apellido, String dni, String cuil, Sexo sexo,
			Nacionalidad nacionalidad, Date fecha_nacimiento, Direccion direccion, String correo_electronico,
			String telefono, String estado, int idUsuario) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuil = cuil;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.estado = estado;
		this.idUsuario = idUsuario;
	}
	
	// GETTERS AND SETTERS
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", cuil=" + cuil + ", sexo=" + sexo + ", nacionalidad=" + nacionalidad + ", fecha_nacimiento="
				+ fecha_nacimiento + ", direccion=" + direccion + ", correo_electronico=" + correo_electronico
				+ ", telefono=" + telefono + ", estado=" + estado + ", idUsuario=" + idUsuario + "]";
	}
}