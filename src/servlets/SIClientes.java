package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCargarDescolgables;
import Negocio.NegCliente;
import entidades.Cliente;
import entidades.Direccion;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import excepciones.OpcionNoEncontradaException;

@WebServlet("/SIClientes")
public class SIClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegCliente negCliente;
    private NegCargarDescolgables negDesc;

    public SIClientes() {
        super();
        negCliente = new NegCliente(); 
        negDesc = new NegCargarDescolgables();
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	
        cargarDescolgables(request);

        if (request.getParameter("ProvCliente") != null) {
        	 String dni = request.getParameter("DniCliente");
             String cuil = request.getParameter("CUILCliente");
             String nombre = request.getParameter("nombreCliente");
             String apellido = request.getParameter("apellidoCliente");
             String telefono = request.getParameter("telefonoCliente");
             String sexo = request.getParameter("SexoCliente");
             String nacionalidad = request.getParameter("NacioCliente");
             String fechaNacimiento = request.getParameter("FNacimientoCliente");
             String localidad = request.getParameter("LocCliente");
             String direccion = request.getParameter("DirecCliente");
             String correo = request.getParameter("CorreoCliente");
             System.out.println(dni);
             System.out.println(cuil);
             System.out.println(nombre);
             System.out.println(apellido);
             System.out.println(telefono);
             System.out.println(sexo);
             System.out.println(nacionalidad);
             System.out.println(fechaNacimiento);
             System.out.println(localidad);
             System.out.println(direccion);
             System.out.println(correo);
             
            cargarLocalidades(request, response);
      /*
       *       request.setAttribute("DniCliente", dni);
            request.setAttribute("CUILCliente", dni);
            request.setAttribute("nombreCliente", dni);
            request.setAttribute("apellidoCliente", dni);
            request.setAttribute("telefonoCliente", dni);
            request.setAttribute("SexoCliente", dni);
            request.setAttribute("NacioCliente", dni);
            request.setAttribute("FNacimientoCliente", dni);
            request.setAttribute("LocCliente", dni);
            request.setAttribute("DirecCliente", dni);
            request.setAttribute("CorreoCliente", dni);*/
            
            
            
        }
        request.setAttribute("mensajeCarga", "Cargadas");
		RequestDispatcher rd = request.getRequestDispatcher("/ClientesAgregar.jsp");
		rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String action = request.getParameter("action");

        
        switch(action) {
        
        case "mostrarClientes":
        	mostrarClientes(request, response);
        	break;
        case "filtrarClientes":
        	filtrarClientes(request, response);
        	break;
        case "agregarCliente":
        	agregarCliente(request, response);
        	break;
        case "buscarPorDNI":
        	buscarPorDNI(request,response);
        	break;
        case "modificarEliminarCliente":
        	modificarEliminarCliente(request, response);
        	break;
        default:
        	try {
				manejarexcepcion(request, response);
			} catch (OpcionNoEncontradaException e) {
				e.printStackTrace();
			}
        }

        
        if (request.getParameter("btnCancelar") != null) {

			RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
			rd.forward(request, response);
		}
    }
    
    private void manejarexcepcion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, OpcionNoEncontradaException {
    	throw new OpcionNoEncontradaException();
    }

    private void buscarPorDNI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String dni = request.getParameter("numeroDNIaBuscar");
    	
    	Cliente client = negCliente.conseguirClientePorDni(dni);
    	
    	if(client != null) {
    		// Obtener la descripción del sexo
            String sexoDescripcion = NegCliente.BuscarSexo(client.getSexo().getId());
            client.setSexo(new Sexo(client.getSexo().getId(), sexoDescripcion)); // Actualizar el objeto Sexo con la descripción
            
         // Obtener la descripción de la nacionalidad
            String nacionalidadDescripcion = NegCliente.BuscarNacionalidad(client.getNacionalidad().getId());
            client.setNacionalidad(new Nacionalidad(client.getNacionalidad().getId(), nacionalidadDescripcion)); // Actualizar el objeto Nacionalidad con la descripción
            
            request.setAttribute("cliente", client);
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
            rd.forward(request, response);
        } else {
            // Manejar el caso en que no se encuentra el cliente
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
            rd.forward(request, response);
        }
		
	}


	private void cargarDescolgables(HttpServletRequest request) {
        // Obtener la lista de sexos y pasarla al JSP
        ArrayList<Sexo> sexos = negDesc.obtenerLosSexos();
        if (sexos != null && !sexos.isEmpty()) {
            request.setAttribute("sexos", sexos);
        } else {
            request.setAttribute("mensajeError", "No se pudieron cargar los datos de sexo.");
        }

        // Obtener la lista de nacionalidades y pasarla al JSP
        ArrayList<Nacionalidad> nac = negDesc.ObtenerLasNacionaliadades();
        if (nac != null && !nac.isEmpty()) {
            request.setAttribute("nacionalidad", nac); // Establecer el atributo "nacionalidad"
        } else {
            request.setAttribute("mensajeError", "No se pudieron cargar los datos de Nacionalidad.");
        }

        // Obtener la lista de provincias y pasarla al JSP
        ArrayList<Provincia> prov = negDesc.ObtenerLasProvincias();
        if (prov != null && !prov.isEmpty()) {
            request.setAttribute("Provincia", prov); // Establecer el atributo "Provincia"
        } else {
            request.setAttribute("mensajeError", "No se pudieron cargar los datos de Provincia.");
        }
    }

    private void cargarLocalidades(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	System.out.println(request.getParameter("ProvCliente"));
        int provId = Integer.parseInt(request.getParameter("ProvCliente"));

        ArrayList<Localidad> localidades = negDesc.ObtenerLasLocalidadesPorProvincia(provId);

        
        if (localidades != null && !localidades.isEmpty()) {
            request.setAttribute("localidades", localidades);
            request.setAttribute("prov", provId);
        } else {
            request.setAttribute("mensajeError", "No se pudieron cargar las localidades.");
        }
    }

    private void mostrarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la lista de clientes
        ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();
        
        // Pasar la lista de clientes a la JSP
        request.setAttribute("listaC", listaClientes);

        // Redirigir a la JSP de listado de clientes
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
    }

    private void filtrarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("numDNIBuscar");
        // Obtener la lista de clientes
        ArrayList<Cliente> listaClientes = negCliente.ObtenerUnCliente(dni);

        // Pasar la lista de clientes a la JSP
        request.setAttribute("listaC", listaClientes);

        // Redirigir a la JSP de listado de clientes
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
    }

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los datos del formulario
        String dni = request.getParameter("DniCliente");
        String cuil = request.getParameter("CUILCliente");
        String nombre = request.getParameter("nombreCliente");
        String apellido = request.getParameter("apellidoCliente");
        String telefono = request.getParameter("telefonoCliente");
        int sexoId = Integer.parseInt(request.getParameter("SexoCliente"));
        int nacionalidadId = Integer.parseInt(request.getParameter("NacioCliente"));
        Date fechaNacimiento = Date.valueOf(request.getParameter("FNacimientoCliente"));
        int localidadId = Integer.parseInt(request.getParameter("LocCliente"));
        String direccion = request.getParameter("DirecCliente");
        String correo = request.getParameter("CorreoCliente");

        // Validar los datos
        if (!validarDatos(request, dni, cuil, nombre, apellido, telefono, sexoId, nacionalidadId, fechaNacimiento,
                localidadId, direccion, correo)) {
            return; // Si la validación falla, no se agrega el cliente
        }

        // Crear la instancia de Cliente
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setCuil(cuil);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setSexo(new Sexo(sexoId)); // Se asume que la descripción del sexo no se necesita
        cliente.setNacionalidad(new Nacionalidad(nacionalidadId)); // Se asume que la descripción de la nacionalidad no se necesita
        cliente.setFecha_nacimiento(fechaNacimiento);
        cliente.setDireccion(new Direccion(direccion, new Localidad(localidadId, "", null))); // Se asume que la provincia no se necesita
        cliente.setCorreo_electronico(correo);
        cliente.setEstado("1"); // Se asume que el estado es activo

        // Agregar el cliente a la base de datos
        int resultado = negCliente.AgregarCliente(cliente);

        // Redirigir a la JSP de agregar cliente con un mensaje de éxito o error
        if (resultado == 0) {
            request.setAttribute("mensajeExito", "¡Cliente agregado correctamente!");
        } else if (resultado == 2) {
            request.setAttribute("mensajeError", "El DNI ya existe en la base de datos.");
        } else if (resultado == 3) {
            request.setAttribute("mensajeError", "El CUIL ya existe en la base de datos.");
        } else if (resultado == 4) {
            request.setAttribute("mensajeError", "El correo electrónico ya existe en la base de datos.");
        } else if (resultado == 5) {
            request.setAttribute("mensajeError", "El teléfono ya existe en la base de datos.");
        } else {
            request.setAttribute("mensajeError", "Error al agregar el cliente.");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/ClientesAgregar.jsp");
        rd.forward(request, response);
    }

    private void modificarEliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Entramos al modificarEliminar");
    	if (request.getParameter("btnGuardar") != null) {
    		System.out.println("ENTRAMOS AL BOTON GUARDAR");
	    	// Obtener los datos del formulario
    		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	        String direccion = request.getParameter("direccion");
	        String correo = request.getParameter("correo");
	        String telefono = request.getParameter("telefono");
	        
	        System.out.println("idCliente: " + idCliente + " Direccion: " + direccion + " Correo: " + correo+ " Telefono: " + telefono);
	        // Validar los datos
	        //if (!validarDatosModificar(request, direccion, correo, telefono)) {
	          //  return; // Si la validación falla, no se modifica el cliente
	        //}
	        System.out.println("ValidarDatos: " + !validarDatosModificar(request, direccion, correo, telefono));
	        
	        // Crear la instancia de Cliente
	        Cliente cliente = new Cliente();
	        cliente.setIdCliente(idCliente);
	        cliente.setDireccion(new Direccion(direccion));
	        cliente.setCorreo_electronico(correo);
	        cliente.setTelefono(telefono);
	        
	        System.out.print("Cliente: " + cliente.getIdCliente() + "| " + cliente.getDireccion().getDireccion()+ "| " + cliente.getCorreo_electronico() + "| " + cliente.getTelefono());
	
	        // Modificar el cliente en la base de datos
	        boolean resultado = negCliente.modificarCliente(cliente);
	        System.out.println("Resultado: " + resultado);
	
	        // Redirigir a la JSP de modificar cliente con un mensaje de éxito o error
	        if (resultado == true) {
	            request.setAttribute("mensaje", "¡Cliente modificado correctamente!");
	        } else {
	            request.setAttribute("mensaje", "Error al modificar el cliente.");
	        }
	
	        RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
	        rd.forward(request, response);
	        System.out.println("SALIENDO DEL MODIFICAR ");
    	}
    	
    	if (request.getParameter("btnEliminarSubmit") != null) {
			// Obtener el DNI del cliente a eliminar
	        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	
	        // Eliminar el cliente de la base de datos
	        int resultado = negCliente.EliminarCliente(idCliente);
	
	        // Redirigir a la JSP de eliminar cliente con un mensaje de éxito o error
	        if (resultado == 0) {
	            request.setAttribute("mensaje", "¡Cliente eliminado correctamente!");
	        } else {
	            request.setAttribute("mensaje", "Error al eliminar el cliente.");
	        }
	
	        RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
	        rd.forward(request, response);
		}
    }

    private boolean validarDatosModificar(HttpServletRequest request, String direccion, String correo,
			String telefono) {
    	 // Validación del teléfono
        if (telefono == null || telefono.isEmpty() || !telefono.matches("[0-9]{8,10}")) {
            request.setAttribute("mensajeError", "El teléfono no tiene un formato válido.");
            return false;
        }

        // Validación del correo electrónico
        if (correo == null || correo.isEmpty() || !correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            request.setAttribute("mensajeError", "El correo electrónico no tiene un formato válido.");
            return false;
        }

        // Validación de la dirección
        if (direccion == null || direccion.isEmpty()) {
            request.setAttribute("mensajeError", "La dirección no puede estar vacía.");
            return false;
        }

        return true;
	}

    private boolean validarDatos(HttpServletRequest request, String dni, String cuil, String nombre, String apellido,
            String telefono, int sexoId, int nacionalidadId, Date fechaNacimiento, int localidadId, String direccion,
            String correo) {
        // Validación del DNI
        if (dni == null || dni.isEmpty() || dni.length() < 7 || dni.length() > 8) {
            request.setAttribute("mensajeError", "El DNI no tiene un formato válido.");
            return false;
        }

        // Validación del CUIL
        if (cuil == null || cuil.isEmpty() || !cuil.matches("(20|23|24|27)[0-9]{9}")) {
            request.setAttribute("mensajeError", "El CUIL no tiene un formato válido.");
            return false;
        }

        // Validación del nombre
        if (nombre == null || nombre.isEmpty()) {
            request.setAttribute("mensajeError", "El nombre no puede estar vacío.");
            return false;
        }

        // Validación del apellido
        if (apellido == null || apellido.isEmpty()) {
            request.setAttribute("mensajeError", "El apellido no puede estar vacío.");
            return false;
        }

        // Validación del teléfono
        if (telefono == null || telefono.isEmpty() || !telefono.matches("[0-9]{8,10}")) {
            request.setAttribute("mensajeError", "El teléfono no tiene un formato válido.");
            return false;
        }

        // Validación del correo electrónico
        if (correo == null || correo.isEmpty() || !correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            request.setAttribute("mensajeError", "El correo electrónico no tiene un formato válido.");
            return false;
        }

        // Validación de la fecha de nacimiento
        if (fechaNacimiento == null) {
            request.setAttribute("mensajeError", "La fecha de nacimiento no puede estar vacía.");
            return false;
        }

        // Validación de la localidad
        if (localidadId == 0) {
            request.setAttribute("mensajeError", "La localidad no puede estar vacía.");
            return false;
        }

        // Validación de la dirección
        if (direccion == null || direccion.isEmpty()) {
            request.setAttribute("mensajeError", "La dirección no puede estar vacía.");
            return false;
        }

        return true;
    }
}