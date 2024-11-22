package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.NegCuentas;
import entidades.Cuenta;

/**
 * Servlet implementation class SlCuentas
 */
@WebServlet("/SlCuentas")
public class SlCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int filas = 0;

        // Al presionar el boton guardar
        if (request.getParameter("btnGuardar") != null) {

            // Sacamos los parametros, y los enviamos al negocio.
            String clienteDNI = request.getParameter("DNICliente");
            String Fecha = request.getParameter("fechaCreacion");
            String tipoCuentaStr = request.getParameter("tipoCuenta");
            String cbu = request.getParameter("cbu");
            String saldoInicial = "10000";

            NegCuentas negCuentas = new NegCuentas();
            filas = negCuentas.crearNuevaCuenta(clienteDNI, Fecha, tipoCuentaStr, cbu,
                    saldoInicial);

            request.setAttribute("cantfilas", filas);
            RequestDispatcher rd = request.getRequestDispatcher("/Cuentas.jsp");
            rd.forward(request, response);

        }

        // Si se presiona el boton de eliminar
        else if (request.getParameter("Eliminar") != null) {
            String cbuCuenta = request.getParameter("cbu");
            NegCuentas cnt = new NegCuentas();
            filas = cnt.EliminarCuentaCbu(cbuCuenta);

            request.setAttribute("cantfilas", filas);
            RequestDispatcher rd = request.getRequestDispatcher("/EditarEliminarCuenta.jsp");
            rd.forward(request, response);

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NegCuentas cnt = new NegCuentas();
        RequestDispatcher rd = null;

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

        if (request.getParameter("btnBuscar") != null) {
            ArrayList<Cuenta> lista = cnt.ObtenerLasCuentas();
            request.setAttribute("listaCuenta", lista);
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

    }

}