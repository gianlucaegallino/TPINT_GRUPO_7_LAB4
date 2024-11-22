package entidades;

import java.sql.Date;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String apellido;
	private String dni;
	private String cuil;
	private int sexo_id;
	private int nacionalidad_id;
	private Date fecha_nacimiento;
	private String direccion_id;
	private String correo_electronico;
	private String telefono;
	private String estado;
	private int idUsuario;

	public Cliente() {
	}

	public Cliente(int idCliente, String nombre, String apellido, String dni, String cuil, int sexo_id,
			int nacionalidad_id, Date fecha_nacimiento, String direccion_id, String correo_electronico, String telefono,
			String estado, int idUsuario) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cuil = cuil;
		this.sexo_id = sexo_id;
		this.nacionalidad_id = nacionalidad_id;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion_id = direccion_id;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.estado = estado;
		this.idUsuario = idUsuario;
	}

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

	public int getSexo_id() {
		return sexo_id;
	}

	public void setSexo_id(int sexo_id) {
		this.sexo_id = sexo_id;
	}

	public int getNacionalidad_id() {
		return nacionalidad_id;
	}

	public void setNacionalidad_id(int nacionalidad_id) {
		this.nacionalidad_id = nacionalidad_id;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion_id() {
		return direccion_id;
	}

	public void setDireccion_id(String direccion_id) {
		this.direccion_id = direccion_id;
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
				+ ", cuil=" + cuil + ", sexo_id=" + sexo_id + ", nacionalidad_id=" + nacionalidad_id
				+ ", fecha_nacimiento=" + fecha_nacimiento + ", direccion_id=" + direccion_id + ", correo_electronico="
				+ correo_electronico + ", telefono=" + telefono + ", estado=" + estado + ", idUsuario=" + idUsuario
				+ "]";
	}

}