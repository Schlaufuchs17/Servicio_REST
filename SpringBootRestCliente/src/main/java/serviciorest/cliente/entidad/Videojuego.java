package serviciorest.cliente.entidad;

import org.springframework.stereotype.Component;

@Component
public class Videojuego {
	
	private int id;
	private String nombre;
	private String compañia;
	private int nota;
	
	public Videojuego() {
		super();
	}
	
	public Videojuego(int id, String nombre, String compañia, int nota) {
		this.id = id;
		this.nombre = nombre;
		this.compañia = compañia;
		this.nota = nota;
	}

	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	//@OVERRIDE
	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", compañia=" + compañia + ", nota=" + nota + "]";
	}

	
}
