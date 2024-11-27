package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Usuario;

// TO-DO: ABSTRAER CONEXION A OTRA CLASE.

public class UsuarioDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdbancoliberacion?useSSL=false";

	
	public int AgregarUsuario(Usuario usuario) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    Connection connect = null;
	    try {
	        connect = DriverManager.getConnection(host + dbName, user, pass);

	        PreparedStatement usuarioStmt = connect
	                .prepareStatement("INSERT INTO usuarios (username, contrasena, tipo_usuario) VALUES (?, ?, ?)");
	        usuarioStmt.setString(1, usuario.getUsuario());
	        usuarioStmt.setString(2, usuario.getContrasena());
	        usuarioStmt.setInt(3, 1);
	        
	        
	        
	        int usuarioResult = usuarioStmt.executeUpdate();
	      
	        if (usuarioResult > 0) {
	            System.out.println("Usuario insertado correctamente.");
	            return usuarioResult;
	        } else {
	            System.out.println("No se insertó el usuario.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connect != null) {
	                connect.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return 0;
	}
	
	
	public int ObtenerIdUsuario(String username) {
	    int idUsuario = -1; // Inicializar con un valor por defecto
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    try (Connection con = DriverManager.getConnection(host + dbName, user, pass)) {
	        PreparedStatement UsuStmt = con.prepareStatement("SELECT id_usuario FROM bdbancoliberacion.usuarios WHERE username =?");
	        UsuStmt.setString(1, username); // Usar el parámetro recibido
	        ResultSet resultado = UsuStmt.executeQuery();

	        if (resultado.next()) { // Verificar si hay resultados
	            idUsuario = resultado.getInt("id_usuario"); // Usar el nombre de la columna
	        } else {
	            System.out.println("No se encontró ningún usuario con el username: " + username);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la conexión o consulta");
	        e.printStackTrace();
	    }
	    return idUsuario;
	}

	
	
	
	
	public Usuario obtenerUsuarioporLogin(String nomUsuario, String contUsuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Usuario usuario = new Usuario();
		Connection con = null;
		try {
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con
					.prepareStatement("Select * from usuarios where username = ? AND contrasena = ?");
			miSentencia.setString(1, nomUsuario);
			miSentencia.setString(2, contUsuario);
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();

			// copia datos de cuenta
			usuario.setIdUsuario(resultado.getInt(1));
			usuario.setUsuario(resultado.getString(2));
			usuario.setContrasena(resultado.getString(3));
			usuario.setTipo_usuario(resultado.getInt(4));

			con.close();
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			e.printStackTrace();
		} finally {
		}
		return usuario;
	}


	public int verificarExistenciaPorNombre(String nom) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
;
		Connection con = null;
		try {
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con
					.prepareStatement("Select * from usuarios where username = ?");
			miSentencia.setString(1, nom);
			ResultSet resultado = miSentencia.executeQuery();
			if(resultado.next()) {
				con.close();
				return 1;
				
			}


			
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			e.printStackTrace();
		} finally {
		}
		return 0;
	}

}
