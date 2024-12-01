package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCargarDescolgables;
import Negocio.NegCuentas;
import entidades.Cuenta;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;

/**
 * Servlet implementation class SITransferir
 */
@WebServlet("/SITransferir")
public class SITransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    private NegCargarDescolgables negDesc;

	
    public SITransferir() {
        super();

        negDesc = new NegCargarDescolgables();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        cargarDescolgablesCuentaBanco(request);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void cargarDescolgablesCuentaBanco(HttpServletRequest request) {
		
		//Agarrar el id de cliente de cookies
		  Cookie[] cookies = null;
		  
		  cookies = request.getCookies();
		  
		  Integer id = null;
		  
		if(cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada automaticamente
			  for (int i = 0; i < cookies.length; i++) {
				
			  	if (cookies[i].getName().equals("IdPersona")){
			  		id = Integer.parseInt(cookies[i].getValue());
			  	}

			  }
		  } else {

			  request.setAttribute("mensajeError", "Este usuario no tiene un cliente asociado.");

		  }

        // Obtener la lista de provincias y pasarla al JSP
        ArrayList<Cuenta> cuentas = negDesc.ObtenerLasCuentasBancarias();
        if (cuentas != null && !cuentas.isEmpty()) {
            request.setAttribute("cuentas", cuentas); // Establecer el atributo "Provincia"
        } else {
            request.setAttribute("mensajeError", "No hay cuentas.");
        }
        request.setAttribute("mensajeCarga", "Cargadas");
    }

}
