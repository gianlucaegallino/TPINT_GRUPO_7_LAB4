package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCargarDescolgables;
import Negocio.NegUsuario;
import dao.CargarDescolgablesDao;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Usuario;

/**
 * Servlet implementation class SIUsuarios
 */
@WebServlet("/SIUsuarios")
public class SIUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegUsuario negUsu;
	private NegCargarDescolgables negDesc;

    public SIUsuarios() {
        super();
        negDesc = new NegCargarDescolgables();
        negUsu = new NegUsuario();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cargarDescolgables(request);
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
		
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

        if ("agregarUsuario".equals(action)) {
            agregarUsuario(request, response);
        } else if("asignarUsuario".equals(action)) {
        	asignarUsuario(request, response);
        }	//Tirar excepcion!
	}
	
	private void asignarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clienteId = Integer.parseInt(request.getParameter("AsignarUsuarioaCuenta"));
		System.out.print("el valor del select de cliente es: " + clienteId + " ");
		
		int usuarioId = Integer.parseInt(request.getParameter("AsignarUsuarioaCuenta2"));
		System.out.print("el id usuario seleccionado es: " + usuarioId);
		
		boolean asignarUsuario = negDesc.asignarUsuarioACliente(usuarioId, clienteId);		
		
		// Validation and message handling
	    if (asignarUsuario) {
	        // Success message
	        request.setAttribute("mensajeExito", "Usuario asignado al cliente correctamente.");
	    } else {
	        // Error message
	        request.setAttribute("mensajeError", "Error al asignar usuario al cliente. Intente nuevamente.");
	    }
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/AgregarUsuario.jsp");
        rd.forward(request, response);
	}

	private void cargarDescolgables(HttpServletRequest request)
            throws ServletException, IOException {
				// Obtenemos los clientes que tengan su id Usuario en NULL
				System.out.print("GET");
				ArrayList<Cliente> listaClientesinIDUsuario = null;
				ArrayList<Usuario> listaUsuarios = null;
				
				listaClientesinIDUsuario= negDesc.obtenerIDUsuarioVacio();
				listaUsuarios = negDesc.obtenerUsuarios();
				System.out.print("Clientes encontrados: " + listaClientesinIDUsuario);
				
				if(listaClientesinIDUsuario != null && !listaClientesinIDUsuario.isEmpty()) {
					request.setAttribute("listaClientes", listaClientesinIDUsuario);
				}else {
		            request.setAttribute("mensajeError", "No hay clientes.");
		        }
				if(listaUsuarios != null && !listaUsuarios.isEmpty()) {
					request.setAttribute("listaUsuarios", listaUsuarios);
				}else {
		            request.setAttribute("mensajeError", "No hay usuarios.");
		        }
				
				//Damos constancia de que esta funcion ya se corrio, sin importar si se devolvieron o no cuentas
		        request.setAttribute("mensajeCarga", "Cargadas");
				System.out.print("Saliendo GET");
				
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
