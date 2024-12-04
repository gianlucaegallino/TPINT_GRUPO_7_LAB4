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

/**
 * Servlet implementation class SIPedirPrestamo
 */
@WebServlet("/SIPedirPrestamo")
public class SIPedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NegPrestamo neg;
	private NegCargarDescolgables negDesc;
	double TASA_INTERES;

	public SIPedirPrestamo() {
		super();
		negDesc = new NegCargarDescolgables();
		neg = new NegPrestamo();
		TASA_INTERES = 6.08; // Tasa de interes mensual, constante

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cargarDescolgablesCuentaBanco(request);
		RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("submit");
		if (act == null) {
			
			// No se selecciono boton
		} else if (act.equals("calcular")) {
			// Variables para el calculo
			double monto = request.getParameter("monto") != null ? Double.parseDouble(request.getParameter("monto"))
					: 0;
			int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 0;

			neg.devolverCalculo(monto, cuotas, TASA_INTERES, request);
			cargarDescolgablesCuentaBanco(request); // Se cargan para que no se pierdan los datos en la recarga

		} else if (act.equals("solicitar")) {
			double monto = request.getParameter("monto") != null ? Double.parseDouble(request.getParameter("monto"))
					: 0;
			int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 0;

			// Armamos un array para almacenar las cookies
			Cookie[] cookies = null;

			// Consigue las cookies almacenadas en el navegador
			cookies = request.getCookies();

			// Defino variables para almacenar el id y las cuentas
			Integer idCliente = null;

			if (cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada
															// automaticamente

				// Buscamos la cookie correspondiente en el array, en este caso la llamada
				// idpersona
				for (int i = 0; i < cookies.length; i++) {

					// Guardamos la cookie de idpersona
					if (cookies[i].getName().equals("IDPersona")) {
						idCliente = Integer.parseInt(cookies[i].getValue());

					}

				}
			}
			
			//Conseguimos el cbu de la cuenta
			String cbuUsu = request.getParameter("cuenta");

			neg.solicitarPrestamo(monto, cuotas, TASA_INTERES, request, idCliente, cbuUsu);
		} else {
			// Tirar excepcion; html alterado
		}
		doGet(request,response);

	}

	private void cargarDescolgablesCuentaBanco(HttpServletRequest request) {

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

		// Damos constancia de que esta funcion ya se corrio, sin importar si se
		// devolvieron o no cuentas
		request.setAttribute("mensajeCarga", "Cargadas");
	}

}
