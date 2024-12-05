package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import interfaces.IConexion;

public class InformesEstadisticosDao implements IConexion{

    
    // METODOS
    
    public int contarMovimientosAltaCuenta(Date fechaDesde, Date fechaHasta) {
    	int totalAltaCuenta = 0;
        try {
            // Usa el controlador más nuevo
            Class.forName("com.mysql.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(host + dbName, user, pass);
            PreparedStatement sentence = cn
                    .prepareStatement("SELECT COUNT(*) FROM movimientos WHERE tipo_movimiento = 'Alta de Cuenta' AND fecha BETWEEN ? AND ?");
            // Convierte explícitamente a java.sql.Date
            sentence.setDate(1, java.sql.Date.valueOf(fechaDesde.toString()));
            sentence.setDate(2, java.sql.Date.valueOf(fechaHasta.toString()));
            ResultSet rs = sentence.executeQuery();
            // Obtén el conteo directamente de la primera fila
            if (rs.next()) {
                totalAltaCuenta = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalAltaCuenta;
    }

    public int contarTodosMovimientos(Date fechaDesde, Date fechaHasta) {
    	int totalMovimientos  = 0;
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement sentence = cn
                    .prepareStatement("SELECT COUNT(*) FROM movimientos WHERE fecha BETWEEN ? AND ?");
            sentence.setDate(1,(java.sql.Date) fechaDesde); 
            sentence.setDate(2,(java.sql.Date) fechaHasta );
			ResultSet rs = sentence.executeQuery();
            while(rs.next()) {
            	totalMovimientos  = rs.getInt(1);
            }
                     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalMovimientos ;
    }
}
