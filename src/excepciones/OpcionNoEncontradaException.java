package excepciones;

public class OpcionNoEncontradaException extends Exception{

	
	public OpcionNoEncontradaException() {
		
	}
	
	@Override
	public String getMessage() {
		return "No se encontro una accion valida a realizar.";
	}
	
	// Usada en SICLIENTES. 
}