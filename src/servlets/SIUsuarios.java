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
import Negocio.NegUsuario;
import entidades.Cliente;
import entidades.Usuario;

/**
 * Servlet implementation class SIUsuarios
 */
@WebServlet("/SIUsuarios")
public class SIUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegUsuario negUsu;

    public SIUsuarios() {
        super();
        negUsu = new NegUsuario();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos los clientes que tengan su id Usuario en NULL
		System.out.print("GET");
		NegCliente negocioCli = new NegCliente();
		ArrayList<Cliente> listaClientesinIDUsuario= negocioCli.obtenerIDUsuarioVacio();
		System.out.print("Clientes encontrados: " + listaClientesinIDUsuario);
		StringBuilder desplegableHTML = new StringBuilder();
		
		if(listaClientesinIDUsuario != null && !listaClientesinIDUsuario.isEmpty()) {
			System.out.print("listaClientes tiene algo");
			for(Cliente user : listaClientesinIDUsuario) {
				System.out.print("recorriendo el for");
				 desplegableHTML.append("<option value=" + user.getIdCliente() + ">" + "Cliente: " + user.getNombre() + " " + user.getApellido() + " | DNI: " + user.getDni() + "</option>");
			}
		}
		
		System.out.print("desplegable" + desplegableHTML.toString());
		request.setAttribute("desplegableHTML", desplegableHTML.toString());
		request.setAttribute("listaClientes", listaClientesinIDUsuario);
		System.out.print("Saliendo GET");
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
	    rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

        if ("agregarUsuario".equals(action)) {
            agregarUsuario(request, response);
        } else if("asignarUsuario".equals(action)) {
        	
        	//Tirar excepcion!
        }
	}

	private void agregarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        String user = request.getParameter("UsernameUsuario");
        String pwd = request.getParameter("ContrasenaUsuario");
        int tipo = Integer.parseInt(request.getParameter("TipoUsuario"));


        String msj = validarDatos(request, user, pwd, tipo);
        // Validar los datos
        if (msj == null) {
        	// Crear la instancia de usuario
            Usuario us = new Usuario();
            us.setUsuario(user);
            us.setContrasena(pwd);
            us.setTipo_usuario(tipo);


            // Agregar el usuario a la base de datos
            int resultado = negUsu.AgregarUsuario(us);

            // Redirigir a la JSP de agregar usuar con un mensaje de éxito o error
            if (resultado == 0) {
                request.setAttribute("mensajeExito", "�Cliente agregado correctamente!");
            } else {
                request.setAttribute("mensajeError", "Error al agregar el cliente.");
            }
        } else {
        	request.setAttribute("mensajeError", msj);
        	System.out.println(msj);
        }

        

        RequestDispatcher rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
        rd.forward(request, response);
    }
	
	 private String validarDatos(HttpServletRequest request, String user, String pwd, int tipo) {
	        // Validación del usuario
	        if (user == null || user.isEmpty()) {
	            return "Nombre Usuario Vacio.";
	        }

	        // Validación de la contrase�a
	        if (pwd == null || pwd.isEmpty()) {
	            return "Contrasena vacia.";
	        }

	        // Validacion del tipo
	        if (tipo != 1 && tipo != 2) {
	            return "Tipo Invalido.";
	        }
	        
	        //Verificacion de usuario no existente
	        if (negUsu.verificarExistenciaPorNombre(user) != 0) {
	            return "Ese nombre de usuario ya existe.";
	        }

	        return null;
	    }
}
