package entidades;

import java.sql.Date;

public class Cuenta {

	private int cliente_id;
	private Date fecha_creacion;
	private int tipo_cuenta_id;
	private int numero_cuenta;
	private String cbu;
	private double saldo;
	private int estado; // Campo estado agregado

	// constructor sin numero cuenta
	public Cuenta(int cliente_id, Date fecha_creacion, int tipo_cuenta_id, String cbu, double saldo, int estado) {
		super();
		this.cliente_id = cliente_id;
		this.fecha_creacion = fecha_creacion;
		this.tipo_cuenta_id = tipo_cuenta_id;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}

	// constructor con numero cuenta

	public Cuenta(int cliente_id, Date fecha_creacion, int tipo_cuenta_id, int numero_cuenta, String cbu, double saldo,
			int estado) {
		super();
		this.cliente_id = cliente_id;
		this.fecha_creacion = fecha_creacion;
		this.tipo_cuenta_id = tipo_cuenta_id;
		this.numero_cuenta = numero_cuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}

	public Cuenta() {
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public int getTipo_cuenta_id() {
		return tipo_cuenta_id;
	}

	public void setTipo_cuenta_id(int tipo_cuenta_id) {
		this.tipo_cuenta_id = tipo_cuenta_id;
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

	public int isEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [cliente_id=" + cliente_id + ", fecha_creacion=" + fecha_creacion + ", tipo_cuenta_id="
				+ tipo_cuenta_id + ", cbu=" + cbu + ", saldo=" + saldo + ", estado=" + estado + "]";
	}

	public int getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(int numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public int getEstado() {
		return estado;
	}

}
