package entidades;

import java.sql.Date;

public class Prestamo {
    private int id; 
    private Cliente cliente;
    private Date fecha;
    private double importePedido;
    private double importeConIntereses;
    private int plazoMeses;
    private double montoMensual;
    private String estado;
    private double interesAnual;
    private String cbu_cuenta;
    private String pagos_restantes;
    


    public Prestamo() {
		super();
	}

	// Constructor
    public Prestamo(int id,  Date fecha, double importePedido, double importeConIntereses, int plazoMeses, double montoMensual, String estado, double interesAnual, Cliente cliente, String cbu_cuenta) {
        this.id = id;

        this.fecha = fecha;
        this.importePedido = importePedido;
        this.importeConIntereses = importeConIntereses;
        this.plazoMeses = plazoMeses;
        this.montoMensual = montoMensual;
        this.estado = estado;
        this.interesAnual = interesAnual; // Asignaci�n de interesAnual
        
        this.cliente = cliente;
        this.cbu_cuenta = cbu_cuenta;
    }
    
    public Prestamo(int id,  Date fecha, double importePedido, double importeConIntereses, int plazoMeses, double montoMensual, String estado, double interesAnual, Cliente cliente, String cbu_cuenta, String pagosRestantes) {
        this.id = id;

        this.fecha = fecha;
        this.importePedido = importePedido;
        this.importeConIntereses = importeConIntereses;
        this.plazoMeses = plazoMeses;
        this.montoMensual = montoMensual;
        this.estado = estado;
        this.interesAnual = interesAnual; // Asignaci�n de interesAnual
        
        this.cliente = cliente;
        this.cbu_cuenta = cbu_cuenta;
        this.pagos_restantes = pagosRestantes;
    }

    // Getters y Setters
    
    
    
    public String getCbu_cuenta() {
		return cbu_cuenta;
	}

	public void setCbu_cuenta(String cbu_cuenta) {
		this.cbu_cuenta = cbu_cuenta;
	}

	public void setCliente(Cliente cli) {

        this.cliente = cli; 
}
    public Cliente getCliente() {

        return this.cliente;
    }
    
    public int getIdCliente() {
    	return this.cliente.getIdCliente();
    }
    public void setIdCliente(int id) {
    	this.cliente.setIdCliente(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImportePedido() {
        return importePedido;
    }

    public void setImportePedido(double importePedido) {
        this.importePedido = importePedido;
    }

    public double getImporteConIntereses() {
        return importeConIntereses;
    }

    public void setImporteConIntereses(double importeConIntereses) {
        this.importeConIntereses = importeConIntereses;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public double getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(double montoMensual) {
        this.montoMensual = montoMensual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(double interesAnual) {
        this.interesAnual = interesAnual;
    }

	public String getPagos_restantes() {
		return pagos_restantes;
	}

	public void setPagos_restantes(String pagos_restantes) {
		this.pagos_restantes = pagos_restantes;
	}
    
    
}
