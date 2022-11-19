package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Videojuego;

@Component
public class DaoVideojuego {

	public List<Videojuego> listaJuegos;
	public int id;

	public DaoVideojuego() {
		System.out.println("DaoPersona -> Creando lista de personas");
		listaJuegos = new ArrayList<Videojuego>();
		Videojuego j1 = new Videojuego(id++, "God of War Ragnarok", "Santa Monica", 10);
		Videojuego j2 = new Videojuego(id++, "Metal Gear Solid", "Konami", 10);
		Videojuego j3 = new Videojuego(id++, "Warcraft The Frozen Throne", "Blizzard", 8);
		Videojuego j4 = new Videojuego(id++, "Halo Reach", "343i", 6);
		Videojuego j5 = new Videojuego(id++, "Tomb Raider The Last Revelation", "Eidos", 7);
		listaJuegos.add(j1);
		listaJuegos.add(j2);
		listaJuegos.add(j3);
		listaJuegos.add(j4);
		listaJuegos.add(j5);
	}

	public String addJuego(Videojuego v) {

		for (Videojuego p : listaJuegos) {
			if (p.getNombre().equalsIgnoreCase(v.getNombre())) {
				return null;
			}
		}
		v.setId(id++);
		listaJuegos.add(v);
		return "Añadido";
	}

	public Videojuego delete(int id) {
		try {
			Videojuego j = get(id);
			int n = listaJuegos.indexOf(j);
			return listaJuegos.remove(n);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Delete -> Videojuego fuera de rango");
			return null;
		}
	}

	public Videojuego update(Videojuego v) {
		try {
			Videojuego vAux = get(v.getId());
			vAux.setNombre(v.getNombre());
			vAux.setCompañia(v.getCompañia());
			vAux.setNota(v.getNota());

			return vAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Update -> Persona fuera de rango");
			return null;
		}
	}

	public Videojuego get(int id) {
		for (Videojuego p : listaJuegos) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	public List<Videojuego> list() {
		return listaJuegos;
	}

}
