package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCliente;
import Negocio.NegCuentas;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.TipoCuenta;

@WebServlet("/SlCuentas")
public class SlCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private NegCuentas negCuenta = new NegCuentas();
    

    public SlCuentas() {
        super();
        negCuenta = new NegCuentas();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	/*

        // Si se presiona el boton de eliminar
        else if (request.getParameter("Eliminar") != null) {
            String cbuCuenta = request.getParameter("cbu");
            NegCuentas cnt = new NegCuentas();
            filas = cnt.EliminarCuentaCbu(cbuCuenta);

            request.setAttribute("cantfilas", filas);
            RequestDispatcher rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            rd.forward(request, response);

        }
        */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		System.out.println("Entrando a doPost");
    		String action = request.getParameter("action");
    		NegCuentas cnt = new NegCuentas();
    		
    		if ("AgregarCuentas".equals(action)) {
    			AgregarCuentas(request, response);
    		} else if("BuscarCuentas".equals(action)) {
    			BuscarCuentas(request,response);
    		} else if("modificarEliminarCuenta".equals(action)) {
    			modificarEliminarCuenta(request,response);
    		}
    		if (request.getParameter("btnBuscar") != null) {
    			ArrayList<Cuenta> lista = cnt.ObtenerLasCuentas();
    			request.setAttribute("listaCuenta", lista);
    			RequestDispatcher rd = request.getRequestDispatcher("/ListarCuenta.jsp");
                rd.forward(request, response);
    		}
        /*
        if (request.getParameter("btnBuscar1") != null) {
            String cbu = request.getParameter("cbuBuscar");
            String tipoCuentaStr = request.getParameter("tipoCuenta");

            Integer tipoCuenta = null;
            if (tipoCuentaStr != null && !tipoCuentaStr.isEmpty()) {
                tipoCuenta = Integer.parseInt(tipoCuentaStr);
            }

            ArrayList<Cuenta> lista = cnt.ObtenerCuentasFiltradas(cbu, tipoCuenta);
            request.setAttribute("listaCuenta", lista);
            rd = request.getRequestDispatcher("/ListarCuenta.jsp");
        }


        if (request.getParameter("btnBuscar2") != null) {
            String cbu = request.getParameter("cbuBuscar");
            ArrayList<Cuenta> lista = cnt.ObtenerCuentaCbu(cbu);
            request.setAttribute("listaCuenta", lista);
            rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
        }
        // Modificar
        if (request.getParameter("botonModificar") != null) {
            int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
            // UNICOS DOS CAMPOS A MODIFICA
            String cbu = request.getParameter("cbuModificar");
            double saldo = Double.parseDouble(request.getParameter("saldoModificar")); // Necesario agregar en el front

            Cuenta cuenta = new Cuenta();
            cuenta.setNumero_cuenta(idCuenta);
            cuenta.setCbu(cbu);
            cuenta.setSaldo(saldo);
            boolean resultado = cnt.modificarCuenta(cuenta);

            if (resultado) {
                request.setAttribute("mensaje", "¡La cuenta se modificó correctamente!");
                rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            } else {
                request.setAttribute("mensaje", "Error al modificar la cuenta.");
                rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            }
        }
        // Eliminar
        if (request.getParameter("Eliminar") != null) {
            String cbuCuenta = request.getParameter("cbu");
            if (cbuCuenta != null && !cbuCuenta.isEmpty()) {
                NegCuentas cnt1 = new NegCuentas();
                int filas = cnt1.EliminarCuentaCbu(cbuCuenta);

                request.setAttribute("cantfilas", filas);
                rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            } else {
                // Manejar el caso en que el valor es nulo o vacío
                // Por ejemplo, mostrar un mensaje de error al usuario
                request.setAttribute("mensaje", "Error: No se ha seleccionado ninguna cuenta.");
                rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            }
        }
        rd.forward(request, response);
		*/
    }
    
    
    private void modificarEliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("btnModificar") != null) {
            int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
            // UNICOS DOS CAMPOS A MODIFICA
            String cbu = request.getParameter("cbu");
            double saldo = Double.parseDouble(request.getParameter("saldo")); // Necesario agregar en el front

            Cuenta cuenta = new Cuenta();
            cuenta.setNumero_cuenta(idCuenta);
            cuenta.setCbu(cbu);
            cuenta.setSaldo(saldo);
            NegCuentas cnt = new NegCuentas();
            boolean resultado = cnt.modificarCuenta(cuenta); 

            if (resultado) {
                request.setAttribute("mensaje", "¡La cuenta se modificó correctamente!");
                RequestDispatcher rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensaje", "Error al modificar la cuenta.");
                RequestDispatcher rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
                rd.forward(request, response);
            }
        }
		
	}

	private void BuscarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.print("Entrando al BuscarCuenta \n");
    		String CbuBuscar = request.getParameter("cbuBuscar");
    		ArrayList<Cuenta> listacuentas = negCuenta.ObtenerCuentaCbu(CbuBuscar);
    		if(listacuentas != null && !listacuentas.isEmpty()) {
    			request.setAttribute("listaCuenta", listacuentas);
    			RequestDispatcher rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
                rd.forward(request, response);
    		}
	}

	private void AgregarCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.print("ENTRANDO AL AGREGARCUENTAS \n");
    	
    	// Sacamos los parametros, y los enviamos al negocio.
        String clienteDNI = request.getParameter("DNICliente");
        String Fecha = request.getParameter("fechaCreacion");
        String tipoCuentaStr = request.getParameter("tipoCuenta");
        String cbu = request.getParameter("cbu");
        String saldoInicial = "10000";
        
        System.out.print("PARAMETROS DEL JSP: " + clienteDNI + " | " + Fecha + " | " + tipoCuentaStr + " | " + cbu + " |\n");
        
        // CREAR INSTANCIA CLIENTE Y CONSIGUE EL CLIENTE PARA SACAR SU ID
        Cliente cliente = new Cliente();
        NegCliente negCli = new NegCliente();
        cliente = negCli.conseguirClientePorDni(clienteDNI);
        
        System.out.print("Cliente: " + cliente + "\n");
        
        // almacena el "id"
        Cuenta cuenta = new Cuenta();
        cuenta.setIDcliente(cliente);
        System.out.print("ID EN CUENTA: " + cuenta.getIDcliente().getIdCliente() + "\n");
        
        // almacena la fecha
        Date Fecha_Creacion = convertirFecha(Fecha);
        System.out.print("FECHA DEL CONVERTIR: " + Fecha_Creacion + "\n");
        cuenta.setFecha_creacion(Fecha_Creacion);
        System.out.print("FECHA EN CUENTA: " + cuenta.getFecha_creacion() + "\n");
        
        // almacena el tipo de cuenta
        TipoCuenta tipodecuenta = new TipoCuenta();
        int Tcuenta = Integer.parseInt(tipoCuentaStr);
        tipodecuenta.setId(Tcuenta);
        cuenta.setCuenta(tipodecuenta);
        
        System.out.print("TIPO CUENTA EN CUENTA: " + cuenta.getCuenta().getId() + "\n");
        
        // almacena el cbu
        cuenta.setCbu(cbu);
        System.out.print("CBU EN CUENTA: " + cuenta.getCbu() + "\n");
        
        //
        double saldo_Inicial = Double.parseDouble(saldoInicial);
        cuenta.setSaldo(saldo_Inicial);
        System.out.print("SALDO EN CUENTA: " + cuenta.getSaldo() + "\n");
        
        NegCuentas negcuenta = new NegCuentas();
        int cantidadCuentas = negcuenta.obtenerCantidadCuentas(cliente.getIdCliente());
        if (cantidadCuentas >= 3) {
        	
        	request.setAttribute("mensajeError", "El cliente ya tiene 3 cuentas asociadas."); 
            RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
            rd.forward(request, response);
        } else {
            // ... (Código para crear la cuenta y insertarla) ...
        	negcuenta.AgregarCuenta(cuenta);
            request.setAttribute("mensaje", "Cuenta agregada correctamente.");
            RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
            rd.forward(request, response);
            return; // Salir de la función
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
        rd.forward(request, response);
    }
    
    private Date convertirFecha(String fechaString) {
    	Date fecha = null;
        if (fechaString != null && !fechaString.isEmpty()) {
            try {
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Formato de la fecha del input
                java.util.Date fechaUtil = formatoFecha.parse(fechaString);
                fecha = new java.sql.Date(fechaUtil.getTime()); // Crea un nuevo objeto java.sql.Date
            } catch (ParseException e) {
                // Manejar la excepción si la fecha no se puede convertir
                e.printStackTrace();
            }
        }
        return fecha;
    }

}