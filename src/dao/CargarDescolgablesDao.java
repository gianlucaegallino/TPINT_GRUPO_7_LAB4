package dao;

import java.sql.*;
import java.util.ArrayList;


import entidades.Prestamo;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import entidades.TipoCuenta;
import entidades.Usuario;
import interfaces.IConexion;

public class CargarDescolgablesDao implements IConexion {

    // Método para obtener los sexos desde la base de datos
    public ArrayList < Sexo > obtenerSexos() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList < Sexo > listaSexos = new ArrayList < > ();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);) {
            String query = "SELECT id, descripcion FROM sexo";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String descripcion = rs.getString("descripcion");
                    listaSexos.add(new Sexo(id, descripcion));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSexos;
    }

    // Método para obtener las nacionalidades desde la base de datos
    public ArrayList < Nacionalidad > obtenerNacionalidades() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList < Nacionalidad > listaNacionalidades = new ArrayList < > ();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass)) {
            String query = "SELECT id, nombre FROM nacionalidad";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    listaNacionalidades.add(new Nacionalidad(id, nombre));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNacionalidades;
    }

    // Método para obtener las provincias desde la base de datos
    public ArrayList < Provincia > obtenerProvincias() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList < Provincia > listaProvincias = new ArrayList < > ();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass)) {
            String query = "SELECT id, nombre FROM provincia";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    listaProvincias.add(new Provincia(id, nombre));
                    
                    System.out.println(id);
                    System.out.println(nombre);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProvincias;
    }

    // Método para obtener las localidades desde la base de datos
    public ArrayList < Localidad > obtenerLocalidadesPorProvincia(int provId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList < Localidad > listaLocalidades = new ArrayList < > ();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass); 
        		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
        		"SELECT l.id, l.nombre, p.id AS provincia_id, p.nombre AS provincia_nombre " +
                        "FROM localidad l JOIN provincia p ON l.provincia_id = p.id " +
                        "WHERE l.provincia_id = ?")) {
        	stmt.setInt(1, provId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int provinciaId = rs.getInt("provincia_id");
                String provinciaNombre = rs.getString("provincia_nombre");

                // Crear la provincia y agregar la localidad
                Provincia provincia = new Provincia(provinciaId, provinciaNombre);
                Localidad localidad = new Localidad(id, nombre, provincia);
 
                listaLocalidades.add(localidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLocalidades;
    }

	public ArrayList<Cuenta> ObtenerLasCuentasBancarias(int id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList < Cuenta > cuentas = new ArrayList < > ();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass); 
        		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(
        		"SELECT * FROM cuentas WHERE cliente_id = ? AND estado = 1"
        				)) {
        	stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
				Cuenta c = new Cuenta();
				//c.setCliente_id(rs.getInt("cliente_id"));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				c.setCuenta(new TipoCuenta(rs.getInt("tipo_cuenta_id"),""));
				c.setNumero_cuenta(rs.getInt("numero_cuenta"));
				c.setCbu(rs.getString("cbu"));
				c.setSaldo(rs.getDouble("saldo"));
				cuentas.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentas;
	}
	
	public ArrayList<Cliente> obtenerIDUsuarioVacio(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT id_cliente, nombre, apellido, dni FROM clientes WHERE id_usuario IS NULL AND estado = 1");
            ResultSet rs = sentence.executeQuery();
            while(rs.next()) {
            	Cliente cliente = new Cliente();
            	cliente.setIdCliente(rs.getInt("id_cliente"));
            	cliente.setNombre(rs.getString("nombre"));
            	cliente.setApellido(rs.getString("apellido"));
            	cliente.setDni(rs.getString("dni"));
            	
            	clientes.add(cliente);
            }
		}catch (Exception e) {
            e.printStackTrace();
        }
		return clientes;
	}
	
	public ArrayList<Usuario> obtenerUsuarios(){
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT u.*, CASE WHEN c.id_usuario IS NULL THEN 'No existe en clientes' ELSE 'Existe en clientes' END AS cliente_status FROM usuarios u LEFT JOIN clientes c ON u.id_usuario = c.id_usuario;");
            ResultSet rs = sentence.executeQuery();
            while(rs.next()) {
            	Usuario usuario = new Usuario();
            	usuario.setIdUsuario(rs.getInt("ID_Usuario"));
            	usuario.setUsuario(rs.getString("username"));
            	usuario.setContrasena(rs.getString("contrasena"));
            	usuario.setTipo_usuario(rs.getInt("tipo_usuario"));
            	usuario.setClienteStatus(rs.getString("cliente_status"));
            	
            	usuarios.add(usuario);
            }
		}catch (Exception e) {
            e.printStackTrace();
        }
		return usuarios;
	}
	

	public ArrayList< Prestamo > ObtenerLosPrestamos(int id) {
		ArrayList<Prestamo> prestamos = new ArrayList<>();
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = null;
			try {
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement st = cn.prepareStatement("SELECT * FROM prestamos WHERE cliente_id = ? AND pagos_restantes > 0 AND estado = 'aprobado'");
				 st.setInt(1, id);
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					Prestamo prestamo = new Prestamo();
					prestamo.setId(rs.getInt("id"));

					Cliente cliente = new Cliente();
					prestamo.setCliente(cliente);
					prestamo.setImportePedido(rs.getDouble("importe_pedido"));
					prestamo.setFecha(rs.getDate("fecha"));
					prestamo.setEstado(rs.getString("estado"));
					prestamo.setInteresAnual(rs.getDouble("interes_anual"));
					prestamo.setImporteConIntereses(rs.getDouble("importe_con_intereses"));
					prestamo.setPlazoMeses(rs.getInt("plazo_meses"));
					prestamo.setMontoMensual(rs.getDouble("monto_mensual"));
					prestamo.setCbu_cuenta(rs.getString("cbu_cuenta"));
					prestamo.setPagos_restantes(rs.getString("pagos_restantes"));
					
					prestamos.add(prestamo);
				}
			}catch (Exception e) {
				throw new RuntimeException("Error al obtener prestamos a pagar", e);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar el driver JDBC", e);
		}
        return prestamos;
	}
	
	public boolean asignarUsuarioACliente(int idUsuario, int idCliente) {
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
					"UPDATE clientes SET id_usuario = ? WHERE id_cliente = ?");
			sentence.setInt(1, idUsuario);
			sentence.setInt(2, idCliente);

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
    
}