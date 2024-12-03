package Negocio;

import java.util.Date;

import dao.CuentaDao;
import dao.MovimientoDao;
import entidades.Cuenta;
import entidades.Movimiento;

public class NegTransferencia {
	private CuentaDao dao = new CuentaDao();
	private MovimientoDao movdao = new MovimientoDao();
	
	public Boolean realizarTransferencia(double monto, String cbuInicio, String cbuDestinatario) {
		
		try {
			dao.RemoverMonto(cbuInicio, monto);
			dao.AgregarMonto(cbuDestinatario, monto);
			long normaldate = new Date().getTime();
			java.sql.Date sqlDate = new java.sql.Date(normaldate);
			
			Cuenta cuentainicio = dao.obtenerCuentaCbu(cbuInicio).get(0);
			
			Cuenta cuentadestino = dao.obtenerCuentaCbu(cbuDestinatario).get(0);
			
			Movimiento emisor = new Movimiento(cuentainicio, sqlDate, "Envio a cuenta "+ cuentadestino.getCbu(), monto, "Transferencia Negativa"); //Agregar parametros
			Movimiento receptor = new Movimiento(cuentadestino, sqlDate, "Recibo desde cuenta "+ cuentainicio.getCbu(), monto, "Transferencia Positiva"); //Agregar parametros
			movdao.AgregarMovimiento(emisor);
			movdao.AgregarMovimiento(receptor);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

}

