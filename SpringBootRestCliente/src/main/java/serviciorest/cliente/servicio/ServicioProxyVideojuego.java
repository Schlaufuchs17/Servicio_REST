package serviciorest.cliente.servicio;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Videojuego;

//Alta ServicioProxyVideojuego dentro de Spring
@Service
public class ServicioProxyVideojuego {

	
	public static final String URL = "http://localhost:8050/videojuegos/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Videojuego obtener(int id){
		try {
			
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs= re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				
				return re.getBody();
			}else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("El videojuego NO se ha encontrado, ID: " + id);
		    System.out.println(" Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	
	public Videojuego alta(Videojuego p){
		try {
			
			//Para hacer un post de una entidad usamos el metodo postForEntity
			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, p, Videojuego.class);
			System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println(" El videojuego NO se ha dado de alta, id: " + p);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	public boolean modificar(Videojuego p){
		try {
			
			restTemplate.put(URL + p.getId(), p, Videojuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego NO se ha modificado, id: " + p.getId());
		    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	public boolean borrar(int id){
		try {
			
			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println(" El videojuego NO se ha borrado, id: " + id);
		    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	public List<Videojuego> listar(String nombre){
		String queryParams = "";		
		if(nombre != null) {
			queryParams += "?nombre=" + nombre;
		}
		
		try {
		
			ResponseEntity<Videojuego[]> response =
					  restTemplate.getForEntity(URL + queryParams,Videojuego[].class);
			Videojuego[] arrayJuegos = response.getBody();
			//Convertimos el array en un arraylist
			return Arrays.asList(arrayJuegos);
		} catch (HttpClientErrorException e) {
			System.out.println("Listar -> Error al obtener la lista de videojuegos");
		    System.out.println("Listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
}
