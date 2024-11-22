package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<Prestamo> listarPrestamosPendientes() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos WHERE estado = 'pendiente'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getString("fecha"),
                    rs.getDouble("importe_pedido"),
                    rs.getDouble("importe_con_intereses"),
                    rs.getInt("plazo_meses"),
                    rs.getDouble("monto_mensual"),
                    rs.getString("estado"),
                    rs.getDouble("interes_anual") // Nuevo campo para el interés anual
                );
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
                    rs.getInt("cliente_id"),
                    rs.getString("fecha"),
                    rs.getDouble("importe_pedido"),
                    rs.getDouble("importe_con_intereses"),
                    rs.getInt("plazo_meses"),
                    rs.getDouble("monto_mensual"),
                    rs.getString("estado"),
                    rs.getDouble("interes_anual") // Nuevo campo para el interés anual
                );
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
