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
    


    // Constructor
    public Prestamo(int id,  Date fecha, double importePedido, double importeConIntereses, int plazoMeses, double montoMensual, String estado, double interesAnual, Cliente cliente) {
        this.id = id;

        this.fecha = fecha;
        this.importePedido = importePedido;
        this.importeConIntereses = importeConIntereses;
        this.plazoMeses = plazoMeses;
        this.montoMensual = montoMensual;
        this.estado = estado;
        this.interesAnual = interesAnual; // Asignación de interesAnual
        
        this.cliente = cliente;
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


    // Getters y Setters
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
}
