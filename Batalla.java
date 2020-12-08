public class Batalla{
	/**
	*	Esta clase será la que estará almacenando 
	*	toda la información que involucre el proceso de un
	*	combate entre jugadores.
	*/
	// Menu menus = new Menu();
	public static void main(String[] args) {
		System.out.println("INICIA EL COMBATE!");
		Jugador conic = new Jugador();

		for(int i=0;i<7;i++){
			Pokemon poke = new Pokemon("Pikachu");
			conic.pokemones.add(poke);
		}

		conic.mostrarPokemon();
	}

}