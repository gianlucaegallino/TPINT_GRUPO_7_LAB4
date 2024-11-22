package entidades;

import dao.ClienteDao;

public class Prestamo {
    private int id; // Cambiado de numeroPrestamo a id para coincidir con la BD
    private int clienteId;
    private String fecha;
    private double importePedido;
    private double importeConIntereses;
    private int plazoMeses;
    private double montoMensual;
    private String estado;
    private double interesAnual; // Nuevo campo para el interés anual
    
    private Cliente cliente; // Nuevo campo para el cliente asociado al préstamo

    // Constructor
    public Prestamo(int id, int clienteId, String fecha, double importePedido, double importeConIntereses, int plazoMeses, double montoMensual, String estado, double interesAnual) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.importePedido = importePedido;
        this.importeConIntereses = importeConIntereses;
        this.plazoMeses = plazoMeses;
        this.montoMensual = montoMensual;
        this.estado = estado;
        this.interesAnual = interesAnual; // Asignación de interesAnual
    }
    
    public Cliente getCliente() {
        if (this.cliente == null) {
            this.cliente = ClienteDao.obtenerClientePorId(this.clienteId); // Suponiendo que tienes un DAO que obtiene el cliente
        }
        return this.cliente;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
