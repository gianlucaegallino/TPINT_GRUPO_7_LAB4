package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Usuario;

public class ClienteDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdbancoliberacion?useSSL=false";

	public int AgregarCliente(Cliente cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connect = null;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);

			PreparedStatement clienteStmt = connect.prepareStatement(
					"INSERT INTO clientes (nombre, apellido, dni, cuil, sexo_id, nacionalidad_id, fecha_nacimiento, direccion_id, correo_electronico, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			clienteStmt.setString(1, cliente.getNombre());
			clienteStmt.setString(2, cliente.getApellido());
			clienteStmt.setString(3, cliente.getDni());
			clienteStmt.setString(4, cliente.getCuil());
			clienteStmt.setInt(5, cliente.getSexo().getId());
			clienteStmt.setInt(6, cliente.getNacionalidad().getId());
			clienteStmt.setDate(7, cliente.getFecha_nacimiento());
			clienteStmt.setString(8, cliente.getDireccion().getDireccion());
			clienteStmt.setString(9, cliente.getCorreo_electronico());
			clienteStmt.setString(10, cliente.getTelefono());

			int clienteResult = clienteStmt.executeUpdate();

			if (clienteResult > 0) {
				System.out.println("Cliente insertado correctamente.");
				return clienteResult;
			} else {
				System.out.println("No se insertó el cliente.");
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

	public ArrayList<Cliente> obtenerTodosLosClientes() {
		ArrayList<Cliente> LCliente = new ArrayList<Cliente>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);

			// Consulta solo para la tabla clientes
			String query = "SELECT * FROM clientes WHERE estado = true";

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				// Crear y configurar el objeto Cliente
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setSexo_id(rs.getInt("sexo_id"));
				cliente.setNacionalidad_id(rs.getInt("nacionalidad_id"));
				cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion_id(rs.getString("direccion_id"));
				cliente.setCorreo_electronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setEstado(rs.getString("estado"));

				LCliente.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return LCliente;
	}

	public Cliente buscarClientePorDNI(String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connect = null;
		Cliente cliente = new Cliente();

		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("SELECT * FROM clientes WHERE dni = ?");
			sentence.setString(1, dni);
			ResultSet rs = sentence.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setSexo_id(rs.getInt("sexo_id"));
				cliente.setNacionalidad_id(rs.getInt("nacionalidad_id"));
				cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				cliente.setDireccion_id(rs.getString("direccion_id"));
				cliente.setCorreo_electronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setIdCliente(rs.getInt("id_cliente"));
				// ****PONER EL RESTO DE ATRIBUTOS NECESARIOS*****
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
		return cliente;
	}

	public boolean modificarCliente(Cliente cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connect = null;
		boolean filasAfectadas = false;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement(
					"UPDATE clientes SET direccion_id = ?, correo_electronico = ?, telefono = ? WHERE id_cliente = ?");
			sentence.setString(1, cliente.getDireccion_id());
			sentence.setString(2, cliente.getCorreo_electronico());
			sentence.setString(3, cliente.getTelefono());
			sentence.setInt(4, cliente.getIdCliente());

			filasAfectadas = sentence.executeUpdate() > 0; // Actualiza la base de datos
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
		return filasAfectadas;
	}
	
	
	public int verificarDatosRepetido(Cliente cliente) {
	    int resultado = 0;
	    String query = "SELECT "
	                 + "CASE "
	                 + "  WHEN EXISTS (SELECT 1 FROM clientes WHERE dni = ?) THEN 2 "
	                 + "  WHEN EXISTS (SELECT 1 FROM clientes WHERE cuil = ?) THEN 3 "
	                 + "  WHEN EXISTS (SELECT 1 FROM clientes WHERE correo_electronico = ?) THEN 4 "
	                 + "  WHEN EXISTS (SELECT 1 FROM clientes WHERE telefono = ?) THEN 5 "
	                 + "  ELSE 0 "
	                 + "END AS resultado";
	    
	    // Establecer la conexión
	    try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
	         PreparedStatement stmtVerificar = conn.prepareStatement(query)) {

	        stmtVerificar.setString(1, cliente.getDni());
	        stmtVerificar.setString(2, cliente.getCuil());
	        stmtVerificar.setString(3, cliente.getCorreo_electronico());
	        stmtVerificar.setString(4, cliente.getTelefono());

	        // Ejecutar la consulta y obtener el resultado
	        try (ResultSet rs = stmtVerificar.executeQuery()) {
	            if (rs.next()) {
	                resultado = rs.getInt("resultado");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejo de excepciones
	        resultado = -1;  // Devolver un valor en caso de error en la consulta
	    }
	    
	    return resultado;  // Devuelve el resultado de la consulta
	}
	
	/*
	 * public int eliminarUsuarioPorDNI(String dni) { Connection connect = null; int
	 * filasAfectadas = 0; try { connect = DriverManager.getConnection(host +
	 * dbName, user, pass); PreparedStatement sentence =
	 * connect.prepareStatement("UPDATE usuarios SET estado = false WHERE dni = ?");
	 * sentence.setString(1, dni);
	 * 
	 * filasAfectadas = sentence.executeUpdate(); } catch (Exception e) {
	 * e.printStackTrace(); } finally { try { if (connect != null) {
	 * connect.close(); } } catch (SQLException e) { e.printStackTrace(); } } return
	 * filasAfectadas; }
	 */

	/*
	 * public static Cliente obtenerClientePorId(int clienteId) { Cliente cliente =
	 * null; String sql = "SELECT * FROM clientes WHERE id = ?";
	 * 
	 * // Establecer la conexi�n a la base de datos try (Connection connection =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/bdbancoliberacion",
	 * "root", "root"); PreparedStatement pstmt = connection.prepareStatement(sql))
	 * {
	 * 
	 * pstmt.setInt(1, clienteId); ResultSet rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { cliente = new Cliente( rs.getInt("id"),
	 * rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"),
	 * rs.getString("cuil"), rs.getInt("sexo_id"), rs.getInt("nacionalidad_id"),
	 * rs.getDate("fecha_nacimiento"), rs.getString("direccion_id"),
	 * rs.getString("correo_electronico"), rs.getString("telefono"),
	 * rs.getString("estado"), rs.getString("idUsuario") ); } } catch (SQLException
	 * e) { e.printStackTrace(); } return cliente; }
	 */

	public int EliminarCliente(String DNI) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE clientes SET estado = false WHERE dni = ?");
			sentence.setString(1, DNI);

			filasAfectadas = sentence.executeUpdate();
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
		return filasAfectadas;

	}

	public Cliente conseguirClienteporUsuario(Usuario usuario) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connect = null;
		Cliente cliente = new Cliente();
		

		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("SELECT * FROM clientes WHERE id_usuario = ?");
			sentence.setInt(1, usuario.getIdUsuario());
			
			ResultSet rs = sentence.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				// ****PONER EL RESTO DE ATRIBUTOS SI SON NECESARIOS*****
			}else {
				cliente = null; // para saber que es un empty result set
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

		return cliente;
	}

}
