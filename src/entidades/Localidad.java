package entidades;

public class Localidad {
    private int id;
    private String nombre;
    private int provinciaId;

    // Constructor
    public Localidad(int id, String nombre, int provinciaId) {
        this.id = id;
        this.nombre = nombre;
        this.provinciaId = provinciaId;
    }

    // Getter y Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para provinciaId
    public int getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(int provinciaId) {
        this.provinciaId = provinciaId;
    }
}