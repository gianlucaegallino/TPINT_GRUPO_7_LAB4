package Negocio;

import java.util.ArrayList;

import dao.ClienteDao;
import entidades.Cliente;
import entidades.Usuario;

public class NegCliente {

    static ClienteDao clienteDao;
    
    // Constructor que recibe el DAO
    public NegCliente(ClienteDao clienteDao) {
        NegCliente.clienteDao = clienteDao;
    }

    public NegCliente(){
    	 NegCliente.clienteDao = new ClienteDao(); 
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

    public int EliminarCliente(int idClient) {
        return clienteDao.EliminarCliente(idClient);
    }

    public Cliente conseguirClientePorDni(String clienteDNI) {
        return clienteDao.buscarClientePorDNI(clienteDNI);
    }
    
    public ArrayList<Cliente> ARRAYbuscarClientesPorDNI(String dni) {
        return clienteDao.ARRAYbuscarClientesPorDNI(dni);
    }
    
    public ArrayList<Cliente> ARRAYbuscarClientesPorCUIL(String cuil) {
        return clienteDao.ARRAYbuscarClientesPorCUIL(cuil);
    }
    
    public ArrayList<Cliente> ARRAYbuscarClientesPorNOMBRE(String nombre) {
        return clienteDao.ARRAYbuscarClientesPorNombre(nombre);
    }
    
    public ArrayList<Cliente> ARRAYbuscarClientesPorAPELLIDO(String apellido) {
        return clienteDao.ARRAYbuscarClientesPorAPELLIDO(apellido);
    }
    
    public boolean modificarCliente(Cliente cliente) {
        return clienteDao.modificarCliente(cliente);
    }
    
    public static String BuscarSexo(int id) {
    	return clienteDao.BuscarSexo(id);
    }

    public static String BuscarNacionalidad(int id) {
    	return clienteDao.BuscarNacionalidad(id);
    }
    
	public Cliente conseguirClienteporUsuario(Usuario usuario) {
		return clienteDao.conseguirClienteporUsuario(usuario);
	}
	
	public Cliente conseguirClienteporId(Integer id) {
		return clienteDao.obtenerClientePorId(id);
	}
}
