package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegUsuario;
import entidades.Usuario;
import entidades.Cliente;
import Negocio.NegCliente;

/**
 * Servlet implementation class SIniciarSesion
 */
@WebServlet("/SIniciarSesion")
public class SIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SIniciarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("btnInicioSesion") != null) {
			// Extrae parametros
			String nomUsuario = request.getParameter("nomUsu");
			String contUsuario = request.getParameter("password");

			// Verifica Parametros
			if (nomUsuario != null && contUsuario != null) {
				// Obtiene Usuario

				NegUsuario negUsuario = new NegUsuario();
				Usuario usuario = negUsuario.conseguirUsuarioPorCredenciales(nomUsuario, contUsuario);

				if (usuario.getUsuario() != null) {
					
					// Redirige en base al tipo de usuario.
					int tipoUsuario = usuario.getTipo_usuario();
					String redireccion = (tipoUsuario == 2) ? "InicioUsuarioBanco.jsp" : "Inicio.jsp";
					
					//Obtiene los datos de cliente, si es un tipo cliente
					if (tipoUsuario != 2) {
						//Implementar
						Cliente cli = new Cliente();
						NegCliente negcli = new NegCliente();
						
					}
					
					// Arma una cookie con los datos de usuario y cliente, y las agrega a la response
					Cookie ckNombre;
					Cookie ckNombrePersona;
					Cookie ckApellidoPersona;
					
					ckNombre = new Cookie("NombreUsuario", usuario.getUsuario());
					
					if (tipoUsuario == 2) {
						ckNombrePersona = new Cookie("NombrePersona", "Administrador");
						ckApellidoPersona = new Cookie("ApellidoPersona", "Banco");
					} else {
						ckNombrePersona = new Cookie("NombrePersona", "Franco");
						ckApellidoPersona = new Cookie("ApellidoPersona", "Colapinto");
					}

					// Suma las cookies a la response
					response.addCookie(ckNombre);
					response.addCookie(ckNombrePersona);
					response.addCookie(ckApellidoPersona);

					// Redirige a donde corresponde
					RequestDispatcher rd = request.getRequestDispatcher("/" + redireccion);
					rd.forward(request, response);
				} else {
					request.setAttribute("mensaje", "error");
					RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}
			}
		}

	}

}
