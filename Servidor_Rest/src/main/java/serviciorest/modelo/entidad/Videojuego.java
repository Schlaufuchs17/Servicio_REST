package serviciorest.modelo.entidad;

public class Videojuego {

	private int id;
	private String nombre;
	private String compañia;
	private int nota;

	public Videojuego(int id, String nombre, String compañia, int nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.compañia = compañia;
		this.nota = nota;

	}

	//ID
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	//NOMBRE
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//COMPAÑIA
	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	//NOTA
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Videojuego [Nombre=" + nombre + ", Compañia=" + compañia + ", Nota=" + nota + ", ID=" + id + "]";
	}

}
