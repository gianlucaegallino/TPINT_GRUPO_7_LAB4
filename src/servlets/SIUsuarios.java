package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegUsuario;

import entidades.Usuario;

/**
 * Servlet implementation class SIUsuarios
 */
@WebServlet("/SIUsuarios")
public class SIUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegUsuario negUsu;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SIUsuarios() {
        super();
        negUsu = new NegUsuario();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

        if ("agregarUsuario".equals(action)) {
            agregarUsuario(request, response);
        } else {
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

            // Redirigir a la JSP de agregar usuar con un mensaje de √©xito o error
            if (resultado == 0) {
                request.setAttribute("mensajeExito", "°Cliente agregado correctamente!");
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
	        // Validaci√≥n del usuario
	        if (user == null || user.isEmpty()) {
	            return "Nombre Usuario Vacio.";
	        }

	        // Validaci√≥n de la contraseÒa
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
