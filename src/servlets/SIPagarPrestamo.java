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
import Negocio.NegCuentas;
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
	public SIPagarPrestamo() {
		super();
		negDesc = new NegCargarDescolgables();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cargar lista 1
		cargarDescolgablesCuentaBanco(request);
		// Cargar lista 2
		cargarDescolgablesPrestamos(request);
		// Devolver valores

		RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

	private void cargarDescolgablesPrestamos(HttpServletRequest request) {

		ArrayList<Prestamo> prestamos = null;

		prestamos = negDesc.ObtenerLosPrestamos(id);


		if (prestamos != null && !prestamos.isEmpty()) {
			request.setAttribute("prestamos", prestamos); // Establecer el atributo "prestamos"
		} else {
			request.setAttribute("mensajeError", "No hay prestamos.");
		}

		// Damos constancia de que esta funcion ya se corrio, sin importar si se
		// devolvieron o no cuentas
		request.setAttribute("mensajeCargaPrest", "Cargadas");
	}

}
