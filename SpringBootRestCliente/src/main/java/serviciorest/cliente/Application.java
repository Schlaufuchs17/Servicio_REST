package serviciorest.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Videojuego;

import serviciorest.cliente.servicio.ServicioProxyVideojuego;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ServicioProxyVideojuego spp;

	@Autowired
	private ApplicationContext context;


	public boolean salir = false;
	// Guardamos la opcion del usuario
	public static int opcion;
	static Scanner sn = new Scanner(System.in);
	static Scanner sc = new Scanner(System.in);
	String datos;
	int nota;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Metodo main que lanza la aplicacion
	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** Arrancando el cliente REST ******");
		
		while (!salir) {

			System.out.println("1. Opcion 1: NUEVO VIDEOJUEGO ");
			System.out.println("2. Opcion 2: BUSCAR VIDEOJUEGO POR ID ");
			System.out.println("3. Opcion 3: MODIFICAR VIDEOJUEGO POR ID ");
			System.out.println("4. Opcion 4: LISTAR TODOS LOS  VIDEOJUEGOS ");
			System.out.println("5. Opcion 5: BORRAR VIDEOJUEGO POR ID ");
			System.out.println("6. Salir ");

			System.out.println("Escribe una de las opciones");
			opcion = sn.nextInt();
			switch (opcion) {

		//ALTA VIDEOJUEGO
			case 1:
				System.out.println("Has seleccionado la opcion 1");
				System.out.println("*********** ALTA VIDEOJUEGO ***************");
				Videojuego videojuego = new Videojuego();
				System.out.println("POR FAVOR INTRODUCE EL NOMBRE");
				datos= sc.next();
				videojuego.setNombre(datos);
				System.out.println("POR FAVOR INTRODUCE LA COMPAÑIA");
				datos= sc.next();
				videojuego.setCompañia(datos);
				System.out.println("POR FAVOR INTRODUCE LA NOTA");
				nota = sn.nextInt();
				videojuego.setNota(nota);

				Videojuego pAlta = spp.alta(videojuego);
				System.out.println(" Videojuego dado de alta " + pAlta);
				break;

		//BUSCAR VIDEOJUEGO
			case 2:
				System.out.println("Has seleccionado la opcion 2");
				System.out.println("************  BUSCAR Videojuego POR ID ***************");
				int id;
				System.out.println("POR FAVOR INTRODUCE EL ID DEL VIDEOJUEGO");
				 id= sn.nextInt();
				
				 videojuego = spp.obtener(id);
				System.out.println(" videojuego con id : " + id + " es " + videojuego);

				break;

		//MODIFICAR VIDEOJUEGO
			case 3:
				System.out.println("Has seleccionado la opcion 3");
				System.out.println("********* MODIFICAR VIDEOJUEGO POR ID*************");
				System.out.println("POR FAVOR INTRODUCE EL ID DEL VIDEOJUEGO");
				 id= sn.nextInt();
				Videojuego pModificar = new Videojuego();
				pModificar.setId(id);
				System.out.println("POR FAVOR INTRODUCE EL NOMBRE:");
				datos= sc.next();
				pModificar.setNombre(datos);
				System.out.println("POR FAVOR INTRODUCE LA COMPAÑIA:");
				datos= sc.next();
				pModificar.setCompañia(datos);
				System.out.println("POR FAVOR INTRODUCE LA NOTA:");
				nota = sn.nextInt();
				pModificar.setNota(nota);
				boolean modificado = spp.modificar(pModificar);
				System.out.println(" VIDEOJUEGO MODIFICADO:" + modificado);
				break;

		//LISTAR VIDEOJUEGOS
			case 4:
				System.out.println("Has seleccionado la opcion 4");
				System.out.println("********** LISTAR VIDEOJUEGO ***************");
				List<Videojuego> listarjuegos = spp.listar(null);
				/*Recorremos la lista y la imprimimos con funciones Lambda, aunque
				tambien se podria haber usado for-each*/
				listarjuegos.forEach((v) -> System.out.println(v));

				break;

		//BORRAR VIDEOJUEGO
			case 5:
				System.out.println("Has seleccionado la opcion 5");
				System.out.println("*********** BORRAR VIDEOJUEGO POR ID ****************** ");
				System.out.println("POR FAVOR INTRODUCE EL ID DEL VIDEOJUEGO");
				
				id= sn.nextInt();
				boolean borrada = spp.borrar(id);
				System.out.println(" Videojuego con " + id + "?" + borrada);
				break;

		//SALIR
			case 6:
				System.out.println("Has seleccionado la opcion 6");
				System.out.println(" PROGRAMA FINALIZADO  ");
				salir = true;
				break;
			default:
				System.out.println("Solo números entre 1 y 6");
			}
		}

		// Mandamos parar la aplicacion
		pararAplicacion();

	}

	public void pararAplicacion() {

		SpringApplication.exit(context, () -> 0);

	}
}
