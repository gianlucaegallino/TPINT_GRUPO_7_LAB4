package Negocio;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public void traerDatos(HttpServletRequest request, HttpServletResponse response, String cBU) {
		System.out.println("traerdatos negt");
		int id = dao.obtenerCuentaCbu(cBU).get(0).getNumero_cuenta();
		ArrayList<Movimiento> lista = movdao.TraerListaMovimiento(id);
		request.setAttribute("movimientos", lista);
		return;
	}

}

