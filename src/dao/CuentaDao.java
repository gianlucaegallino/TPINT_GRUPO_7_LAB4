package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.TipoCuenta;
import interfaces.IConexion;
import interfaces.ICuentaDao;

public class CuentaDao implements IConexion, ICuentaDao {


	public int AgregarCuenta(Cuenta cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement(
					"INSERT INTO cuentas (cliente_id, fecha_creacion, tipo_cuenta_id, cbu, saldo, estado)VALUES(?,?,?,?,?,?)");
			// Establecer los valores de los parámetros utilizando el objeto 'cuenta'
			miSentencia.setInt(1, cuenta.getIDcliente().getIdCliente());
			miSentencia.setDate(2, (Date) cuenta.getFecha_creacion());
			miSentencia.setInt(3, cuenta.getCuenta().getId());
			miSentencia.setString(4, cuenta.getCbu());
			miSentencia.setDouble(5, cuenta.getSaldo());
			miSentencia.setInt(6, 1);

			// Ejecutar la consulta
			int filasAfectadas = miSentencia.executeUpdate(); // Usamos 'executeUpdate' ya que no esperamos un
																// resultado, solo la inserción.

			if (filasAfectadas > 0) {
				System.out.println("Cuenta insertada correctamente.");
				return filasAfectadas;
			} else {
				System.out.println("No se inserto ninguna cuenta.");

			}
		} catch (Exception e) {
			e.printStackTrace(); // Mostrar el error si ocurre algún problema
		} finally {
			try {
				if (con != null) {
					con.close(); // Cerrar la conexión
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}
	
	public int obtenerCantidadCuentasCliente(int idCliente) {
	    int cantidad = 0;
	    try (Connection connection = DriverManager.getConnection(host + dbName, user, pass)) {
	        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM cuentas WHERE cliente_id = ?");
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            cantidad = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cantidad;
	}

	@Override
	public ArrayList<Cuenta> obtenerLasCuentas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> listCuenta = new ArrayList<Cuenta>();

		Connection cn = null;
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);
			String query = "SELECT * FROM cuentas WHERE estado = 1";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Cuenta c = new Cuenta();
				c.setIDcliente(new Cliente(rs.getInt("cliente_id")));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				int tipocuenta = rs.getInt("tipo_cuenta_id");
				String tipoCuentaStr = BuscarTipoC(tipocuenta);
				TipoCuenta cuent = new TipoCuenta(tipoCuentaStr);
				c.setCuenta(cuent);
				c.setNumero_cuenta(rs.getInt("numero_cuenta"));
				c.setCbu(rs.getString("cbu"));
				c.setSaldo(rs.getDouble("saldo"));
				listCuenta.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCuenta;
	}

	/// ------------------------------------------------------
	public ArrayList<Cuenta> obtenerCuentasFiltradas(String cbu, Integer tipoCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Cuenta> listCuenta = new ArrayList<>();
		Connection cn = null;

		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);

			// Construir consulta din�mica
			String query = "SELECT * FROM cuentas WHERE estado = 1";
			if (cbu != null && !cbu.isEmpty()) {
				query += " AND cbu LIKE '%" + cbu + "%'";
			}
			if (tipoCuenta != null) {
				query += " AND tipo_cuenta_id = " + tipoCuenta;
			}

			// Ejecutar consulta
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Procesar resultados
			while (rs.next()) {
				Cuenta c = new Cuenta();
				c.setIDcliente(new Cliente(rs.getInt("cliente_id")));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				int tipocuenta = rs.getInt("tipo_cuenta_id");
				String tipoCuentaStr = BuscarTipoC(tipocuenta);
				TipoCuenta cuent = new TipoCuenta(tipoCuentaStr);
				c.setCuenta(cuent);
				c.setNumero_cuenta(rs.getInt("numero_cuenta"));
				c.setCbu(rs.getString("cbu"));
				c.setSaldo(rs.getDouble("saldo"));
				listCuenta.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCuenta;
	}

	public int EliminarCuenta(int numCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect
					.prepareStatement("UPDATE cuentas SET estado = false WHERE numero_cuenta = ?");
			sentence.setInt(1, numCuenta);

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

	public ArrayList<Cuenta> obtenerCuentaCbu(String cbu) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Cuenta> listCuenta = new ArrayList<>();
		Connection cn = null;

		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);

			// Construir consulta din�mica
			String query = "SELECT * FROM cuentas WHERE estado = 1";
			if (cbu != null && !cbu.isEmpty()) {
				query += " AND cbu = '" + cbu + "'";
			}

			// Ejecutar consulta
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Procesar resultados
			while (rs.next()) {
				Cuenta c = new Cuenta();
				c.setIDcliente(new Cliente(rs.getInt("cliente_id")));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				c.setCuenta(new TipoCuenta(rs.getInt("tipo_cuenta_id")));
				
				int tipocuenta = rs.getInt("tipo_cuenta_id");
				String tipoCuentaStr = BuscarTipoC(tipocuenta);
				TipoCuenta cuent = new TipoCuenta(tipoCuentaStr);
				c.setCuenta(cuent);
				
				c.setNumero_cuenta(rs.getInt("numero_cuenta"));
				c.setCbu(rs.getString("cbu"));
				c.setSaldo(rs.getDouble("saldo"));
				listCuenta.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCuenta;
	}
	

	public int obtenerNumCuentaConCbu(String cbu) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Integer numCuenta = null;
		Connection cn = null;

		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);

			// Construir consulta din�mica
			String query = "SELECT * FROM cuentas WHERE estado = 1";
			if (cbu != null && !cbu.isEmpty()) {
				query += " AND cbu = '" + cbu + "'";
			}

			// Ejecutar consulta
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Procesar resultados
			while (rs.next()) {
				numCuenta = rs.getInt("numero_cuenta");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return numCuenta;
	}

	public String BuscarTipoC(int tipocuent) {
        // Realiza la consulta a la base de datos para obtener la descripción del sexo
        String TIPOcuenta = null;
        try (Connection conn = DriverManager.getConnection(host + dbName, user, pass);
             PreparedStatement stmt = conn.prepareStatement("SELECT nombre FROM tipo_cuenta WHERE id = ?")) {
            stmt.setInt(1, tipocuent);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	TIPOcuenta = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TIPOcuenta;
    }

	public int EliminarCuentaCbu(String cbuCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE cuentas SET estado = 0 WHERE cbu = ?");
			sentence.setString(1, cbuCuenta);

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
	
	public int AgregarMonto(String cbuCuenta, double monto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE cuentas SET saldo = saldo + ? WHERE cbu = ?");
			sentence.setDouble(1, monto);
			sentence.setString(2, cbuCuenta);

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
	
	public int RemoverMonto(String cbuCuenta, double monto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE cuentas SET saldo = saldo - ? WHERE cbu = ?");
			sentence.setDouble(1, monto);
			sentence.setString(2, cbuCuenta);

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

	public boolean modificarCuenta(Cuenta cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connect = null;
		boolean filasAfectadas = false;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect
					.prepareStatement("UPDATE cuentas SET cbu = ?, saldo = ? WHERE numero_cuenta = ?");
			sentence.setString(1, cuenta.getCbu());
			sentence.setDouble(2, cuenta.getSaldo());
			sentence.setInt(3, cuenta.getNumero_cuenta());

			filasAfectadas = sentence.executeUpdate() > 0;
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
