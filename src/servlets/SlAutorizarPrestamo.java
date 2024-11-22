package servlets;

import java.io.IOException;
import entidades.Prestamo;
import java.util.List;

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
        List<Prestamo> prestamosPendientes = negocioPrestamo.obtenerPrestamosPendientes();
        request.setAttribute("prestamosPendientes", prestamosPendientes);
        request.getRequestDispatcher("AutorizarPrestamo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String accion = request.getParameter("accion");

        boolean resultado = false;
        if ("aprobar".equals(accion)) {
            resultado = negocioPrestamo.aprobarPrestamo(Integer.parseInt(id));
        } else if ("rechazar".equals(accion)) {
            resultado = negocioPrestamo.rechazarPrestamo(Integer.parseInt(id));
        }

        String mensaje = resultado ? "Préstamo procesado correctamente" : "Error al procesar el préstamo";
        request.setAttribute("mensaje", mensaje);
        doGet(request, response);
    }
}
