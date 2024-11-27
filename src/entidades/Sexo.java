package entidades;

public class Sexo {
    private int id;
    private String descripcion;

    public Sexo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    
	public Sexo(int id) {
		super();
		this.id = id;
	}


	public Sexo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
}



