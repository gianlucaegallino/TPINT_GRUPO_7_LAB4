package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

import entidades.Prestamo;

public class PrestamoDao {
    private String host = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bdbancoliberacion?useSSL=false";
    private Connection connection;

    public PrestamoDao() {
        try {
            connection = DriverManager.getConnection(host + dbName, user, pass);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    
    public int AgregarPrestamo(Prestamo prestamo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement(
					"INSERT INTO prestamos (cliente_id, fecha, importe_pedido, estado, interes_anual, importe_con_intereses, plazo_meses, monto_mensual, cbu_cuenta)VALUES(?,?,?,?,?,?,?,?,?)");


			miSentencia.setInt(1, prestamo.getCliente().getIdCliente());
			miSentencia.setDate(2, (Date) prestamo.getFecha());
			miSentencia.setDouble(3, prestamo.getImportePedido());
			miSentencia.setString(4, "pendiente");
			miSentencia.setDouble(5, prestamo.getInteresAnual());
			miSentencia.setDouble(6, prestamo.getImporteConIntereses());
			miSentencia.setInt(7, prestamo.getPlazoMeses());
			miSentencia.setDouble(8, prestamo.getMontoMensual());
			miSentencia.setString(9, prestamo.getCbu_cuenta());

			// Ejecutar la consulta
			int filasAfectadas = miSentencia.executeUpdate(); // Usamos 'executeUpdate' ya que no esperamos un
																// resultado, solo la inserciÃ³n.

			if (filasAfectadas > 0) {
				System.out.println("Cuenta insertada correctamente.");
				return filasAfectadas;
			} else {
				System.out.println("No se inserto ninguna cuenta.");

			}
		} catch (Exception e) {
			e.printStackTrace(); // Mostrar el error si ocurre algÃºn problema
		} finally {
			try {
				if (con != null) {
					con.close(); // Cerrar la conexiÃ³n
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;

	}

    public List<Prestamo> listarPrestamosPendientes() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE estado = 'pendiente'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getDouble("importe_pedido"),
                    rs.getDouble("importe_con_intereses"),
                    rs.getInt("plazo_meses"),
                    rs.getDouble("monto_mensual"),
                    rs.getString("estado"),
                    rs.getDouble("interes_anual"),
                    new Cliente(rs.getInt("cliente_id")),
                    rs.getString("cbu_cuenta"));
            
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    public Prestamo obtenerPorNumero(int id) {
        Prestamo prestamo = null;
        String sql = "SELECT * FROM prestamos WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                prestamo = new Prestamo(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getDouble("importe_pedido"),
                    rs.getDouble("importe_con_intereses"),
                    rs.getInt("plazo_meses"),
                    rs.getDouble("monto_mensual"),
                    rs.getString("estado"),
                    rs.getDouble("interes_anual"), // Nuevo campo para el interés anual
                    new Cliente(rs.getInt("cliente_id")),
                    rs.getString("cbu_cuenta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    public boolean actualizar(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET estado = ?, interes_anual = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, prestamo.getEstado());
            pstmt.setDouble(2, prestamo.getInteresAnual()); // Nuevo campo para actualizar el interés anual
            pstmt.setInt(3, prestamo.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
