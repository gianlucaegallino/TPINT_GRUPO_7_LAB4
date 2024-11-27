package Negocio;

import dao.UsuarioDao;
import entidades.Usuario;

public class NegUsuario {
	UsuarioDao dao;


	// Constructor para inicializar cnt
	public NegUsuario() {
		this.dao = new UsuarioDao(); // Inicializa cnt aquí
	}

	
	public Usuario conseguirUsuarioPorCredenciales(String nom, String cont) {
		return dao.obtenerUsuarioporLogin(nom, cont);
	}
	
	public int AgregarUsuario(Usuario usuario) {
		return dao.AgregarUsuario(usuario);
	}

	public int verificarExistenciaPorNombre(String nom) {
		return dao.verificarExistenciaPorNombre(nom);
	}
	
}
