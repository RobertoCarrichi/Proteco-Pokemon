public class Batalla{
	/**
	*	Esta clase será la que estará almacenando 
	*	toda la información que involucre el proceso de un
	*	combate entre jugadores.
	*/
	public static void main(String[] args) {
		System.out.println("\n\t############### INICIANDO POKEJUEGO ############### \n");
		try {
			System.out.println("Estamos preparando todo...");
			// Thread.sleep(3000);
			System.out.println("LISTO! Es momento de comenzar!");
			// Thread.sleep(1500);
			System.out.println("Comenzaremos por la creacion de los dos jugadores que van a combatir...");
			// Thread.sleep(1800);

			/*************************************************************** 
			* INICIA LA CREACION Y ASIGNACION DE POKEMON PARA CADA JUGADOR *
			****************************************************************/

			Jugador jugador1 = new Jugador(1);
			// Thread.sleep(1650);
			Jugador jugador2 = new Jugador(2);
			Combate combate = new Combate(jugador1, jugador2);
			combate.inicio();			
		}catch (Exception e) {
			System.out.println("Al parecer tenemos problemas al cargar contenido del juego.");
			e.printStackTrace();
		}
	}

}