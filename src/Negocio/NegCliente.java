package Negocio;

import java.util.ArrayList;

import dao.ClienteDao;
import entidades.Cliente;
import entidades.Usuario;

public class NegCliente {

    private final ClienteDao clienteDao;
    
    // Constructor que recibe el DAO
    public NegCliente(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public NegCliente(){
    	 this.clienteDao = new ClienteDao(); 
	}

	public int AgregarCliente(Cliente cliente) {
        int datoRep = clienteDao.verificarDatosRepetido(cliente);
        if (datoRep != 0) {
            return datoRep;
        }
        return clienteDao.AgregarCliente(cliente);
    }

    public ArrayList<Cliente> obtenerTodosLosClientes() {
        return clienteDao.obtenerTodosLosClientes();
    }

    public ArrayList<Cliente> ObtenerUnCliente(String dni) {
        ArrayList<Cliente> lista = new ArrayList<>();
        ArrayList<Cliente> list1 = clienteDao.obtenerTodosLosClientes();
        for (Cliente cliente : list1) {
            if (cliente.getDni().equals(dni)) {
                lista.add(cliente);
            }
        }
        return lista;
    }

    public int EliminarCliente(String dni) {
        return clienteDao.EliminarCliente(dni);
    }

    public Cliente conseguirClientePorDni(String clienteDNI) {
        return clienteDao.buscarClientePorDNI(clienteDNI);
    }

    public boolean modificarCliente(Cliente cliente) {
        return clienteDao.modificarCliente(cliente);
    }


	public Cliente conseguirClienteporUsuario(Usuario usuario) {
		return clienteDao.conseguirClienteporUsuario(usuario);
	}
}
