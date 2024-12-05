package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegInformesEstadisticos;

@WebServlet("/SvInformesEstadisticos")
public class SvInformesEstadisticos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SvInformesEstadisticos() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String action = request.getParameter("action");
		
		if("PorcentajeMovsAlta".equals(action)) {
			System.out.print("Entrando a PorcentajeMovsAlta");
			PorcentajeMovsAlta(request,response);
		}
	}

	private void PorcentajeMovsAlta(HttpServletRequest request, HttpServletResponse response) {
		try {
			// Obtener las fechas de la solicitud
			String fechaDesde = request.getParameter("fechadesdeInput");
			String fechaHasta = request.getParameter("fechahastaInput");
			
			System.out.print("fechadesde: " + fechaDesde);
			System.out.print("fechahasta: " + fechaHasta);
			
			// Convertir las fechas al formato SQL Date
			java.sql.Date dateDesde = java.sql.Date.valueOf(fechaDesde);
	        java.sql.Date dateHasta = java.sql.Date.valueOf(fechaHasta);
	        
	        System.out.print("fechadesdeSQL: " + dateDesde);
			System.out.print("fechahastaSQL: " + dateHasta);
			
			NegInformesEstadisticos NegInform = new NegInformesEstadisticos();
			int totalAltaCuenta = NegInform.contarMovimientosAltaCuenta(dateDesde, dateHasta);
			int totalMovimientos = NegInform.contarTodosMovimientos(dateDesde, dateHasta);
			
			System.out.print("TotalAltaCuenta: " + totalAltaCuenta);
			System.out.print("TotalMovimientos: " + totalMovimientos);
			
			// Calcular el porcentaje
			double porcentaje = 0;
			if (totalMovimientos > 0) {
				porcentaje = ((double) totalAltaCuenta / totalMovimientos) * 100;
			}
			
			System.out.print("Porcentaje: " + porcentaje);
			
			// Establecer el porcentaje en el atributo de la solicitud
			request.setAttribute("fechainicio", dateDesde.toString());
			request.setAttribute("fechafinal", dateHasta.toString());
			request.setAttribute("porcentaje", porcentaje);
			
			// Redireccionar la solicitud a la página JSP
			RequestDispatcher rd = request.getRequestDispatcher("/InformesEstadisticos.jsp");
            rd.forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejar el error
	    }
	}
}
