package entidades;

public class Nacionalidad {
    private int id;
    private String nombre;

    public Nacionalidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Nacionalidad(int id) {
		super();
		this.id = id;
	}

	public Nacionalidad(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Nacionalidad() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
