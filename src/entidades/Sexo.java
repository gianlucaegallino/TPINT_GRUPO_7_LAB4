package entidades;

public class Sexo {
    private int id;
    private String descripcion;

    public Sexo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}



