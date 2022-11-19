package clienterest.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import clienterest.entidad.Videojuego;

@Service
public class ServicioProxyJuego {

	//SERVIDOR 8080
	public static final String URL = "http://localhost:8080/videojuegos/";

	@Autowired
	private RestTemplate restTemplate;

	//ALTA VIDEOJUEGO
	public Videojuego alta(Videojuego v) {
		try {

			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, v, Videojuego.class);
			System.out.println("Alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("Alta -> El videojuego no se ha podido dar de alta: " + v);
			System.out.println("Alta -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	//BORRAR VIDEOJUEGO
	public boolean borrar(int id) {
		try {

			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("Borrar -> El videojuego no se ha podido borrar, id:  " + id);
			System.out.println("Borrar -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	//MODIFICAR VIDEOJUEGO
	public boolean modificar(Videojuego v) {
		try {

			restTemplate.put(URL + v.getId(), v, Videojuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("Modificar -> El videojuego no se ha modificado, id: " + v.getId());
			System.out.println("Modificar -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	//OBTENER ID
	public Videojuego obtener(int id) {
		try {

			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs = re.getStatusCode();
			if (hs == HttpStatus.OK) {

				return re.getBody();
			} else {
				System.out.println("Respuesta no permitida");
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("Obtener -> El videojuego no se ha encontrado, id: " + id);
			System.out.println("Obtener -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	//LISTAR VIDEOJUEGO
	public List<Videojuego> listar() {

		try {

			ResponseEntity<Videojuego[]> response = restTemplate.getForEntity(URL, Videojuego[].class);
			Videojuego[] arrayVideojuegos = response.getBody();
			//CONVERTIR EL ARRAY EN ARRAY LIST
			return Arrays.asList(arrayVideojuegos);
		} catch (HttpClientErrorException e) {
			System.out.println("Listar -> Error en la lista de videojuegos");
			System.out.println("Listar -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}
}
