package entidades;

import java.sql.Date;

public class Movimiento {

	Integer id;
	Cuenta cuenta;
	Date fecha;
	String detalle;
	Double importe;
	String tipo_movimiento;

	public Movimiento(Integer id, Cuenta cuenta, Date fecha, String detalle, Double importe, String tipo_movimiento) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.detalle = detalle;
		this.importe = importe;
		this.tipo_movimiento = tipo_movimiento;
	}
	public Movimiento(Cuenta cuenta, Date fecha, String detalle, Double importe, String tipo_movimiento) {
		super();

		this.cuenta = cuenta;
		this.fecha = fecha;
		this.detalle = detalle;
		this.importe = importe;
		this.tipo_movimiento = tipo_movimiento;
	}


	public Movimiento() {
		super();
	};

	// Getters y setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public int getNumCuenta() {
		return cuenta.getNumero_cuenta();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

}
