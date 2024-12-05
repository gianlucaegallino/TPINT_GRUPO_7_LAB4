package servlets;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
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
import entidades.Nacionalidad;



@WebServlet("/SvFlitrosCliente")
public class SvFlitrosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NegCliente negCliente;
	private NegCargarDescolgables negDesc;
	private String nombreFiltro = null; // Variable para almacenar el nombre del filtro

    public SvFlitrosCliente() {
        super();
        negCliente = new NegCliente();
        negDesc = new NegCargarDescolgables();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrando a doGet");
        cargarDescolgables(request);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrando a doPost");
        String filtro = request.getParameter("action");

        if ("mostrarClientes".equals(filtro)) {
            System.out.println("Procesando acción: mostrarClientes");
            mostrarClientes(request, response);
        } else if ("FiltrarXdniClientes".equals(filtro)) {
            System.out.println("Procesando acción: FiltrarXdniClientes");
            filtrarXdniClientes(request, response);
        } else if ("FiltrarXcuilClientes".equals(filtro)) {
            System.out.println("Procesando acción: FiltrarXcuilClientes");
            filtrarXcuilClientes(request, response);
        } else if ("FiltrarXNombreClientes".equals(filtro)) {
            System.out.println("Procesando acción: FiltrarXNombreClientes");
            filtrarXNombreClientes(request, response);
        } else if ("FiltrarXApellidoClientes".equals(filtro)) {
            System.out.println("Procesando acción: FiltrarXApellidoClientes");
            filtrarXApellidoClientes(request, response);
        } else if ("FiltrarXgeneroClientes".equals(filtro)) { // Suponiendo que tienes un botón "Filtrar por GENERO"
            System.out.println("Procesando acción: FiltrarXgeneroClientes");
            filtrarxSexoClientes(request,response);
        } else if("FiltrarXNacionalidadClientes".equals(filtro)) {
        	System.out.println("Procesando acción: FiltrarXNacionalidadClientes");
        	filtrarxNacionalidadClientes(request,response);
        } else if("FiltrarXFechasClientes".equals(filtro)) {
        	System.out.println("Procesando acción: FiltrarXFechasClientes");
        	filtrarxFechasClientes(request,response);
        } // FIN
    }

    private void filtrarxFechasClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fechaInicialString = request.getParameter("fechaInicial");
        String fechaFinalString = request.getParameter("fechaFinal");

        System.out.print("Fecha Inicial:" + fechaInicialString);
        System.out.print("Fecha Final:" + fechaFinalString);

        // Convertir las fechas de String a Date
        Date fechaInicial = convertirFecha(fechaInicialString);
        Date fechaFinal = convertirFecha(fechaFinalString);

        // Validar que las fechas sean válidas
        if (fechaInicial != null && fechaFinal != null) {
            // Obtener la lista de clientes
            ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();

            // Filtrar los clientes por rango de fechas
            ArrayList<Cliente> clientesFiltrados = new ArrayList<>();
            for (Cliente cliente : listaClientes) {
                Date fechaNacimiento = cliente.getFecha_nacimiento();
                if (fechaNacimiento != null && (fechaNacimiento.equals(fechaInicial) || fechaNacimiento.equals(fechaFinal) ||
                        (fechaNacimiento.after(fechaInicial) && fechaNacimiento.before(fechaFinal)))) {
                    clientesFiltrados.add(cliente);
                }
            }

            StringBuilder htmlTabla = new StringBuilder();

            if (clientesFiltrados != null && !clientesFiltrados.isEmpty()) {
                for (Cliente cliente : clientesFiltrados) {
                    htmlTabla.append("<tr>");
                    htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                    htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                    htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                    htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                    htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                    htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                    htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                    htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                    htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                    htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                    htmlTabla.append("</tr>");
                }
            } else {
                htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
            }

            // Pasar el HTML de la tabla a la JSP
            request.setAttribute("tablaHTML", htmlTabla.toString());

            // Redirigir a la JSP de listado de clientes
            System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
            rd.forward(request, response);
        } else {
            // Mostrar mensaje de error al usuario
            System.out.println("No se seleccionaron fechas válidas.");
            request.setAttribute("mensajeError", "Por favor, selecciona fechas válidas.");
            // Redirigir a la JSP de listado de clientes
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
            rd.forward(request, response);
        }
	}

    private Date convertirFecha(String fechaString) {
        Date fecha = null;
        if (fechaString != null && !fechaString.isEmpty()) {
            try {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Formato de la fecha del input
                fecha = (Date) formatoFecha.parse(fechaString);
            } catch (ParseException e) {
                // Manejar la excepción si la fecha no se puede convertir
                e.printStackTrace();
            }
        }
        return fecha;
    }

	private void filtrarxNacionalidadClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedNacionalidadId = request.getParameter("NacioCliente");
		System.out.println("selectedNacioId: " + selectedNacionalidadId);
		
		if(selectedNacionalidadId != null && !selectedNacionalidadId.isEmpty()) {
			// Obtener la lista de clientes
			ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();
			
			StringBuilder htmlTabla = new StringBuilder();
			
			if (listaClientes != null && !listaClientes.isEmpty()) {
				for(Cliente cliente : listaClientes) {
					if(cliente.getNacionalidad().getId() == Integer.parseInt(selectedNacionalidadId)) {
						htmlTabla.append("<tr>");
                		htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                		htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                		htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                		htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                		htmlTabla.append("</tr>");
					}
				}
			}else {
                htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
            }
			// Pasar el HTML de la tabla a la JSP
            request.setAttribute("tablaHTML", htmlTabla.toString());

            // Redirigir a la JSP de listado de clientes
            System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
            rd.forward(request, response);
		}
		
	}


	private void filtrarxSexoClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String selectedSexId = request.getParameter("SexoCliente");
        System.out.println("selectedSexId: " + selectedSexId);
        if (selectedSexId != null && !selectedSexId.isEmpty()) {
            // Filtrar por el sexo seleccionado
        	// Obtener la lista de clientes
            ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();
            
            // CREA LA TABLA
            StringBuilder htmlTabla = new StringBuilder();
            
            // PREGUNTA SI EL ARRAY CONTIENE ELEMENTOS 
            if(listaClientes != null && !listaClientes.isEmpty()) {
            	for(Cliente cliente : listaClientes) {
            		if(cliente.getSexo().getId() == Integer.parseInt(selectedSexId)) {
            			htmlTabla.append("<tr>");
                		htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                		htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                		htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                		htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                		htmlTabla.append("</tr>");
            		}
            	}
            }else {
                htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
            }
            
            // Pasar el HTML de la tabla a la JSP
            request.setAttribute("tablaHTML", htmlTabla.toString());

            // Redirigir a la JSP de listado de clientes
            System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
            rd.forward(request, response);
        } else {
            // Mostrar mensaje de error al usuario
            System.out.println("No se seleccionó un sexo");
            request.setAttribute("mensajeError", "Por favor, selecciona un sexo.");
            // Redirigir a la JSP de listado de clientes
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
            rd.forward(request, response);
        }
	}


	private void cargarDescolgables(HttpServletRequest request) {
        System.out.println("Entrando a cargarDescolgables");
        ArrayList<Nacionalidad> listaNacionalidades = null;
        
        listaNacionalidades = negDesc.ObtenerLasNacionaliadades();
        
        if(listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
        	request.setAttribute("listaNacionalidades", listaNacionalidades);
        }else {
        	request.setAttribute("mensajeError", "No hay nacionalidades.");
        }
        //Damos constancia de que esta funcion ya se corrio, sin importar si se devolvieron o no cuentas
        request.setAttribute("mensajeCarga", "Cargadas");
    }

	private void filtrarXApellidoClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apellido = request.getParameter("ApellidoAfiltrar");

        StringBuilder htmlTabla = new StringBuilder();

        // Si hay un filtro de nombre activo, filtra por apellido solo en esos registros
        if (nombreFiltro != null) {
            ArrayList<Cliente> listaClientesApellido = negCliente.ARRAYbuscarClientesPorAPELLIDO(apellido);
            if (listaClientesApellido != null && !listaClientesApellido.isEmpty()) {
                for (Cliente cliente : listaClientesApellido) {
                	if(cliente.getNombre().equals(nombreFiltro)) {
                		htmlTabla.append("<tr>");
                		htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                		htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                		htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                		htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                		htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                		htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                		htmlTabla.append("</tr>");
                	}
                }
            } else {
                htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
            }
            nombreFiltro = null;
        } else { // Si no hay filtro de nombre activo, filtra por apellido en todos los registros
            ArrayList<Cliente> listaClientesApellido = negCliente.ARRAYbuscarClientesPorAPELLIDO(apellido);
            if (listaClientesApellido != null && !listaClientesApellido.isEmpty()) {
                for (Cliente cliente : listaClientesApellido) {
                    htmlTabla.append("<tr>");
                    htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                    htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                    htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                    htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                    htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                    htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                    htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                    htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                    htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                    htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                    htmlTabla.append("</tr>");
                    System.out.print("TABLAHTML: " + htmlTabla.toString());
                }
            } else {
                htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
            }
        }
        
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL Filtro Apellido CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
		
	}


	private void filtrarXNombreClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("NombreAfiltrar");
		System.out.print("INPUT: " + nombre);
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorNOMBRE(nombre);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
				htmlTabla.append("<tr>");
				htmlTabla.append("<td>" + cliente.getDni() + "</td>");
				htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
				htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
				htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
				htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
				htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
				htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
				htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
				htmlTabla.append("</tr>");
			}
		}else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
		
		System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL Filtro nombre CLIENTES");
        // Guardar el valor del nombre filtrado en la variable nombreFiltro
        nombreFiltro = nombre; 
        
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
		
	}


	private void filtrarXcuilClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cuil = request.getParameter("cuilAfiltrar");
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorCUIL(cuil);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
				htmlTabla.append("<tr>");
				htmlTabla.append("<td>" + cliente.getDni() + "</td>");
				htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
				htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
				htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
				htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
				htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
				htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
				htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
				htmlTabla.append("</tr>");
			}
		}else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
		
		System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
	}


	private void filtrarXdniClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dniAfiltrar");
		
		ArrayList<Cliente> listaClientesDNI = negCliente.ARRAYbuscarClientesPorDNI(dni);
		
		StringBuilder htmlTabla = new StringBuilder();
		if (listaClientesDNI != null && !listaClientesDNI.isEmpty()) {
			for (Cliente cliente : listaClientesDNI) {
				htmlTabla.append("<tr>");
				htmlTabla.append("<td>" + cliente.getDni() + "</td>");
				htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
				htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
				htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
				htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
				htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
				htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
				htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
				htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
				htmlTabla.append("</tr>");
			}
		}else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
		
		System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
	}


	private void mostrarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("ENTRAMOS AL MOSTRAR");        
        // Obtener la lista de clientes
        ArrayList<Cliente> listaClientes = negCliente.obtenerTodosLosClientes();

        // Construir el HTML de la tabla
        StringBuilder htmlTabla = new StringBuilder();
        if (listaClientes != null && !listaClientes.isEmpty()) {
            for (Cliente cliente : listaClientes) {
                htmlTabla.append("<tr>");
                htmlTabla.append("<td>" + cliente.getDni() + "</td>");
                htmlTabla.append("<td>" + cliente.getCuil() + "</td>");
                htmlTabla.append("<td>" + cliente.getNombre() + "</td>");
                htmlTabla.append("<td>" + cliente.getApellido() + "</td>");
                htmlTabla.append("<td>" + cliente.getSexo().getDescripcion() + "</td>");
                htmlTabla.append("<td>" + cliente.getNacionalidad().getNombre() + "</td>");
                htmlTabla.append("<td>" + cliente.getFecha_nacimiento() + "</td>");
                htmlTabla.append("<td>" + cliente.getDireccion().getDireccion() + "</td>");
                htmlTabla.append("<td>" + cliente.getCorreo_electronico() + "</td>");
                htmlTabla.append("<td>" + cliente.getTelefono() + "</td>");
                htmlTabla.append("</tr>");
            }
        } else {
            htmlTabla.append("<tr><td colspan='10'>No hay clientes disponibles.</td></tr>");
        }
        
        System.out.print("TABLAHTML: " + htmlTabla.toString());
        // Pasar el HTML de la tabla a la JSP
        request.setAttribute("tablaHTML", htmlTabla.toString());

        // Redirigir a la JSP de listado de clientes
        System.out.print("SALIMOS DEL MOSTRAR CLIENTES");
        RequestDispatcher rd = request.getRequestDispatcher("/ClientesListar.jsp");
        rd.forward(request, response);
	}

}
