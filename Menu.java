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
	/**
	 * Instancia de apoyo que permite solicitar opciones al usuario desde su teclado.
	 */
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
     * @throws InterruptedException Avisa una posible interface la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
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
			Thread.sleep(3500);
			System.out.println("Hemos terminado de elegir a tus pokemon!\n");
			Thread.sleep(2500);
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
			System.out.println("\n\tEspero estes listo/a!\n");
			Thread.sleep(6000);
			System.out.println("\n\tEsto comienza ya!\n");
			Thread.sleep(2000);
			int elegidos=6;
			for (int i = 0; i < 6; i++) {
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

				if (i < 5) {
					System.out.println("\n\tPasemos a la siguiente elección...\n");
					Thread.sleep(2000);
				}else{
					System.out.println("\n\tHas terminado de elegir tus pokemon!\n");
					Thread.sleep(2000);
				}
			}
		}
	}

	/**
	 * Método que muestra las opciones que tiene un jugador al inicial su turno.
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
	 * Permite mostar al jugador en turno las pociones que tiene y en que estado se encuentran, 
	 * posteriormente le solicita elegir alguna que no haya sido utilizada con anterioridad.
	 * @param jugador Requiere de una instancia de tipo JUGADOR para saber la lista de pociones
	 * correcta.
	 * @return Retorna el índice que hace referencia a la poción elegida por el jugador.
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
	 * Método genérico que muestra al equipo actual y solicita la elección de alguno de los pokémon en
	 * el equipo.
	 * @param jugador Requiere del jugador que mande a llamar a este método.
	 * @return Retorna el índice que hará referencia al pokemón que el jugador eligió.
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
	 * Permite poder cambiar de pokémon peleador.
	 * @param jugador Se requiere de un entrenador para efectuar el cambio.
	 * @return Retorna el nuevo índice que representará al nuevo pokémon peleador del jugador que
	 * solicitó esta acción.
	 */
	public int elegirPeleador(Jugador jugador){
		jugador.mostrarPokemon();
		System.out.println("\n\tEste es tu peleador actual: \n");
		jugador.getPeleador().mostrarInfo();
		System.out.printf("Con que pokemon quieres pelear ahora? Elige con el numero, si todos se encuentran debilitados elige un cero.\n ~> ");
		int peleador = scan.nextInt();
		if ( peleador == 1 || peleador == 2 || peleador == 3 || peleador == 4 || peleador == 5 || peleador == 6 ) {
			if ( jugador.getPokemones().get(peleador-1).getEstado() ){
				return peleador-1;
			} else {
				System.out.println("\n\tAY NO! Has elegido un pokemon debilitado, deberas elegir otro pokemon.\n");
				elegirPeleador(jugador);
			}
		}else if( peleador == 0 ){
			System.out.println("Todos los pokemon de "+jugador.getNombre().toUpperCase()+" han quedado fuera de juego ):\n");
		}else {
			System.out.println("\n\tAY NO! Has elegido un pokemon que no existe, elige con cuidado.\n");
			elegirPeleador(jugador);
		}
		return -1;
	}

	/**
	 * Permite mostrar información de los ataques que tiene el pokémon peleador del jugador y solicita
	 * que se eliga uno para efectual el ataque.
	 * @param jugador Dice que jugador elegirá el ataque.
	 * @return Retorna un valor entre 0 y 1 que represente alguno de los movimientos que el pokémon
	 * peleador del jugador tiene disponibles.
	 */
	public int elegirAtaque(Jugador jugador){
		System.out.println("Los ataques disponibles de tu pokemon actual son: ");
		System.out.println("    1 : "+jugador.getPeleador().getMovimiento(0));
		System.out.println("    2 : "+jugador.getPeleador().getMovimiento(1));
		System.out.printf("\nElige el ataque mediante el numero:\n ~> ");
		int ataque = scan.nextInt();
		if (ataque == 1 || ataque == 2) {
			return ataque - 1;
		} else {
			System.out.println("\tCHISPAS! Has elegido un ataque que tu pokemon no tiene. Vuelve a elegir.\n");
			elegirAtaque(jugador);
		}
		return -1;
	}
}