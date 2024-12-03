 package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCliente;

import javax.servlet.http.Cookie;

import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Cuenta;

@WebServlet("/SIDatos")
public class SIDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SIDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("accion")=="buscarDatos") {
		
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
				  

			        // Con el id, obtenemos el cliente correspondientes
					NegCliente neg = new NegCliente();
					Cliente cli = neg.conseguirClienteporId(id);
					request.setAttribute("DatosUsuario", cli);

			        
			  } else {

				  //No encontramos la cookie, todo mal :(
				  request.setAttribute("mensajeError", "Este usuario no tiene un cliente asociado.");

			  }
		

		

		
		RequestDispatcher rd= request.getRequestDispatcher("/InformacionCliente.jsp");
		rd.forward(request, response);
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}