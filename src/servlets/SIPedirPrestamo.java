package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegPrestamo;

/**
 * Servlet implementation class SIPedirPrestamo
 */
@WebServlet("/SIPedirPrestamo")
public class SIPedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	NegPrestamo neg;
	double TASA_INTERES;

    public SIPedirPrestamo() {
        super();
        neg = new NegPrestamo();
        TASA_INTERES = 0.50; // Tasa de interes anual, constante

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("submit");
		if (act == null) {
		    //No se selecciono boton
		} else if (act.equals("calcular")) {
			// Variables para el calculo
			double monto = request.getParameter("monto") != null ? Double.parseDouble(request.getParameter("monto")) : 0;
			int cuotas = request.getParameter("cuotas") != null ? Integer.parseInt(request.getParameter("cuotas")) : 0;

		   neg.devolverCalculo(monto, cuotas, TASA_INTERES, request);

		   
		} else if (act.equals("solicitar")) {
		   neg.solicitarPrestamo(monto, cuotas, TASA_INTERES, request);
		} else {
		    //Tirar excepcion; html alterado
		}
		
        RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamo.jsp");
        rd.forward(request, response);
	}

}
