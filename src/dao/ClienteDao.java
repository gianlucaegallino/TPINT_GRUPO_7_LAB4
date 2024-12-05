package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import entidades.Usuario;
import interfaces.IClienteDao;
import interfaces.IConexion;

public class ClienteDao implements IConexion, IClienteDao  {


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

	@Override
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
			String query = "SELECT * FROM clientes WHERE estado = 1";

			PreparedStatement st = cn.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				// Crear y configurar el objeto Cliente
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setCuil(rs.getString("cuil"));
				
				int sexoId = rs.getInt("sexo_id");
				String SexoDescrip = BuscarSexo(sexoId);
				cliente.setSexo(new Sexo(sexoId, SexoDescrip));
	            
	            int nacioID = rs.getInt("nacionalidad_id");
	            String NacioDescrip = BuscarNacionalidad(nacioID);
	            cliente.setNacionalidad(new Nacionalidad(nacioID, NacioDescrip));
	            
				cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				
				String direccion = rs.getString("direccion_id");
				Direccion direc = new Direccion(direccion);
				cliente.setDireccion(direc);
				
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
	
	public ArrayList<Cliente> ARRAYbuscarClientesPorAPELLIDO(String apellido) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT * FROM clientes WHERE apellido LIKE ? AND estado = 1");
            sentence.setString(1, "%" + apellido + "%");
            ResultSet rs = sentence.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCuil(rs.getString("cuil"));

                int sexoId = rs.getInt("sexo_id");
                Sexo sexo = new Sexo(BuscarSexo(sexoId));
                cliente.setSexo(sexo);

                int nacioID = rs.getInt("nacionalidad_id");
                Nacionalidad nacio = new Nacionalidad(BuscarNacionalidad(nacioID));
                cliente.setNacionalidad(nacio);

                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

