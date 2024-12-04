package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Direccion;
import entidades.Movimiento;
import entidades.Nacionalidad;
import entidades.Sexo;

public class MovimientoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdbancoliberacion?useSSL=false";

	
	public Boolean AgregarMovimiento(Movimiento mov) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		int filasAfectadas = 0;
		
		try {
			con = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement miSentencia = con.prepareStatement(
					"INSERT INTO movimientos (cuenta_id, fecha, detalle, importe, tipo)VALUES(?,?,?,?,?)");
			// Establecer los valores de los parámetros utilizando el objeto 'cuenta'
			miSentencia.setInt(1, mov.getNumCuenta());
			miSentencia.setDate(2, (Date) mov.getFecha());
			miSentencia.setString(3, mov.getDetalle());
			miSentencia.setDouble(4, mov.getImporte());
			miSentencia.setString(5, mov.getTipo_movimiento());

			// Ejecutar la consulta
			filasAfectadas = miSentencia.executeUpdate(); // Usamos 'executeUpdate' ya que no esperamos un
																// resultado, solo la inserción.

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
		return (filasAfectadas > 0);
	}
	
	public ArrayList<Movimiento> TraerListaMovimiento(int id){
		ArrayList<Movimiento> movs = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = connect
                    .prepareStatement("SELECT * FROM movimientos WHERE cuenta_id = ?");
            sentence.setInt(1, id);
            ResultSet rs = sentence.executeQuery();
            while (rs.next()) {
            	Movimiento m = new Movimiento();
            	m.setCuenta(new Cuenta(rs.getInt("cuenta_id")));
                m.setFecha(rs.getDate("fecha"));
                m.setDetalle(rs.getString("detalle"));
                m.setImporte(rs.getDouble("importe"));
                m.setTipo_movimiento(rs.getString("tipo_movimiento"));

                movs.add(m);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movs;
	}
}
