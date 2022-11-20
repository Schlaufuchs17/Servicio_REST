package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Videojuego;

//Dar de alta un objeto dentro del contexto Spring.
@Component
public class DaoVideojuego {

	public List<Videojuego> listaJuegos;
	public int id;

	//Se crea la lista de videojuegos
	public DaoVideojuego() {
		System.out.println("DaoVideojuegos -> Creando la lista de videojugos!");
		listaJuegos = new ArrayList<Videojuego>();
		Videojuego p1 = new Videojuego(id++, "Bloodborne", "From Software", 91);// ID: 0
		Videojuego p2 = new Videojuego(id++, "Red Dead Redemption 2", "Rockstar", 95);// ID: 1
		Videojuego p3 = new Videojuego(id++, "Warcraft 3 The Frozen Throne", "Blizzard Entertainment", 85);// ID: 2
		Videojuego p4 = new Videojuego(id++, "Tomb Raider The Last Revelation", "Eidos", 82);// ID:3
		Videojuego p5 = new Videojuego(id++, "Metal Gear Solid", "Konami", 87);// ID:4
		listaJuegos.add(p1);
		listaJuegos.add(p2);
		listaJuegos.add(p3);
		listaJuegos.add(p4);
		listaJuegos.add(p5);
	}

	//Devuelve un videojuego a partir de su posicion del array
	
	public Videojuego get(int posicion) {
		try {
			return listaJuegos.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Videojuego fuera de rango");
			return null;
		}
	}

	public List<Videojuego> list() {
		return listaJuegos;
	}

	public String add(Videojuego p) {

		for (Videojuego x : listaJuegos) {
			if (x.getNombre().equalsIgnoreCase(p.getNombre())) {
				return null;
			}

		}
		p.setId(id++);
		listaJuegos.add(p);
		return "añadido";
	}

	public Videojuego delete(int posicion) {
		try {
			return listaJuegos.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Videojuego fuera de rango");
			return null;
		}
	}

	public Videojuego update(Videojuego p) {
		try {
			Videojuego pAux = listaJuegos.get(p.getId());
			pAux.setNombre(p.getNombre());
			pAux.setCompañia(p.getCompañia());
			pAux.setNota(p.getNota());

			return pAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Videojuego fuera de rango");
			return null;
		}
	}


	public List<Videojuego> listByNombre(String nombre) {
		List<Videojuego> listajuegoAux = new ArrayList<Videojuego>();
		for (Videojuego p : listaJuegos) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				listajuegoAux.add(p);
			}
		}
		return listajuegoAux;
	}
}
