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
		} else if("MontoPromedioPrestamo".equals(action)) {
			System.out.print("Entrando a MontoPromedioPrestamo");
			MontoPromedioPrestamo(request,response);
		} else if("PorcentajePrestamosAprobados".equals(action)) {
			PorcentajePrestamosAprobados(request,response);
		}
	}

	private void PorcentajePrestamosAprobados(HttpServletRequest request, HttpServletResponse response) {
		try {
            // Obtener las fechas de la solicitud
            String fechaDesde = request.getParameter("fechadesdeInput3");
            String fechaHasta = request.getParameter("fechahastaInput3");

            System.out.print("fechadesde: " + fechaDesde);
            System.out.print("fechahasta: " + fechaHasta);

            // Convertir las fechas al formato SQL Date
            java.sql.Date dateDesde = java.sql.Date.valueOf(fechaDesde);
            java.sql.Date dateHasta = java.sql.Date.valueOf(fechaHasta);

            System.out.print("fechadesdeSQL: " + dateDesde);
            System.out.print("fechahastaSQL: " + dateHasta);

            NegInformesEstadisticos NegInform = new NegInformesEstadisticos();
            double porcentajeAprobados = NegInform.calcularPorcentajePrestamosAprobados(dateDesde, dateHasta);

            System.out.print("PorcentajeAprobados: " + porcentajeAprobados);

            // Establecer el porcentaje en el atributo de la solicitud
            request.setAttribute("fechainicio3", dateDesde.toString());
            request.setAttribute("fechafinal3", dateHasta.toString());
            request.setAttribute("porcentajeAprobados", porcentajeAprobados);

            // Redireccionar la solicitud a la página JSP
            RequestDispatcher rd = request.getRequestDispatcher("/InformesEstadisticos.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error
        }
	}

	private void MontoPromedioPrestamo(HttpServletRequest request, HttpServletResponse response) {
		try {
            // Obtener las fechas de la solicitud
            String fechaDesde = request.getParameter("fechadesdeInput2");
            String fechaHasta = request.getParameter("fechahastaInput2");

            System.out.print("fechadesde: " + fechaDesde);
            System.out.print("fechahasta: " + fechaHasta);

            // Convertir las fechas al formato SQL Date
            java.sql.Date dateDesde = java.sql.Date.valueOf(fechaDesde);
            java.sql.Date dateHasta = java.sql.Date.valueOf(fechaHasta);

            System.out.print("fechadesdeSQL: " + dateDesde);
            System.out.print("fechahastaSQL: " + dateHasta);

            NegInformesEstadisticos NegInform = new NegInformesEstadisticos();
            double promedioMontoPrestamo = NegInform.calcularMontoPromedioPrestamo(dateDesde, dateHasta);

            System.out.print("PromedioMontoPrestamo: " + promedioMontoPrestamo);

            // Establecer el promedio en el atributo de la solicitud
            request.setAttribute("fechainicio2", dateDesde.toString());
            request.setAttribute("fechafinal2", dateHasta.toString());
            request.setAttribute("promedioMontoPrestamo", promedioMontoPrestamo);

            // Redireccionar la solicitud a la página JSP
            RequestDispatcher rd = request.getRequestDispatcher("/InformesEstadisticos.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error
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
