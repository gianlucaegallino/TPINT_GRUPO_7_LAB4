package entidades;

import java.sql.Date;

public class Cuenta {

	private Cliente IDcliente;
	private Date fecha_creacion;
	private TipoCuenta cuenta;
	private int numero_cuenta;
	private String cbu;
	private double saldo;
	private int estado; // Campo estado agregado
	
	// CONSTRUCTORS

	public Cuenta() {
		super();
	}
	public Cuenta(int numero_cuenta) {
		super();
		this.numero_cuenta = numero_cuenta;
	}
	public Cuenta(Cliente iDcliente, Date fecha_creacion, TipoCuenta cuenta, int numero_cuenta, String cbu,
			double saldo, int estado) {
		super();
		IDcliente = iDcliente;
		this.fecha_creacion = fecha_creacion;
		this.cuenta = cuenta;
		this.numero_cuenta = numero_cuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}
	
		
	// GETTERS AND SETTERS
	public Cliente getIDcliente() {
		return IDcliente;
	}
	public void setIDcliente(Cliente iDcliente) {
		IDcliente = iDcliente;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public TipoCuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(TipoCuenta cuenta) {
		this.cuenta = cuenta;
	}
	public int getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(int numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Cuenta [IDcliente=" + IDcliente + ", fecha_creacion=" + fecha_creacion + ", cuenta=" + cuenta
				+ ", numero_cuenta=" + numero_cuenta + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
	}

	

}
