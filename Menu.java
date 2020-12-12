import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Proporciona al usuario los distintos menús de elección durante el combate y construcción
 * de instancias como el equipo de pokémon de un jugador.
 */
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
	 * Este constructor permite la creación de una instancia sin parámetros iniciales.
	 */
	public Menu(){}

	/**
	 * Ejecuta el ciclo de elección de un equipo para un jugador. El segundo
	 * parámetro sería el "modo", si es "true" se eligen los pokemon aleatoriamente
	 * y si es "false" se solicita al usuario que escoja uno por uno.
	 * 
	 * @param jugador        Indica el jugador al que se le está asignando el
	 *                       equipo.
	 * @param aleatoriamente Expresa con un "true" que el jugador si quiso que se
	 *                       eligieran aleatoriamente, si es "false" entonces se
	 *                       sigue el proceso de elección individual.
	 * @throws InterruptedException
	 */
	public void eleccionPokemon(Jugador jugador, boolean aleatoriamente) throws InterruptedException {
		/**
		 * DISPONIBLES será una copia de los nombres que hay disponibles en el juego.
		 */
		ArrayList<String> disponibles = pokemonDisponibles;
		if (aleatoriamente) {
			System.out.println("\n\t#######################################################");
			System.out.println("\t#  COMIENZA EL PROCESO DE ELECCION DE TU EQUIPO DE    #");
			System.out.println("\t#            POKEMON DE FORMA ALEATORIA               #");
			System.out.println("\t#                                                     #");
			System.out.println("\t# Se elegirán aleatoriamente y al final se mostrarán  #");
			System.out.println("\t# los pokemon que conforman a tu equipo.              #");
			System.out.println("\t#######################################################\n");
			System.out.println("\n\tEstamos eligiendo tus pokemon, un momento por favor...\n");
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
			// Thread.sleep(3000);
			System.out.println("Hemos terminado de elegir a tus pokemon!\n");
		} else {
			System.out.println("\n\t#######################################################");
			System.out.println("\t#  COMIENZA EL PROCESO DE ELECCION DE TU EQUIPO DE    #");
			System.out.println("\t#                      POKEMON                        #");
			System.out.println("\t#                                                     #");
			System.out.println("\t# Se indicaran solo los nombres que tiene disponible  #");
			System.out.println("\t# el entrenador para elegir, pues sus estadísticas    #");
			System.out.println("\t# se elegiran aleatoriamente para que exista igualdad #");
			System.out.println("\t# de condiciones entre todos los pokemon.             #");
			System.out.println("\t#######################################################\n");
			int elegidos=6;
			for (int i = 0; i < 1; i++) {
				boolean exit = false;
				while (! exit) {
					System.out.println("Elige uno de los siguientes nombres: ");
					System.out.println("\t"+disponibles.toString());
					System.out.printf(" ~> ");
					String apodo = scan.nextLine();
					if (!disponibles.contains(apodo)) {
						System.out.println("\n\tOH NO! No está en la lista, puede que te hayas equivocado al escribir. Elige con cuidado.\n");
					} else {
						/* 
							Si se encontró el pokémon en la lista, por lo que se añadirá a la lista del jugador
							y posteriormente se eliminará de la lista de "pokémon disponibles" para que no se repitan
							pokémon en el equipo.
						*/
						jugador.addPokemon(new Pokemon(apodo));
						elegidos-=1;
						System.out.printf("\n\tCorrecto! "+apodo+" ahora es parte de tu equipo. ");
						if ( elegidos != 0 ) {
							System.out.printf("Aun debes elegir "+elegidos+" pokemon, sigamos...\n\n");
						} else {
							System.out.printf("Ya has terminado de elegir a tu equipo!\n");
						}
						disponibles.remove(apodo);						
						/* 
							Se cambia de valor a EXIT para que ya no pida otro nombre ya que si se encontró
							esto se hace para que se pueda pasar a la elección de un nuevo pokémon.
						*/
						exit=true; 
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	public int mostrarOpcionesTurno(){
		int opcion; 
		boolean exit = false;
		do{
			System.out.println(" Que deseas hacer?");
			System.out.println("   1. Realiza un ataque.");
			System.out.println("   2. Usar pocion.");
			System.out.println("   3. Cambiar pokemon.");
			System.out.println("   4. Rendirse.\n");
			System.out.printf(" Elige con el numero: \n ~> ");
			opcion = scan.nextInt();
			if ( opcion==1 || opcion==2 || opcion==3 || opcion==4 || opcion==5 ) {
				exit = true;
			} else {
				System.out.println("\tCHISPAS! No has elegido una opcion permitida, intenta otra vez...");
			}
		}while(!exit);
		return opcion;
	}

	/**
	 * 
	 * @param jugador
	 * @return
	 */
	public int elegirPocion(Jugador jugador){
		jugador.mostrarPociones();
		System.out.printf("Elige mediante el numero de pocion: \n ~> ");
		int pocion = scan.nextInt();
		if ( pocion == 1 || pocion == 2 || pocion == 3 || pocion == 4 || pocion == 5 || pocion == 6 ) {
			if (jugador.getPociones().get(pocion).getEstado()){
				System.out.println("\n\tOH NO! No has elegido una pocion que ya has utilizado, elige una sin utilizar. \n");
				elegirPocion(jugador);			
			} else {
				return pocion;
			}
		} else {
			System.out.println("\n\tOH NO! No has elegido un numero de pocion valido, intenta otra vez. \n");
			elegirPocion(jugador);
		}
		return -1;
	}

	/**
	 * 
	 * @param jugador
	 * @return
	 */
	public int elegirPokemon(Jugador jugador){
		jugador.mostrarPokemon();
		System.out.printf("Elige mediante el numero de pokemon: \n ~> ");
		int pokemon = scan.nextInt();
		if ( pokemon == 1 || pokemon == 2 || pokemon == 3 || pokemon == 4 || pokemon == 5 || pokemon == 6 ) {
			return pokemon;
		} else {
			System.out.println("\n\tOH NO! No has elegido un numero de pocion valido, intenta otra vez. \n");
			elegirPokemon(jugador);
		}
		return -1;
	}

	/**
	 * 
	 * @param jugador
	 * @return
	 */
	public int elegirPeleador(Jugador jugador){
		System.out.println("\n\tEste es tu peleador actual: \n");
		jugador.getPeleador().mostrarInfo();
		System.out.printf("Con que pokemon quieres pelear ahora? Elige con el numero: \n ~> ");
		int peleador = scan.nextInt();
		if ( peleador == 1 || peleador == 2 || peleador == 3 || peleador == 4 || peleador == 5 || peleador == 6 ) {
			if ( jugador.getPokemones().get(peleador-1).getEstado() ){
				return peleador-1;
			} else {
				System.out.println("\n\tAY NO! Has elegido un pokemon debilitado, deberas elegir otro pokemon.\n");
				elegirPeleador(jugador);
			}
		} else {
			System.out.println("\n\tAY NO! Has elegido un pokemon que no existe, elige con cuidado.\n");
			elegirPeleador(jugador);
		}
		return -1;
	}
}