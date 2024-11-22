package Negocio;

import java.util.List;
import dao.PrestamoDao;
import entidades.Prestamo;

public class NegPrestamo {
    private PrestamoDao prestamoDao = new PrestamoDao();

    public List<Prestamo> obtenerPrestamosPendientes() {
        return prestamoDao.listarPrestamosPendientes();
    }

    public boolean aprobarPrestamo(int id) {
        Prestamo prestamo = prestamoDao.obtenerPorNumero(id);
        if (prestamo != null) {
            prestamo.setEstado("aprobado");
            return prestamoDao.actualizar(prestamo);
        }
        return false;
    }

    public boolean rechazarPrestamo(int id) {
        Prestamo prestamo = prestamoDao.obtenerPorNumero(id);
        if (prestamo != null) {
            prestamo.setEstado("rechazado");
            return prestamoDao.actualizar(prestamo);
        }
        return false;
    }
}
