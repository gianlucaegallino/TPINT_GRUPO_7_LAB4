package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;

/**
 * Servlet implementation class SIClientes
 */
@WebServlet("/SIClientes")
public class SIClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SIClientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		

		System.out.println("El método doGet se está ejecutando.");
		 // NUEVO BLOQUE: Obtener la lista de sexos y pasarla al JSP
	    NegCargarDescolgables negDesc = new NegCargarDescolgables(); // Instancia de la lógica de negocio para clientes
		 ArrayList<Sexo> sexos = negDesc.obtenerLosSexos();
		    if (sexos != null && !sexos.isEmpty()) {
		        
		        request.setAttribute("sexos", sexos);
		    } else {
		        
		        request.setAttribute("mensajeError", "No se pudieron cargar los datos de sexo.");
		    }
		    
		    ArrayList<Nacionalidad> nac = negDesc.ObtenerLasNacionaliadades();
		    
		    if (nac != null && !nac.isEmpty()) {
		        
		        request.setAttribute("nacionalidad", nac);
		    } else {
		        
		        request.setAttribute("mensajeError", "No se pudieron cargar los datos de Nacionalidad.");
		    }

		    
		    ArrayList<Provincia> prov = negDesc.ObtenerLasProvincias();
		    
		    if (prov != null && !prov.isEmpty()) {
		        
		        request.setAttribute("Provincia", prov);
		    } else {
		        
		        request.setAttribute("mensajeError", "No se pudieron cargar los datos de Provincia.");
		    }
		    
		    int filas = 0;
			
			if (request.getParameter("btnAgregarCliente") != null) {
				
		
				
				   
				    //guardar el cliente, etc.
					Cliente cliente = new Cliente();

					// Agrega Nombre
					String cNomStr = request.getParameter("nombreCliente");
					cliente.setNombre(cNomStr);

					// Agrega Apellido
					String cApeStr = request.getParameter("apellidoCliente");
					cliente.setApellido(cApeStr);

					// Agrega DNI
					String cDNIStr = request.getParameter("DniCliente");
					cliente.setDni(cDNIStr);
					
					// Agrega CUIL
					String cCuilStr = request.getParameter("CUILCliente");
					cliente.setCuil(cCuilStr);

					// Agrega Genero
					String cSexoStr = request.getParameter("SexoCliente");
					int sexoInt = Integer.parseInt(cSexoStr);
					cliente.setSexo_id(sexoInt);

					// Agrega la nacionalidad
					String cNacionalidadStr = request.getParameter("NacioCliente");
					int nacioInt = Integer.parseInt(cNacionalidadStr);
					cliente.setNacionalidad_id(nacioInt);

					// Agrega la Fecha
					String Fecha = request.getParameter("FNacimientoCliente");
					try {
						// Crear un formato de fecha para interpretar YYYY-MM-dd
						SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");

						// Parsear la fecha y luego convertirla a java.sql.Date
						java.util.Date fechaParseada = formatoEntrada.parse(Fecha);

						// Convertir la fecha parseada a java.sql.Date para almacenarla en la base de
						// datos
						java.sql.Date fecha1 = new java.sql.Date(fechaParseada.getTime());

						// Establecer la fecha en el objeto cuenta
						cliente.setFecha_nacimiento(fecha1);
					} catch (Exception e) {
						// Manejar el caso donde el valor de la fecha no es válido
						System.out.println("La fecha ingresada no es válida");
					}

					// Agrega la direccion
					String cDirecStr = request.getParameter("DirecCliente");
					cliente.setDireccion_id(cDirecStr);

					// Agrega el correo
					String cCorreoStr = request.getParameter("CorreoCliente");
					cliente.setCorreo_electronico(cCorreoStr);

					// Agrega el telefono
					String cTelStr = request.getParameter("telefonoCliente");
					cliente.setTelefono(cTelStr);
					
				
					

					NegCliente cnt = new NegCliente();
					filas = cnt.AgregarCliente(cliente);
					
					switch (filas) {
			        case 1:
			            // No hay conflictos, se puede agregar el cliente
			            request.setAttribute("mensajeExito", "¡El cliente se agregó correctamente!");
			            RequestDispatcher rd = request.getRequestDispatcher("/ClientesAgregar.jsp");
		        	    rd.forward(request, response);
		        	    
			            return;
			        case 2:
			            // El DNI ya existe
			            request.setAttribute("mensajeError", "El DNI ya está registrado.");
			            break;
			        case 3:
			            // El CUIL ya existe
			            request.setAttribute("mensajeError", "El CUIL ya está registrado.");
			            break;
			        case 4:
			            // El correo electrónico ya existe
			            request.setAttribute("mensajeError", "El correo electrónico ya está registrado.");
			            break;
			        case 5:
			            // El teléfono ya existe
			            request.setAttribute("mensajeError", "El teléfono ya está registrado.");
			            break;
			        default:
			            // En caso de error inesperado
			            request.setAttribute("mensajeError", "Hubo un error al verificar los datos.");
			            break;
			    }
																	
			}
  
		 String provCliente = request.getParameter("ProvCliente");
		 String LocalCliente= request.getParameter("LocCliente");
		 //System.out.println("loalidad: " + LocalCliente );
		 String nacCliente = request.getParameter("NacioCliente");
		 String generoCliente= request.getParameter("SexoCliente");
		 
		
		  if (provCliente != null && !provCliente.isEmpty() )   {
			  //guardar lo ingresado en los inputs
			  
			  String dni = request.getParameter("DniCliente");
		      String cuil = request.getParameter("CUILCliente");
		      String nombre = request.getParameter("nombreCliente");
		      String apellido = request.getParameter("apellidoCliente");
	          String telefono = request.getParameter("telefonoCliente");
	          String direccion = request.getParameter("DirecCliente");
	          String correo = request.getParameter("CorreoCliente");
	          String fechaNacimiento = request.getParameter("FNacimientoCliente");
			  
			  
			  int privID= Integer.parseInt(provCliente);
			  //NegCargarDescolgables negDesc = new NegCargarDescolgables();
			  ArrayList<Localidad> localidades = negDesc.ObtenerLasLocalidadesPorProvincia(privID);
			  
			  if (localidades != null && !localidades.isEmpty()) {
		            request.setAttribute("localidades", localidades);
		        } else {
		            request.setAttribute("mensajeError", "No se pudieron cargar las localidades.");
		        }
			  
			// Devolver los datos ingresados al formulario
		        request.setAttribute("DniCliente", dni);
		        request.setAttribute("CUILCliente", cuil);
		        request.setAttribute("nombreCliente", nombre);
		        request.setAttribute("apellidoCliente", apellido);
		        request.setAttribute("telefonoCliente", telefono);
		        request.setAttribute("DirecCliente", direccion);
		        request.setAttribute("CorreoCliente", correo);
		        request.setAttribute("FNacimientoCliente", fechaNacimiento);
		        request.setAttribute("Nac", nacCliente);
		        request.setAttribute("generoCliente",generoCliente );
		        request.setAttribute("prov", provCliente);		        						   			  
		  }

		 
		// REQUESTDISPATCHER
	    RequestDispatcher rd = request.getRequestDispatcher("/ClientesAgregar.jsp");
	    rd.forward(request, response);
	    
	    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		if (request.getParameter("btnMostrarCliente") != null) {
			// Obtener la lista de clientes
			NegCliente negCliente = new NegCliente();
			ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();

			// Pasar la lista de clientes a la JSP
			request.setAttribute("listaC", listaClientes);

			// Redirigir a la JSP de listado de clientes
			RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
			rd.forward(request, response);

		}
		
		if(request.getParameter("btnFiltrar")!= null){
			String dni = request.getParameter("numDNIBuscar");
			// Obtener la lista de clientes
			NegCliente negCliente = new NegCliente();
			ArrayList<Cliente> listaClientes = negCliente.ObtenerUnCliente(dni);

		 // Pasar la lista de clientes a la JSP
			request.setAttribute("listaC", listaClientes);
			
		 // Redirigir a la JSP de listado de clientes
			RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
		   rd.forward(request, response);
		}
		
		
		// CODIGO PARA EL MODIFICAR/ELIMINAR
		/* BUSCAR EL DNI */
		
		
		if (request.getParameter("btnBuscarDNI") != null)  {
			String dni = request.getParameter("numeroDNIaBuscar");
			Cliente cli = new Cliente();
			NegCliente negCli = new NegCliente();
			
			cli = negCli.conseguirClientePorDni((dni));
			System.out.println("Dni clienet: "+cli.getDni());
			
			if (cli.getDni() != null) {
				request.setAttribute("cliente", cli);
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje", "No se encontró un cliente con ese DNI.");
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			}
		}
		if (request.getParameter("btnGuardar") != null) {
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			String direccion = request.getParameter("direccion");
			String correo = request.getParameter("correo");
			String telefono = request.getParameter("telefono");

			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			cliente.setDireccion_id(direccion);
			cliente.setCorreo_electronico(correo);
			cliente.setTelefono(telefono);

			NegCliente negCliente = new NegCliente();
			boolean resultado = negCliente.modificarCliente(cliente);

			if (resultado) {
				request.setAttribute("mensaje", "¡El cliente se modificó correctamente!");
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje", "Error al modificar el cliente.");
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			}
		}

		if (request.getParameter("btnEliminarSubmit") != null) {
			String DniCliente = request.getParameter("dniCliente");

			Cliente cliente = new Cliente();
			cliente.setDni(DniCliente);

			NegCliente negCliente = new NegCliente();
			int resultado = negCliente.EliminarCliente(DniCliente);

			if (resultado != 0) {
				request.setAttribute("mensaje", "¡El cliente se elimino correctamente!");
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje", "Error al eliminar el cliente.");
				request.getRequestDispatcher("/ClientesModificarEliminar.jsp").forward(request, response);
			}
		}

		if (request.getParameter("btnCancelar") != null) {

			RequestDispatcher rd = request.getRequestDispatcher("/ClientesModificarEliminar.jsp");
			rd.forward(request, response);
		}

	}

}
