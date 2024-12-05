package entidades;

public class Provincia {
    private int id;
    private String nombre;

    // Constructor
    public Provincia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Provincia() {
		// TODO Auto-generated constructor stub
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
}
