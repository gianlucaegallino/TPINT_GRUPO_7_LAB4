package interfaces;

import java.util.ArrayList;

import entidades.Cuenta;

public interface ICuentaDao {

	public ArrayList<Cuenta> obtenerLasCuentas();
	//Este dao se asegura que CuentaDao tenga al menos un buscar.
}
