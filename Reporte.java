/**
 * Esta clase será la que estará almacenando 
 * toda la información que involucre el proceso de un
 * combate entre jugadores. 
 * Almacena la información en un archivo txt.
 */
public class Reporte {
    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/

    /**
     * 
     */
    int turnosTotales;

    /**
     * 
     */
    int ataquesRealizados;

    /**
     * 
     */
    int pocionesUtilizadas;

    /**
     * 
     */
    int totalBajas;
    
    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/
    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public Reporte(Jugador jugador1, Jugador jugador2) {
        this.turnosTotales = 0;
        reportarJugadores(jugador1,jugador2);
    }
    
    /*************************************************
     *      MÉTODOS DE INSTANCIA: REPORTES           *
     * Cada uno de los siguientes métodos tienen     *
     * la función de imprimir en un mismo archivo    *
     * que mantiene el historial de todo el combate. *
     *************************************************/

    /**
     * 
     */
    public void reportarTurnoInicial(Jugador jugador){
        System.out.printf(" *** -> ");
        System.out.println("\tEl primer turno en el combate es para "+jugador.getNombre()+"!");
    }

    /**
     * 
     * @param jugador
     */
    public void reportarTurno(Jugador jugador){
        System.out.printf(" *** -> ");
        System.out.println("\tEs turno de "+jugador.getNombre()+"!");
        this.turnosTotales+=1;
    }

    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public void reportarJugadores(Jugador jugador1, Jugador jugador2) {
        System.out.printf(" *** -> ");
        System.out.println("\t\tLos jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase());
    }
    
    /**
     * 
     * @param jugador
     * @param pocion
     * @param pokemon
     */
    public void reportarPocion(Jugador jugador, Pocion pocion, Pokemon pokemon){
        System.out.printf(" *** -> ");
        System.out.println(jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase());
        this.pocionesUtilizadas+=1;
    }
    
    /**
     * 
     * @param jugador
     * @param pokemon
     * @param oponente
     */
    public void reportarAtaque(Jugador jugador, Pokemon pokemon, Pokemon oponente){
        System.out.printf(" *** -> ");
        System.out.println("El pokemon "+pokemon.apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha atacado a "+oponente.apodo.toUpperCase());   
        this.ataquesRealizados+=1;
    }
    
    /**
     * 
     * @param jugador
     * @param pokemon
     */
    public void reportarBaja(Jugador jugador, Pokemon pokemon){
        System.out.printf(" *** -> ");
        System.out.println("El pokemon "+pokemon.apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate.");
        this.totalBajas+=1;
    }

    /**
     * 
     */
    public void reportarVictoria(Jugador jugador){
        System.out.printf(" *** -> ");
        System.out.println("HA TERMINADO EL COMBATE!");
        System.out.println(jugador.getNombre()+"es el ganador!");
        reportarFinal();
    }
    
    /**
     * 
     */
    public void reportarFinal(){
        System.out.printf(" *** -> ");
        System.out.println("Se realizaron "+this.turnosTotales+" turnos durante todo el combate.");
        System.out.printf(" *** -> ");
        System.out.println("Se utilizaron "+this.pocionesUtilizadas+" pociones y hubo "+this.ataquesRealizados+" ataques.");
        System.out.printf(" *** -> ");
        System.out.println("Se debilitaron "+this.totalBajas+" pokemon en este combate.");
    }
}
