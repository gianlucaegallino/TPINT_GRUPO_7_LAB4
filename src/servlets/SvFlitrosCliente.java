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
		String filtro = request.getParameter("action");
		
		if("mostrarClientes".equals(filtro)) {
			mostrarClientes(request, response);
		}
	}


	private void mostrarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("ENTRAMOS AL MOSTRAR");        
        // Obtener la lista de clientes
        ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();

        // Construir el HTML de la tabla
        StringBuilder htmlTabla = new StringBuilder();
        if (listaClientes != null && !listaClientes.isEmpty()) {
            for (Cliente cliente : listaClientes) {
                htmlTabla.append("<tr>");
                htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                htmlTabla.append("</tr>");
            }
        } else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
        
        System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
	}

}
