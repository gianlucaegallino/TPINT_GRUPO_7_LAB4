package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Cuenta;

public class CuentaDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdbancoliberacion?useSSL=false";

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
					"INSERT INTO Cuentas (cliente_id, fecha_creacion, tipo_cuenta_id, cbu, saldo, estado)VALUES(?,?,?,?,?,?)");
			// Establecer los valores de los parámetros utilizando el objeto 'cuenta'
			miSentencia.setInt(1, cuenta.getCliente_id());
			miSentencia.setDate(2, cuenta.getFecha_creacion());
			miSentencia.setInt(3, cuenta.getTipo_cuenta_id());
			miSentencia.setString(4, cuenta.getCbu());
			miSentencia.setDouble(5, cuenta.getSaldo());
			miSentencia.setInt(6, cuenta.isEstado());

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
				c.setCliente_id(rs.getInt("cliente_id"));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				c.setTipo_cuenta_id(rs.getInt("tipo_cuenta_id"));
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
				query += " AND cbu = '" + cbu + "'";
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
				c.setCliente_id(rs.getInt("cliente_id"));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				c.setTipo_cuenta_id(rs.getInt("tipo_cuenta_id"));
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
				c.setCliente_id(rs.getInt("cliente_id"));
				c.setFecha_creacion(rs.getDate("fecha_creacion"));
				c.setTipo_cuenta_id(rs.getInt("tipo_cuenta_id"));
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
