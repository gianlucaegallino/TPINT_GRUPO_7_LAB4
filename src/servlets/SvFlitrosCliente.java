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
	private String nombreFiltro = null; // Variable para almacenar el nombre del filtro

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
		}else if("FiltrarXdniClientes".equals(filtro)) {
			filtrarXdniClientes(request, response);
		}else if("FiltrarXcuilClientes".equals(filtro)) {
			filtrarXcuilClientes(request,response);
		}else if("FiltrarXNombreClientes".equals(filtro)) {
			filtrarXNombreClientes(request,response);
		}else if("FiltrarXApellidoClientes".equals(filtro)) {
			filtrarXApellidoClientes(request,response);
		}
	}


	private void filtrarXApellidoClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apellido = request.getParameter("ApellidoAfiltrar");

        StringBuilder htmlTabla = new StringBuilder();

        // Si hay un filtro de nombre activo, filtra por apellido solo en esos registros
        if (nombreFiltro != null) {
            ArrayList<Cliente> listaClientesApellido = negCliente.ARRAYbuscarClientesPorAPELLIDO(apellido, nombreFiltro);
            if (listaClientesApellido != null && !listaClientesApellido.isEmpty()) {
                for (Cliente cliente : listaClientesApellido) {
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
        } else { // Si no hay filtro de nombre activo, filtra por apellido en todos los registros
            ArrayList<Cliente> listaClientesApellido = negCliente.ARRAYbuscarClientesPorAPELLIDO(apellido);
            if (listaClientesApellido != null && !listaClientesApellido.isEmpty()) {
                for (Cliente cliente : listaClientesApellido) {
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
        }
        
        System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
		
	}


	private void filtrarXNombreClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("NombreAfiltrar");
		System.out.print("INPUT: " + nombre);
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorNOMBRE(nombre);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
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
		}else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
		
		System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
		
        // Guardar el valor del nombre filtrado en la variable nombreFiltro
        nombreFiltro = nombre; 
	}


	private void filtrarXcuilClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cuil = request.getParameter("cuilAfiltrar");
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorCUIL(cuil);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
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
		}else {
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


	private void filtrarXdniClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dniAfiltrar");
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorDNI(dni);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
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
		}else {
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
