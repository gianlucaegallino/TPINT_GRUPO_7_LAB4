package Negocio;

import dao.CuentaDao;
import dao.MovimientoDao;
import entidades.Movimiento;

public class NegTransferencia {
	private CuentaDao dao = new CuentaDao();
	private MovimientoDao movdao = new MovimientoDao();
	
	public Boolean realizarTransferencia(float monto, String cbuInicio, String cbuDestinatario) {
		
		try {
			dao.RemoverMonto(cbuInicio, monto);
			dao.AgregarMonto(cbuDestinatario, monto);
			Movimiento emisor = new Movimiento(); //Agregar parametros
			Movimiento receptor = new Movimiento(); //Agregar parametros
			movdao.AgregarMovimiento(emisor);
			movdao.AgregarMovimiento(receptor);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

}

// Queda agregar movimientos a la transferencia, al alta de cuentas, todos. y despues impolementar la muestra de historial.