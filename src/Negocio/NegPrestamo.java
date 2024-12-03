package Negocio;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.PrestamoDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;

public class NegPrestamo {
    private PrestamoDao prestamoDao = new PrestamoDao();

    public List<Prestamo> obtenerPrestamosPendientes() {
        return prestamoDao.listarPrestamosPendientes();
    }

    public boolean aprobarPrestamo(int id) {
        Prestamo prestamo = prestamoDao.obtenerPorNumero(id);
        if (prestamo != null) {
            prestamo.setEstado("aprobado");
            return prestamoDao.actualizar(prestamo);
        }
        return false;
    }

    public boolean rechazarPrestamo(int id) {
        Prestamo prestamo = prestamoDao.obtenerPorNumero(id);
        if (prestamo != null) {
            prestamo.setEstado("rechazado");
            return prestamoDao.actualizar(prestamo);
        }
        return false;
    }

	public void devolverCalculo(double monto, int cuotas, double TASA_INTERES, HttpServletRequest request) {
		// Calculo del interes total en porcentaje
		double interesTotal = realizarCalculointeresTotal(cuotas, TASA_INTERES);
		double totalConInteres = realizarCalculototalConInteres(monto, interesTotal);
		double cuotaMensual = realizarCalculocuotaMensual(cuotas, totalConInteres);

		//Marcado de parametros
		request.setAttribute("interesTotal", interesTotal);
		request.setAttribute("totalConInteres", totalConInteres);
		request.setAttribute("cuotaMensual", cuotaMensual);
		request.setAttribute("TASA_INTERES", TASA_INTERES);
		request.setAttribute("monto", monto);
		request.setAttribute("cuotas", cuotas);

		return;
	}

	public void solicitarPrestamo(double monto, int cuotas, double TASA_INTERES, HttpServletRequest request, int idCliente) {
		// Calculo del interes total en porcentaje
		double interesTotal = realizarCalculointeresTotal(cuotas, TASA_INTERES);
		double totalConInteres = realizarCalculototalConInteres(monto, interesTotal);
		double cuotaMensual = realizarCalculocuotaMensual(cuotas, totalConInteres);

		
		
		Prestamo prestamo = new Prestamo(1, new Date(), monto, totalConInteres, cuotas, cuotaMensual, "pendiente", TASA_INTERES*12, new Cliente(idcliente));
		//Solicitado Prestamo
		prestamoDao.AgregarPrestamo(prestamo);
	}
	
	private double realizarCalculointeresTotal(int cuotas, double TASA_INTERES){
		return (TASA_INTERES * cuotas); // Interes total acumulado en porcentaje
	}
	
	private double realizarCalculototalConInteres(double monto, double interesTotal){
		return monto * (1 + (interesTotal / 100)); // Calculo del monto total a pagar
	}
	
	private double realizarCalculocuotaMensual(int cuotas, double totalConInteres){
		return (cuotas > 0) ? totalConInteres / cuotas : 0; // Evitar division por cero
	}
}