                String direccion = rs.getString("direccion_id");
                Direccion direc = new Direccion(direccion);
                cliente.setDireccion(direc);

                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdCliente(rs.getInt("id_cliente"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
	
	public ArrayList<Cliente> ARRAYbuscarClientesPorDNI(String dni) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT * FROM clientes WHERE dni LIKE ? AND estado = 1");
            sentence.setString(1, "%" + dni + "%");
            ResultSet rs = sentence.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCuil(rs.getString("cuil"));

                int sexoId = rs.getInt("sexo_id");
                Sexo sexo = new Sexo(BuscarSexo(sexoId));
                cliente.setSexo(sexo);

                int nacioID = rs.getInt("nacionalidad_id");
                Nacionalidad nacio = new Nacionalidad(BuscarNacionalidad(nacioID));
                cliente.setNacionalidad(nacio);

                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

                String direccion = rs.getString("direccion_id");
                Direccion direc = new Direccion(direccion);
                cliente.setDireccion(direc);

                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdCliente(rs.getInt("id_cliente"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
	
	public ArrayList<Cliente> ARRAYbuscarClientesPorCUIL(String cuil) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT * FROM clientes WHERE cuil LIKE ? AND estado = 1");
            sentence.setString(1, "%" + cuil + "%");
            ResultSet rs = sentence.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCuil(rs.getString("cuil"));

                int sexoId = rs.getInt("sexo_id");
                Sexo sexo = new Sexo(BuscarSexo(sexoId));
                cliente.setSexo(sexo);

                int nacioID = rs.getInt("nacionalidad_id");
                Nacionalidad nacio = new Nacionalidad(BuscarNacionalidad(nacioID));
                cliente.setNacionalidad(nacio);

                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

                String direccion = rs.getString("direccion_id");
                Direccion direc = new Direccion(direccion);
                cliente.setDireccion(direc);

                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdCliente(rs.getInt("id_cliente"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
	
	public ArrayList<Cliente> ARRAYbuscarClientesPorNombre(String nombre) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ? AND estado = 1");
            sentence.setString(1, "%" + nombre + "%");
            ResultSet rs = sentence.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setDni(rs.getString("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCuil(rs.getString("cuil"));

                int sexoId = rs.getInt("sexo_id");
                Sexo sexo = new Sexo(BuscarSexo(sexoId));
                cliente.setSexo(sexo);

                int nacioID = rs.getInt("nacionalidad_id");
                Nacionalidad nacio = new Nacionalidad(BuscarNacionalidad(nacioID));
                cliente.setNacionalidad(nacio);

                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

                String direccion = rs.getString("direccion_id");
                Direccion direc = new Direccion(direccion);
                cliente.setDireccion(direc);

                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setIdCliente(rs.getInt("id_cliente"));

                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
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
			PreparedStatement sentence = connect.prepareStatement("SELECT * FROM clientes WHERE dni = ? AND estado = 1");
			sentence.setString(1, dni);
			ResultSet rs = sentence.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getString("dni"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setCuil(rs.getString("cuil"));
				
				int sexoId = rs.getInt("sexo_id");
	            Sexo sexo = new Sexo(sexoId); // Puedes obtener la descripción si la necesitas
	            cliente.setSexo(sexo);
	            
	            int nacioID = rs.getInt("nacionalidad_id");
	            Nacionalidad nacio = new Nacionalidad(nacioID);
	            cliente.setNacionalidad(nacio);
	            
				cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
				
				String direccion = rs.getString("direccion_id");
				Direccion direc = new Direccion(direccion);
				cliente.setDireccion(direc);
				
				cliente.setCorreo_electronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setIdCliente(rs.getInt("id_cliente"));
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

	
	public String BuscarSexo(int sexoId) {
        // Realiza la consulta a la base de datos para obtener la descripción del sexo
        String sexoDescripcion = null;
        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT descripcion FROM sexo WHERE id = ?")) {
            stmt.setInt(1, sexoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sexoDescripcion = rs.getString("descripcion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sexoDescripcion;
    }
	
	
	public int BuscarIdLocalidad(String direccion) {
        // Realiza la consulta a la base de datos para obtener la descripción del sexo
        Integer idloc = null;
        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT id_localidad FROM direccion WHERE direccion = ?")) {
            stmt.setString(1, direccion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	idloc = rs.getInt("id_localidad");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idloc;
    }
	
	public Localidad BuscarLocalidad(String direccion) {
		Localidad idloc = null;

		Integer id = BuscarIdLocalidad(direccion);

		
		if (id != null) {
			 // Realiza la consulta a la base de datos 
	        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
	             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM localidad WHERE id = ?")) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                	idloc = new Localidad();
	                	idloc.setId(rs.getInt("id"));
	                	idloc.setNombre(rs.getString("nombre"));
	                	idloc.setProvincia(new Provincia());
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return idloc;
		} else  {
			return null;
		}
			
		
    }
	
	public String BuscarNacionalidad(int id) {
		String NacionalidadNombre = null;
        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT nombre FROM nacionalidad WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	NacionalidadNombre = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return NacionalidadNombre;
	}
	
	public Nacionalidad BuscarNacionalidadEntera(int id) {
		Nacionalidad nac = null;
        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM nacionalidad WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	nac = new Nacionalidad();
                	nac.setNombre(rs.getString("nombre"));
                	nac.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nac;
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
			sentence.setString(1, cliente.getDireccion().getDireccion());
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

	
	 public Cliente obtenerClientePorId(int clienteId) { 
		  try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection connect = null;
			Cliente cliente = null;

			try {
				connect = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement sentence = connect.prepareStatement("SELECT * FROM clientes WHERE id_cliente = ? AND estado = 1");
				sentence.setInt(1, clienteId);
				ResultSet rs = sentence.executeQuery();
				if (rs.next()) {
	                cliente = new Cliente();
	                cliente.setDni(rs.getString("dni"));
	                cliente.setNombre(rs.getString("nombre"));
	                cliente.setApellido(rs.getString("apellido"));
	                cliente.setCuil(rs.getString("cuil"));

	                int sexoId = rs.getInt("sexo_id");
	                Sexo sexo = new Sexo(BuscarSexo(sexoId));
	                cliente.setSexo(sexo);

	                int nacioID = rs.getInt("nacionalidad_id");
	                Nacionalidad nacio =BuscarNacionalidadEntera(nacioID);
	                cliente.setNacionalidad(nacio);

	                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

	                String direccion = rs.getString("direccion_id");
	                cliente.setDireccion(new Direccion(direccion));

	                cliente.setCorreo_electronico(rs.getString("correo_electronico"));
	                cliente.setTelefono(rs.getString("telefono"));
	                cliente.setIdCliente(rs.getInt("id_cliente"));
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
	 

	public int EliminarCliente(int idCliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE clientes SET estado = 0 WHERE id_cliente = ?");
			sentence.setInt(1, idCliente);

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
				cliente.setIdCliente(rs.getInt("id_cliente"));
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
