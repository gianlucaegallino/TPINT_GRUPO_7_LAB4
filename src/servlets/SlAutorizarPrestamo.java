package servlets;

import java.io.IOException;
import entidades.Prestamo;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegPrestamo;

@WebServlet("/SlAutorizarPrestamo")
public class SlAutorizarPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NegPrestamo negocioPrestamo = new NegPrestamo();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if("buscarPrestamoPendientes".equals(action)) {
        	buscarPrestamoPendientes(request, response);
        } else if ("aprobar".equals(action)) {
            aprobarPrestamo(request, response);
        } else if ("rechazar".equals(action)) {
            rechazarPrestamo(request, response);
        }
    }

	private void rechazarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
        boolean resultado = negocioPrestamo.aprobarPrestamo(idPrestamo);
        if (resultado) {
            // Redireccionar a una página de éxito
            request.setAttribute("mensajeExito", "Préstamo rechazado correctamente");
            request.getRequestDispatcher("AutorizarPrestamos.jsp").forward(request, response);
        } else {
            // Redireccionar a una página de error
            request.setAttribute("mensajeError", "Ha ocurrido un problema al rechazar el préstamo");
            request.getRequestDispatcher("AutorizarPrestamos.jsp").forward(request, response);
        }
	}

	private void aprobarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
        boolean resultado = negocioPrestamo.aprobarPrestamo(idPrestamo);
        if (resultado) {
            // Redireccionar a una página de éxito
            request.setAttribute("mensajeExito", "Préstamo aprobado correctamente");
            request.getRequestDispatcher("AutorizarPrestamos.jsp").forward(request, response);
        } else {
            // Redireccionar a una página de error
            request.setAttribute("mensajeError", "Ha ocurrido un problema al aprobar el préstamo");
            request.getRequestDispatcher("AutorizarPrestamos.jsp").forward(request, response);
        }
	}

	private void buscarPrestamoPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Entrando al buscar Prestamos");
		 ArrayList<Prestamo> prestamosPendientes = negocioPrestamo.obtenerPrestamosPendientes();
	     System.out.print("Prestamos Pendientes: " + prestamosPendientes);
	     
	     StringBuilder htmlTabla = new StringBuilder();
	     if(prestamosPendientes != null && !prestamosPendientes.isEmpty()) {
	    	 for(Prestamo prestamo : prestamosPendientes) {
	    		 htmlTabla.append("<tr>");
	    		 htmlTabla.append("<td>" + prestamo.getId() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getCliente().getDni() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getCbu_cuenta() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getFecha() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getEstado() + "</td>");
	    		 htmlTabla.append("<td>" + "$ " + String.format("%.2f", prestamo.getImportePedido()) + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getPlazoMeses() + " meses" + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getInteresAnual() + " %" + "</td>");
	    		 double valorXCuota = prestamo.getImportePedido()/prestamo.getPlazoMeses();
	    		 htmlTabla.append("<td>" + "$ " + String.format("%.2f", valorXCuota) + "</td>");
	    		 htmlTabla.append("<td>" + "$ " + prestamo.getImporteConIntereses() + "</td>");
	    		 htmlTabla.append("<td>" + prestamo.getPagos_restantes() + "</td>");
	    		 htmlTabla.append("<td class='acciones-column'>");
        		 htmlTabla.append("<form action='SlAutorizarPrestamo' method='POST'>");
        		 htmlTabla.append("<input type='hidden' name='action' value='aprobar' />");
        		 htmlTabla.append("<input type='hidden' name='idPrestamo' value='" + prestamo.getId() + "' />");
        		 htmlTabla.append("<input type='submit' value='Aprobar' class='aprobarBtn' style='font-size: 1.2em; padding: 10px 20px; border: 1px solid #fff;' />");
        		 htmlTabla.append("</form>");
        		 htmlTabla.append("<form action='SlAutorizarPrestamo' method='POST'>");
        		 htmlTabla.append("<input type='hidden' name='action' value='rechazar' />");
        		 htmlTabla.append("<input type='hidden' name='idPrestamo' value='" + prestamo.getId() + "' />");
        		 htmlTabla.append("<input type='submit' value='Rechazar' class='rechazarBtn' style='font-size: 1.2em; padding: 10px 20px; border: 1px solid #fff;' />");
        		 htmlTabla.append("</form>");
        		 htmlTabla.append("</td>");
	    		 htmlTabla.append("</tr>");
	    	 }
	     }
	     System.out.print("TABLAHTML: " + htmlTabla.toString());
	        // Pasar el HTML de la tabla a la JSP
	     request.setAttribute("tablaHTML", htmlTabla.toString());
	     
	     //request.setAttribute("prestamosPendientes", prestamosPendientes);
	     System.out.print("Salimos del get");
	     request.getRequestDispatcher("AutorizarPrestamos.jsp").forward(request, response);
	}
}
