package interfaces;

import entidades.Usuario;

public interface IUsuarioDao {

	public Usuario obtenerUsuarioporLogin(String nomUsuario, String contUsuario);
	//Este dao se asegura que UsuarioDao tenga al menos una opcion de buscar login.
}
