package dao;

import java.sql.*;

import java.util.ArrayList;

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
            System.out.println("Conexi�n exitosa a la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e); // Lanza la excepción
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

    public ArrayList<Prestamo> listarPrestamosPendientes() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = null;
			try {
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement st = cn.prepareStatement("SELECT * FROM prestamos WHERE estado = 'pendiente'");
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					Prestamo prestamo = new Prestamo();
					prestamo.setId(rs.getInt("id"));
					int idCliente = rs.getInt("cliente_id");
					String ClienteNombre = buscarAtributo(idCliente, "nombre");
					String ClienteApellido = buscarAtributo(idCliente, "apellido");
					String ClienteDNI = buscarAtributo(idCliente, "dni");
					Cliente cliente = new Cliente(idCliente, ClienteNombre, ClienteApellido, ClienteDNI);
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
				throw new RuntimeException("Error al obtener préstamos pendientes", e);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar el driver JDBC", e);
		}
        return prestamos;
    }

    
    public String buscarAtributo(int id , String elementoAbuscar) {
    	String valor = null;
    	String sql = "SELECT " + elementoAbuscar + " FROM clientes WHERE id_cliente = ?";
    	try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                valor = rs.getString(elementoAbuscar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
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
                    rs.getDouble("interes_anual"), // Nuevo campo para el inter�s anual
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
            pstmt.setDouble(2, prestamo.getInteresAnual()); // Nuevo campo para actualizar el inter�s anual
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
                System.out.println("Conexi�n cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexi�n: " + e.getMessage());
        }
    }

	public int removerPagosRestantes(int iddeuda, Integer cuotaspagas) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connect = null;
		int filasAfectadas = 0;
		try {
			connect = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = connect.prepareStatement("UPDATE prestamos SET pagos_restantes = pagos_restantes - ? WHERE id = ?");
			sentence.setString(1, String.valueOf(cuotaspagas));
			sentence.setInt(2, iddeuda);

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
}
