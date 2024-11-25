package entidades;

public class Usuario {



	private int idUsuario;
	private String usuario;
	private String contrasena;
	private int tipo_usuario;

	public Usuario(int idUsuario, String usuario, String contrasena, int tipo_usuario) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.tipo_usuario = tipo_usuario;
	}

	public Usuario() {
	}

	// Getters Setters

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasena=" + contrasena
				+ ", tipo_usuario=" + tipo_usuario + "]";
	}
	
}
