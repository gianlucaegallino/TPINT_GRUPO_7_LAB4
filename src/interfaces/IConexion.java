package interfaces;

public interface IConexion {

	String host = "jdbc:mysql://localhost:3306/";
	String user = "root";
	String pass = "root";
	String dbName = "bdbancoliberacion?useSSL=false";
	
	// Interfaz utilizada en todos los dao, con los parametros de BD. todo es public static final
}
