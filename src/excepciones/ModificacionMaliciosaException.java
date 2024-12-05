package excepciones;

public class ModificacionMaliciosaException extends Exception{

	
	public ModificacionMaliciosaException() {
		
	}
	
	@Override
	public String getMessage() {
		return "Se detecto una modificacion maliciosa al codigo.";
	}
}
