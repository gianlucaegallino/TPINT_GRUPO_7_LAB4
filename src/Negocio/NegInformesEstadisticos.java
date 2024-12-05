package Negocio;

import java.util.Date;

import dao.InformesEstadisticosDao;

public class NegInformesEstadisticos {
	private InformesEstadisticosDao inform;

	public NegInformesEstadisticos() {
		super();
		// TODO Auto-generated constructor stub
		this.inform = new InformesEstadisticosDao();
	}
	
	// METODOS
	public int contarMovimientosAltaCuenta(Date fechaDesde, Date fechaHasta) {
		return inform.contarMovimientosAltaCuenta(fechaDesde, fechaHasta);
	}
	
	public int contarTodosMovimientos(Date fechaDesde, Date fechaHasta) {
		return inform.contarTodosMovimientos(fechaDesde, fechaHasta);
	}
	public double calcularMontoPromedioPrestamo(Date fechaDesde, Date fechaHasta) {
        return inform.calcularMontoPromedioPrestamo(fechaDesde, fechaHasta);
    }
	public double calcularPorcentajePrestamosAprobados(Date fechaDesde, Date fechaHasta) {
        return inform.calcularPorcentajePrestamosAprobados(fechaDesde, fechaHasta);
    }
}
