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
        RequestDispatcher rd = request.getRequestDispatcher("/Transferir.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private void cargarDescolgablesCuentaBanco(HttpServletRequest request) {
		
		//Agarrar el id de cliente de cookies
		  Cookie[] cookies = null;
		  
		  cookies = request.getCookies();
		  
		  Integer id = null;
		  
		  ArrayList<Cuenta> cuentas = null;
		  
		if(cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada automaticamente
			  for (int i = 0; i < cookies.length; i++) {
				
			  	if (cookies[i].getName().equals("IDPersona")){
			  		id = Integer.parseInt(cookies[i].getValue());
			  		System.out.println(id);
			  		System.out.println("boob");
			  	}

			  }
			  
			  System.out.println("Im in");
		        // Obtener la lista de provincias y pasarla al JSP
		        cuentas = negDesc.ObtenerLasCuentasBancarias(id);
			System.out.println("Im out");
		        
		  } else {

			  request.setAttribute("mensajeError", "Este usuario no tiene un cliente asociado.");

		  }


        if (cuentas != null && !cuentas.isEmpty()) {
            request.setAttribute("cuentas", cuentas); // Establecer el atributo "cuentas"
        } else {
            request.setAttribute("mensajeError", "No hay cuentas.");
        }
        request.setAttribute("mensajeCarga", "Cargadas");
    }

}
