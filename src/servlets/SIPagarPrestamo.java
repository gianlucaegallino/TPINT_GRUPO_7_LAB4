package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCargarDescolgables;
import Negocio.NegPrestamo;
import entidades.Cuenta;
import entidades.Prestamo;

/**
 * Servlet implementation class SIPagarPrestamo
 */
@WebServlet("/SIPagarPrestamo")
public class SIPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	NegCargarDescolgables negDesc;
	NegPrestamo negpr;
	public SIPagarPrestamo() {
		super();
		negDesc = new NegCargarDescolgables();
		negpr = new NegPrestamo();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cargar lista
		cargarDescolgables(request);

		// Devolver valores

		RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int cantCuotasApagar = Integer.parseInt(request.getParameter("cantcuotasapagar"));
		String cbuDePago = request.getParameter("cuenta");
		double precioUnaCuota = Double.parseDouble(request.getParameter("costocuota"));
		int CuotasRestantes = Integer.parseInt(request.getParameter("cuotasrestantes"));
		double dinerodisponible = Double.parseDouble(request.getParameter("saldo"));
		int iddeuda = Integer.parseInt(request.getParameter("iddeuda"));
		
		double costoDelPago = cantCuotasApagar * precioUnaCuota;
				
		cargarDescolgables(request);
				
		if (cantCuotasApagar > CuotasRestantes || cantCuotasApagar <= 0) {
			//Dar error
			RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (costoDelPago > dinerodisponible) {
			//Dar error
			RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamo.jsp");
			rd.forward(request, response);
			return;
		}
		
		//Realizar pago deuda
		negpr.realizarPago(cbuDePago, cantCuotasApagar, costoDelPago, iddeuda);
		
		

	}

	private void cargarDescolgables(HttpServletRequest request) {

		// Armamos un array para almacenar las cookies
		Cookie[] cookies = null;

		// Consigue las cookies almacenadas en el navegador
		cookies = request.getCookies();

		// Defino variables para almacenar el id y las cuentas
		Integer id = null;
		ArrayList<Cuenta> cuentas = null;

		if (cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada
														// automaticamente

			// Buscamos la cookie correspondiente en el array, en este caso la llamada
			// idpersona
			for (int i = 0; i < cookies.length; i++) {

				// Guardamos la cookie de idpersona
				if (cookies[i].getName().equals("IDPersona")) {
					id = Integer.parseInt(cookies[i].getValue());

				}

			}

			// Con el id, obtenemos las cuentas bancarias correspondientes
			cuentas = negDesc.ObtenerLasCuentasBancarias(id);

		} else {

			// No encontramos la cookie, todo mal :(
			request.setAttribute("mensajeError", "Este usuario no tiene un cliente asociado.");

		}

		if (cuentas != null && !cuentas.isEmpty()) {
			request.setAttribute("cuentas", cuentas); // Establecer el atributo "cuentas"
		} else {
			request.setAttribute("mensajeError", "No hay cuentas.");
		} 
			

		cargarDescolgablesPrestamos(request, id);
		
			

		// Damos constancia de que esta funcion ya se corrio, sin importar si se
		// devolvieron o no cuentas
		request.setAttribute("mensajeCarga", "Cargadas");
	}

	private void cargarDescolgablesPrestamos(HttpServletRequest request, int id) {

		ArrayList<Prestamo> prestamos = null;

		prestamos = negDesc.ObtenerLosPrestamos(id);



		if (prestamos != null && !prestamos.isEmpty()) {
			request.setAttribute("prestamos", prestamos); // Establecer el atributo "prestamos"
		} else {
			request.setAttribute("mensajeError", "No hay prestamos.");
		}

		// Damos constancia de que esta funcion ya se corrio, sin importar si se
		// devolvieron o no prestamos
		request.setAttribute("mensajeCargaPrest", "Cargadas");
	}

}
