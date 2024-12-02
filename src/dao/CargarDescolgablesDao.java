package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Cuenta;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import entidades.TipoCuenta;

public class CargarDescolgablesDao {
    private String host = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "bdbancoliberacion?useSSL=false";

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
}