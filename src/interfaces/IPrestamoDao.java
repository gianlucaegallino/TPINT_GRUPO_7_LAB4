package interfaces;

import java.util.ArrayList;

import entidades.Prestamo;

public interface IPrestamoDao {

	public ArrayList<Prestamo> listarPrestamosPendientes();
	//Este dao se asegura que PrestamoDao tenga al menos un buscar de pendientes.
}
