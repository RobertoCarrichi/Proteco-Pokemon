/**
 * Clase inicial que contiene al método MAIN, por lo que de esta parte todo el programa.
 */
public class Main{
	/**
	 * Método principal del programa para que comience, es de tipo estático para que no deba
	 * crearse una instancia para utilizarlo.
	 */
	public static void main(String[] args) {
		System.out.println("\n\t############### INICIANDO POKEJUEGO ############### \n");
		try {
			System.out.println("Estamos preparando todo...");
			Thread.sleep(3000);
			System.out.println("LISTO! Es momento de comenzar!");
			Thread.sleep(1500);
			System.out.println("Comenzaremos por la creacion de los dos jugadores que van a combatir...");
			Thread.sleep(1800);
			System.out.println();
			/*************************************************************** 
			* INICIA LA CREACION Y ASIGNACION DE POKEMON PARA CADA JUGADOR *
			****************************************************************/
			Jugador jugador1 = new Jugador(1);
			Thread.sleep(1650);
            Jugador jugador2 = new Jugador(2);

            /************************************************ 
			*               INICIA EL COMBATE!              *
			*************************************************/
            Combate combate = new Combate(jugador1,jugador2);
            combate.inicio();
		}catch (Exception e) {
			System.out.println("Al parecer tenemos problemas al cargar contenido del juego.");
			e.printStackTrace();
		}
	}
}