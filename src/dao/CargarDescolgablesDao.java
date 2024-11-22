package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import entidades.Sexo;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;

public class CargarDescolgablesDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdbancoliberacion?useSSL=false";
	
	// Método para obtener los sexos desde la base de datos
	public ArrayList<Sexo> obtenerSexos() {
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		
	    ArrayList<Sexo> listaSexos = new ArrayList<>();
	    try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass);) {
	        String query = "SELECT id, descripcion FROM sexo";
	        try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {
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
    public ArrayList<Nacionalidad> obtenerNacionalidades() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Nacionalidad> listaNacionalidades = new ArrayList<>();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass)) {
            String query = "SELECT id, nombre FROM nacionalidad";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
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
    public ArrayList<Provincia> obtenerProvincias() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Provincia> listaProvincias = new ArrayList<>();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass)) {
            String query = "SELECT id, nombre FROM provincia";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    listaProvincias.add(new Provincia(id, nombre));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProvincias;
    }
    
    // Método para obtener las localidades desde la base de datos
    public ArrayList<Localidad> obtenerLocalidades() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Localidad> listaLocalidades = new ArrayList<>();
        try (Connection conn = (Connection) DriverManager.getConnection(host + dbName, user, pass)) {
            String query = "SELECT id, nombre, provincia_id FROM localidad";
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int provinciaId = rs.getInt("provincia_id");
                    listaLocalidades.add(new Localidad(id, nombre, provinciaId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLocalidades;
    }
    
	
	
}


