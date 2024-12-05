package excepciones;

public class BotonInvalidoException extends Exception{

	
	public BotonInvalidoException() {
		
	}
	
	@Override
	public String getMessage() {
		return "No se encontro un boton ejecutor.";
	}
	
	// Usada en SIniciarSesion
}