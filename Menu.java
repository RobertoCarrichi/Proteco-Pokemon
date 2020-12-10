import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu{

	/******************************************
     *         INSTANCIAS DE APOYO            *
     ******************************************/
    Scanner scan = new Scanner(System.in);

    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/
	/**
	 * Se trata de una lista que contiene LOS APODOS de los pokémon disponibles.
	 */
	ArrayList<String> pokemonDisponibles = new ArrayList<String>(Arrays.asList("Wartortle", "Golduck", "Poliwhirl","Charmeleon", "Ninetales", "Rapidash", "Ivysaur", "Bayleef", "Grovyle", "Raichu", "Electabuzz", "Jolteon"));

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

	/**
	 * La funcionalidad principal de este método
	 * será proporcionar al usuario los distintos
	 * menús durante el combate.
	 */

	/**
	 * Este constructor permite la creación de una instancia sin parámetros.
	 */
	public Menu(){}

	/**
	 * Ejecuta el ciclo de elección de un equipo para un jugador.
	 * El segundo parámetro sería el "modo", si es "true" se eligen los pokemon aleatoriamente y si es "false" se
	 * solicita al usuario que escoja uno por uno.
	 * @param jugador Indica el jugador al que se le está asignando el equipo.
	 * @param aleatoriamente Expresa con un "true" que el jugador si quiso que se eligieran aleatoriamente, si es "false"
	 * entonces se sigue el proceso de elección individual.
	 */
	public void eleccionPokemon(Jugador jugador, boolean aleatoriamente){
		/**
		 * DISPONIBLES será una copia de los nombres que hay disponibles en el juego.
		 */
		ArrayList<String> disponibles = pokemonDisponibles;
		if (aleatoriamente) {
			/* 
				Si entra aquí es porque existe un "true". 
				Para la implementación de elegir aleatoriamente se dividirá la lista de DISPONIBLES en cuatro 
				bloques diferentes:
					0 - 2 Los tipo agua
					3 - 5 Los tipo fuego
					6 - 8 Los tipo hierba
					9 - 11 Los tipo eléctrico
				Primero se elegirá aleatoriamente 1 del primer bloque, 1 del segundo y así sucesivamente.
			*/
			// Bloque de 0 - 2
			int indiceElegido = (int) (Math.random() * 3);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			// Al término de haber elegido un pokémon, este se elimina de la copia de las listas.
			// Haciendo que el nuevo bloque sea de nuevos valores
			disponibles.remove(indiceElegido);
			
			// Bloque de 2 - 4
			indiceElegido = (int)((Math.random() * 3) + 2);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			disponibles.remove(indiceElegido);
			
			// Bloque de 4 - 6
			indiceElegido = (int)((Math.random() * 3) + 4);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			disponibles.remove(indiceElegido);
			
			// Bloque de 6 - 8
			indiceElegido = (int)((Math.random() * 3) + 6);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			disponibles.remove(indiceElegido);

			/* 
				Terminó el primer bloque, el jugador ya tiene 4 de los 6 pokemones en su equipo, se deberán elegir dos más
				aleatoriamente con los pokémon restantes.
			*/
			// Se elegirá cualquiera de los 8 pokes restantes
			// El bloque es de 0 - 6
			indiceElegido = (int)(Math.random() * 7);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			disponibles.remove(indiceElegido);

			// Se añadió un quinto pokemón, por lo que en la lista ya solo quedan 6 disponibles
			// en un bloque de 0 - 5
			indiceElegido = (int)(Math.random() * 6);
			jugador.addPokemon(new Pokemon(disponibles.get(indiceElegido)));
			disponibles.remove(indiceElegido);
			
			/* 
				SE HAN TERMINADO DE ELEGIR POKEMON DE FORMA ALEATORIA.
			*/
		} else {
			System.out.println("#######################################################");
			System.out.println("#  COMIENZA EL PROCESO DE ELECCION DE TU EQUIPO DE    #");
			System.out.println("#                      POKEMON                        #");
			System.out.println("#                                                     #");
			System.out.println("# Se indicaran solo los nombres que tiene disponible  #");
			System.out.println("# el entrenador para elegir, pues sus estadísticas    #");
			System.out.println("# se elegiran aleatoriamente para que exista igualdad #");
			System.out.println("# de condiciones entre todos los pokemon.             #");
			System.out.println("#######################################################");

			for (int i = 0; i < 6; i++) {
				String apodo = "";
				while (! disponibles.contains(apodo)) {
					System.out.println("Elige uno de los siguientes nombres: ");
					System.out.println(disponibles.toString());
					apodo = scan.nextLine();
					if (!disponibles.contains(apodo)) {
						System.out.println("OH NO! No está en la lista.");
					} else {
						System.out.println("Si se encontro.");
						break;
					}
				}
			}
		}
	}
}