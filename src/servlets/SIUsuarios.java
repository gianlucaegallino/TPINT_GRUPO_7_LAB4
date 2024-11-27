package servlets;

import java.io.IOException;

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


        /* AGREGAR VALIDACION
        // Validar los datos
        if (!validarDatos(request, dni, cuil, nombre, apellido, telefono, sexoId, nacionalidadId, fechaNacimiento,
                localidadId, direccion, correo)) {
            return; 
        }*/

        // Crear la instancia de usuario
        Usuario us = new Usuario();
        us.setUsuario(user);
        us.setContrasena(pwd);
        us.setTipo_usuario(tipo);


        // Agregar el cliente a la base de datos
        int resultado = negUsu.AgregarUsuario(us);

        // Redirigir a la JSP de agregar cliente con un mensaje de éxito o error
        if (resultado == 0) {
            request.setAttribute("mensajeExito", "¡Cliente agregado correctamente!");
        } else {
            request.setAttribute("mensajeError", "Error al agregar el cliente.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/ClientesAgregar.jsp");
        rd.forward(request, response);
    }
}
