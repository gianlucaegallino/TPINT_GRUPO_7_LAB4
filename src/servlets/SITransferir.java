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
import Negocio.NegTransferencia;
import entidades.Cuenta;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import entidades.Usuario;

/**
 * Servlet implementation class SITransferir
 */
@WebServlet("/SITransferir")
public class SITransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    private NegCargarDescolgables negDesc;
    private NegCuentas negCta;
    private NegTransferencia negTransf;

	
    public SITransferir() {
        super();

        negDesc = new NegCargarDescolgables();
        negCta = new NegCuentas();
        negTransf = new NegTransferencia();
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
		String action = request.getParameter("action");

        if ("realizarTransferencia".equals(action)) {

            realizarTransferencia(request, response);

            doGet(request,response);
        } else {
        	//Tirar excepcion!
        }
        

        
	}
	
	private void realizarTransferencia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario

		String cbuUsu = request.getParameter("cuenta");
		
        String cbuDest = request.getParameter("cbu_destinatario");
    	
        double montoATransferir = Double.parseDouble(request.getParameter("monto"));

        double montoActual = Double.parseDouble(request.getParameter("saldo")); 
        

        if (montoATransferir > montoActual) {
        	 request.setAttribute("mensajeError", "No tenes suficiente dinero.");
        	 return;
        }
        
        if (cbuUsu.equals(cbuDest)) {
       	 request.setAttribute("mensajeError", "No puede autotransferirse dinero.");
       	 return;
        }
        
        ArrayList <Cuenta> ctas = negCta.ObtenerCuentaCbu(cbuDest); 
        		
        if (ctas != null && ctas.isEmpty() != true) {

            negTransf.realizarTransferencia(montoATransferir, cbuUsu, cbuDest);

            
            //imprimir mensajes 
        	request.setAttribute("mensajeExito", "Transferencia Realizada Correctamente");
            
        } else {
        	request.setAttribute("mensajeError", "Cbu invalido o inexistente");
        }
        

    }
	
	private void cargarDescolgablesCuentaBanco(HttpServletRequest request) {
		
		//Armamos un array para almacenar las cookies
		  Cookie[] cookies = null;
		  
		  // Consigue las cookies almacenadas en el navegador
		  cookies = request.getCookies();
		  
		  //Defino variables para almacenar el id y las cuentas
		  Integer id = null;
		  ArrayList<Cuenta> cuentas = null;
		  
		if(cookies != null && cookies.length > 1) { // si hay mas cookies que la JSESSIONID, que es seteada automaticamente
			
			//Buscamos la cookie correspondiente en el array, en este caso la llamada idpersona
			  for (int i = 0; i < cookies.length; i++) {
				  
				  //Guardamos la cookie de idpersona
			  	if (cookies[i].getName().equals("IDPersona")){
			  		id = Integer.parseInt(cookies[i].getValue());

			  	}

			  }
			  

		        // Con el id, obtenemos las cuentas bancarias correspondientes
		        cuentas = negDesc.ObtenerLasCuentasBancarias(id);

		        
		  } else {

			  //No encontramos la cookie, todo mal :(
			  request.setAttribute("mensajeError", "Este usuario no tiene un cliente asociado.");

		  }


        if (cuentas != null && !cuentas.isEmpty()) {
            request.setAttribute("cuentas", cuentas); // Establecer el atributo "cuentas"
        } else {
            request.setAttribute("mensajeError", "No hay cuentas.");
        }
        
        //Damos constancia de que esta funcion ya se corrio, sin importar si se devolvieron o no cuentas
        request.setAttribute("mensajeCarga", "Cargadas");
    }

}
