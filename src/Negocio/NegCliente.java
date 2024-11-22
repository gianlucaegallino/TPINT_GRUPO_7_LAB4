package Negocio;

import java.util.ArrayList;

import dao.ClienteDao;
import entidades.Cliente;

public class NegCliente {

	ClienteDao cnt;

	// Constructor para inicializar cnt
	public NegCliente() {
		this.cnt = new ClienteDao(); // Inicializa cnt aquí
	}

	public int AgregarCliente(Cliente cliente) {
		int datoRep=cnt.verificarDatosRepetido(cliente);
		if (datoRep!=0) {
			return datoRep;
		}
		return cnt.AgregarCliente(cliente);
	}

	public ArrayList<Cliente> obtenerTodosLosClientes() {
		return cnt.obtenerTodosLosClientes();
	}
	

	/*
	 * public int AgregarCliente(Cliente cliente, String username ) { UsuarioDao
	 * usu= new UsuarioDao(); int idUsuario=0; idUsuario =
	 * usu.ObtenerIdUsuario(username); return cnt.AgregarCliente(cliente,
	 * idUsuario); }/*
	 * 
	 * 
	 * 
	 * /*public ArrayList<Cliente> obtenerTodosLosClientes() { return
	 * cnt.obtenerTodosLosClientes(); }
	 */
	  public ArrayList<Cliente> ObtenerUnCliente(String dni) { 
		 ArrayList<Cliente>lista = new ArrayList<Cliente>();
		 ArrayList<Cliente> list1 =cnt.obtenerTodosLosClientes(); 
		 for (Cliente cliente : list1) {
			if(cliente.getDni().equals(dni)) {
			lista.add(cliente);
			} 
		}
		 return lista;
	}
	 

	public int EliminarCliente(String dni) {
		return cnt.EliminarCliente(dni);
	}

	public Cliente conseguirClientePorDni(String clienteDNI) {
		ClienteDao cDao = new ClienteDao();
		return cDao.buscarClientePorDNI(clienteDNI);
	}

	public boolean modificarCliente(Cliente cliente) {
		ClienteDao cDao = new ClienteDao();
		return cDao.modificarCliente(cliente);
	}
}
