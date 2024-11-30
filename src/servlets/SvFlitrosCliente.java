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
import entidades.Cliente;


@WebServlet("/SvFlitrosCliente")
public class SvFlitrosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegCliente negCliente;

    public SvFlitrosCliente() {
        super();
        negCliente = new NegCliente();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DECLARAMOS LA VARIABLE 
		String filtro = request.getParameter("filtro");
		
		if("mostrarClientes".equals(filtro)) {
			mostrarClientes(request, response);
		}
	}


	private void mostrarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener la lista de clientes
        ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();
        
        // Construir el HTML de la tabla
        StringBuilder htmlTabla = new StringBuilder();
        htmlTabla.append("<tbody>");
        for (Cliente cliente : listaClientes) {
            htmlTabla.append("<tr>");
	            htmlTabla.append("<td>").append(cliente.getDni()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getCuil()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getNombre()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getApellido()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getSexo().getDescripcion()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getNacionalidad().getNombre()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getFecha_nacimiento()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getDireccion().getDireccion()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getCorreo_electronico()).append("</td>");
	            htmlTabla.append("<td>").append(cliente.getTelefono()).append("</td>");
            htmlTabla.append("</tr>");
        }
        htmlTabla.append("</tbody>");

        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
	}

}
