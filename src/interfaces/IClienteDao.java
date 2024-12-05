package interfaces;

import java.util.ArrayList;

import entidades.Cliente;

public interface IClienteDao {

	public ArrayList<Cliente> obtenerTodosLosClientes();
	//Este dao se asegura que ClienteDao tenga al menos un buscar.
}
