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
        System.out.println("\tEl primer turno en el combate es para "+jugador.getNombre().toUpperCase()+"!\n");
    }

    /**
     * 
     * @param jugador
     */
    public void reportarInicioTurno(Jugador jugador){
        System.out.println("\tEs turno de "+jugador.getNombre().toUpperCase()+"!\n");
        this.turnosTotales+=1;
    }

    public void reportarFinTurno(Jugador jugador){
        System.out.println("\tTermino el turno de "+jugador.getNombre().toUpperCase()+".\n");
    }


    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public void reportarJugadores(Jugador jugador1, Jugador jugador2) {
        System.out.println("\t\tLos jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase()+"\n");
    }
    
    /**
     * 
     * @param jugador
     * @param pocion
     * @param pokemon
     */
    public void reportarPocion(Jugador jugador, Pocion pocion, Pokemon pokemon){
        System.out.println("\t"+jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase()+"\n");
        this.pocionesUtilizadas+=1;
    }
    
    /**
     * 
     * @param jugador
     * @param pokemon
     * @param oponente
     */
    public void reportarAtaque(Jugador jugador, Pokemon pokemon, Pokemon oponente){
        System.out.println("\tEl pokemon "+pokemon.apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha atacado a "+oponente.apodo.toUpperCase()+"\n");   
        this.ataquesRealizados+=1;
    }
    
    public void reportarCambioPeleador(Jugador jugador){
        System.out.println("\tAhora "+jugador.getPokemones().get(jugador.getPeleador()).apodo.toUpperCase()+" es el pokemon con el que estara luchando "+jugador.getNombre().toUpperCase()+"\n");
    }


    /**
     * 
     * @param jugador
     * @param pokemon
     */
    public void reportarBaja(Jugador jugador, Pokemon pokemon){
        System.out.println("\tEl pokemon "+pokemon.apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate!\n");
        this.totalBajas+=1;
    }

    /**
     * 
     */
    public void reportarAbandono(Jugador perdedor){
        System.out.println("\n\t"+perdedor.getNombre().toUpperCase()+" SE HA RENDIDO!\n");
    }    

    /**
     * 
     */
    public void reportarVictoria(Jugador jugador){
        System.out.println("\tHA TERMINADO EL COMBATE!\n");
        System.out.println("\t"+jugador.getNombre().toUpperCase()+" ha ganado!\n");
        reportarFinal();
    }
    
    /**
     * 
     */
    public void reportarFinal(){
        System.out.println("\n\t\t### ESTE ES EL RESUMEN DE LA PELEA ###\n");
        System.out.println("\tSe realizaron "+this.turnosTotales+" turnos durante todo el combate.\n");
        System.out.println("\tSe utilizaron "+this.pocionesUtilizadas+" pociones y hubo "+this.ataquesRealizados+" ataques.\n");
        System.out.println("\tSe debilitaron "+this.totalBajas+" pokemon en este combate.\n\n");
    }
}
