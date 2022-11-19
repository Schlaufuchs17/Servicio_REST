package clienterest;

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

import clienterest.entidad.Videojuego;
import clienterest.servicio.ServicioProxyJuego;

@SpringBootApplication
public class ClienteRestApplication implements CommandLineRunner {
	@Autowired
	private ServicioProxyJuego sp;

	@Autowired
	private ApplicationContext context;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {

			String texto = "";
			boolean continuar = true;
			int num;
			int nota = 0;
			Videojuego videojuego, vRespuesta;
			boolean respues;

			boolean interruptor = false;

			do {
				System.out.println("\nElige una número del menu:\n" + 
			"1. Añadir videojuego\n" + 
			"2. Eliminar videojuego\n" + 
			"3. Modificar videojuego\n"+ 
			"4. Obtener un videojuego\n" + 
			"5. Listar todos los videojuegos\n"+ 
			"6. Salir");
				
				num = Integer.parseInt(sc.nextLine());

				switch (num) {
			//AÑADIR VIDEOJUEGO
				case 1:
					videojuego = new Videojuego();
					System.out.println("Titulo");
					texto = sc.nextLine();
					videojuego.setNombre(texto);
					System.out.println("Compañia");
					texto = sc.nextLine();
					videojuego.setCompañia(texto);
					System.out.println("Nota (0-10)");
					do {
						try {
							texto = sc.nextLine();

							if (Integer.parseInt(texto) >= 0 & Integer.parseInt(texto) < 11) { //NOTA QUE ESTE COMPRENDIDA ENTRE 1-10
								interruptor = true;
							} else {
								System.out.println("Nota incorrecta");

							}
						} catch (NumberFormatException e) {
							System.out.println("Introduzca una nota valida (0-10)");
						}
					} while (!interruptor);
					videojuego.setNota(nota);
					System.out.println("Enviando");
					vRespuesta = sp.alta(videojuego);
					System.out.println(vRespuesta);
					break;
					
			//ELIMINAR VIDEOJUEGO
				case 2:
					System.out.println("Introduzca la ID");
					texto = sc.nextLine();
					respues = sp.borrar(Integer.parseInt(texto));
					System.out.println("Eliminado" + respues);
					break;
			//MODIFICAR VIDEOJUEGO
				case 3:
					videojuego = new Videojuego();
					System.out.println("Introduzca la ID del videojuego para Modificar");
					videojuego.setId(Integer.parseInt(sc.nextLine()));
					System.out.println("Introduzca el nuevo Nombre");
					videojuego.setNombre(sc.nextLine());
					System.out.println("Introduzca la nueva compañia");
					videojuego.setCompañia(sc.nextLine());
					System.out.println("Introduzca la nueva nota");
					videojuego.setNota(Integer.parseInt(sc.nextLine()));
					respues = sp.modificar(videojuego);
					System.out.println("¿Se ha modificado con exito? " + respues);
					break;
					
			//OBTENER VIDEOJUEGO
				case 4:
					System.out.println("Introduzca la ID del videojuego para buscar");
					texto = sc.nextLine();
					videojuego = sp.obtener(Integer.parseInt(texto));
					if (videojuego != null)
						System.out.println(videojuego);

					break;
					
			//LISTAR VIDEOJUEGOS
				case 5:
					List<Videojuego> vJuegos;
					vJuegos = sp.listar();
					for (Videojuego v : vJuegos)
						System.out.println(v.toString());

					break;
			//SALIR
				case 6:
					System.out.println("Saliendo");

					continuar = false;
					break;
				default:
					System.out.println("Introduzca un numero de entre las opciones");
				}

			} while (continuar);

		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}

		System.out.println("CLIENTE: Fin del programa");
		pararAplicacion();
	}

//PARAR APLICACION
	public void pararAplicacion() {

		SpringApplication.exit(context, () -> 0);

	}

}
