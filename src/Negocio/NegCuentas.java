package Negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.CuentaDao;
import entidades.Cliente;
import entidades.Cuenta;

public class NegCuentas {

	CuentaDao cnt;

	// Constructor para inicializar cnt
	public NegCuentas() {
		this.cnt = new CuentaDao(); // Inicializa cnt aquí
	}

	public int AgregarCuenta(Cuenta cuenta) {
		return cnt.AgregarCuenta(cuenta);
	}

	public ArrayList<Cuenta> ObtenerLasCuentas() {
		return cnt.obtenerLasCuentas();

	}
	public int obtenerCantidadCuentas(int IdCliente){
		return cnt.obtenerCantidadCuentasCliente(IdCliente);
	}

	public ArrayList<Cuenta> ObtenerCuentasFiltradas(String cbu, Integer tipoCuenta) {
		return cnt.obtenerCuentasFiltradas(cbu, tipoCuenta);
	}

	public ArrayList<Cuenta> ObtenerCuentaCbu(String cbu) {
		return cnt.obtenerCuentaCbu(cbu);
	}
	
	

	/*
	 * public ArrayList<Cuenta> ObtenerCuentasDeUnClinte(int numCuenta, int
	 * tipoCuenta) { ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
	 * ArrayList<Cuenta> list1 = cnt.obtenerLasCuentas(); for (Cuenta cuenta :
	 * list1) { if (cuenta.getNumeroCuenta() == numCuenta || cuenta.getTipoCuenta()
	 * == tipoCuenta) {
	 * 
	 * lista.add(cuenta); } }
	 * 
	 * return lista; }
	 * 
	 * public ArrayList<Cuenta> ObtenerUnaCuenta(int numCuenta) { ArrayList<Cuenta>
	 * lista = new ArrayList<Cuenta>(); ArrayList<Cuenta> list1 =
	 * cnt.obtenerLasCuentas(); for (Cuenta cuenta : list1) { if
	 * (cuenta.getNumeroCuenta() == numCuenta) {
	 * 
	 * lista.add(cuenta); } }
	 * 
	 * return lista; }
	 * 
	 * public ArrayList<Cuenta> ObtenertipoCuentas(int tipocuenta) {
	 * ArrayList<Cuenta> lista = new ArrayList<Cuenta>(); ArrayList<Cuenta> list1 =
	 * cnt.obtenerLasCuentas(); for (Cuenta cuenta : list1) { if
	 * (cuenta.getTipoCuenta() == tipocuenta) {
	 * 
	 * lista.add(cuenta); } }
	 * 
	 * return lista; }
	 * 
	 * public int EliminarCuenta(int numCuenta) { return
	 * cnt.EliminarCuenta(numCuenta); }
	 */

	public int crearNuevaCuenta(String clienteDNI, String fecha, String tipoCuentaStr, String cbu,
			String saldoInicial) {

		Cuenta cuenta = new Cuenta();

		// Conseguimos el ID del cliente en base al DNI.
		NegCliente negCli = new NegCliente();
		Cliente cliente = new Cliente();
		cliente = negCli.conseguirClientePorDni(clienteDNI);

		// PONER UN TRY

		// Seteamos el ID.

		cuenta.setCliente_id(cliente.getIdCliente());

		// Crear un formato de fecha para interpretar YYYY-MM-dd
		try {
			SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fechaParseada = formatoEntrada.parse(fecha);
			java.sql.Date fecha1 = new java.sql.Date(fechaParseada.getTime());
			cuenta.setFecha_creacion(fecha1);
		} catch (Exception e) {
			System.out.println("La fecha ingresada no es valida");
		}

		// Obtener y setear Tipo de Cuenta

		int tipoCuenta = Integer.parseInt(tipoCuentaStr);
		cuenta.setTipo_cuenta_id(tipoCuenta);

		// Obtener y setear CBU
		cuenta.setCbu(cbu);

		// Saldo - Asignar un valor inicial por defecto (10000 en este caso)
		cuenta.setSaldo(10000);

		cuenta.setEstado(1);

		int filas = AgregarCuenta(cuenta);
		return filas;

	}

	public int EliminarCuentaCbu(String cbuCuenta) {
		return cnt.EliminarCuentaCbu(cbuCuenta);
	}

	public boolean modificarCuenta(Cuenta cuenta) {
		CuentaDao cDao = new CuentaDao();
		return cDao.modificarCuenta(cuenta);
	}

}