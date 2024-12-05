package interfaces;

import java.util.ArrayList;

import entidades.Movimiento;

public interface IMovimientoDao {

	public ArrayList<Movimiento> TraerListaMovimiento(int id);
	//Este dao se asegura que MovimientoDao tenga al menos un buscar con una caract. unica.
}
