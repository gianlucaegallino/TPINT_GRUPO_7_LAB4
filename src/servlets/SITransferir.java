package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCargarDescolgables;
import Negocio.NegCuentas;

/**
 * Servlet implementation class SITransferir
 */
@WebServlet("/SITransferir")
public class SITransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private NegCuentas negCuenta;
    private NegCargarDescolgables negDesc;

	
    public SITransferir() {
        super();
        negCuenta = new NegCuentas(); 
        negDesc = new NegCargarDescolgables();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        cargarDescolgablesCuentaBanco(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
