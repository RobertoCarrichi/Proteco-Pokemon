import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    /**
     * 
     */
    int totalCambios;

    /**
     * 
     */
    String nombreArchivo;

    /**
     * 
     */
    File reporteFinal = null;

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/
    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public Reporte(Jugador jugador1, Jugador jugador2) throws IOException{
        this.turnosTotales = 0;
        this.nombreArchivo = "CombatePokemon_"+jugador1.getNombre().toUpperCase()+"_"+jugador2.getNombre().toUpperCase()+".txt";
        this.reporteFinal = new File(this.nombreArchivo);
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
    public void reportarTurnoInicial(Jugador jugador) throws IOException, FileNotFoundException {
        System.out.println("\tEl primer turno en el combate es para "+jugador.getNombre().toUpperCase()+"!\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEl primer turno en el combate es para "+jugador.getNombre().toUpperCase()+"!\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador
     */
    public void reportarInicioTurno(Jugador jugador) throws IOException{
        System.out.println("\tEs turno de "+jugador.getNombre().toUpperCase()+"!\n");
        this.turnosTotales+=1;
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEs turno de "+jugador.getNombre().toUpperCase()+"!\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador
     * @throws IOException
     */
    public void reportarFinTurno(Jugador jugador) throws IOException{
        System.out.println("\tTermino el turno de "+jugador.getNombre().toUpperCase()+".");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tTermino el turno de "+jugador.getNombre().toUpperCase()+".\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public void reportarJugadores(Jugador jugador1, Jugador jugador2) throws IOException {
        System.out.println("\t#### Los jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase()+" ####\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("######################################################################################################\n");
        reportar.write("#\n#\t#### Los jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase()+" ####\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador
     * @param pocion
     * @param pokemon
     */
    public void reportarPocion(Jugador jugador, Pocion pocion, Pokemon pokemon) throws IOException{
        System.out.println("\t"+jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase()+"\n");
        this.pocionesUtilizadas+=1;

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\t"+jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase()+"\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador1
     * @param jugador2
     */
    public void reportarAtaque(Jugador jugador1, Jugador jugador2, int ataque, int danio) throws IOException {
        FileWriter reportar = new FileWriter(reporteFinal, true);
        if ( danio > 0 ) {
            System.out.println("\tEl pokemon "+jugador1.getPeleador().apodo.toUpperCase()+" de "+jugador1.getNombre().toUpperCase()+" ha atacado con "+jugador1.getPeleador().getMovimiento(ataque).toUpperCase()+" y ha hecho "+danio+" de danio a "+jugador2.getPeleador().apodo.toUpperCase()+"\n");   
            reportar.write("#\tEl pokemon "+jugador1.getPeleador().apodo.toUpperCase()+" de "+jugador1.getNombre().toUpperCase()+" ha atacado con "+jugador1.getPeleador().getMovimiento(ataque).toUpperCase()+" y ha hecho "+danio+" de danio a "+jugador2.getPeleador().apodo.toUpperCase()+"\n#\n");   
        } else {
            System.out.println("\tEl pokemon "+jugador1.getPeleador().apodo.toUpperCase()+" de "+jugador1.getNombre().toUpperCase()+" ha atacado con "+jugador1.getPeleador().getMovimiento(ataque).toUpperCase()+" y no le ha hecho danio a "+jugador2.getPeleador().apodo.toUpperCase()+"\n");   
            reportar.write("#\tEl pokemon "+jugador1.getPeleador().apodo.toUpperCase()+" de "+jugador1.getNombre().toUpperCase()+" ha atacado con "+jugador1.getPeleador().getMovimiento(ataque).toUpperCase()+" y no le ha hecho danio a "+jugador2.getPeleador().apodo.toUpperCase()+"\n#\n");   
        }
        this.ataquesRealizados+=1;
        reportar.close();
    }
    
    /**
     * 
     * @param atacante
     * @param defensor
     */
    public void reportarImprevisto(Pokemon atacante, Pokemon defensor) throws IOException{
        System.out.println("\tOH NO! "+defensor.apodo.toUpperCase()+" es más veloz que "+atacante.apodo.toUpperCase()+", le quito el turno y ataco antes!\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tOH NO! "+defensor.apodo.toUpperCase()+" es más veloz que "+atacante.apodo.toUpperCase()+", le quito el turno y ataco antes!\n#\n");
        reportar.close();
    }


    /**
     * 
     * @param jugador
     * @throws IOException
     */
    public void reportarCambioPeleador(Jugador jugador) throws IOException {
        System.out.println("\tAhora "+jugador.getPeleador().apodo.toUpperCase()+" es el pokemon con el que estara luchando "+jugador.getNombre().toUpperCase()+"\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tAhora "+jugador.getPeleador().apodo.toUpperCase()+" es el pokemon con el que estara luchando "+jugador.getNombre().toUpperCase()+"\n#\n");
        reportar.close();
    }
    
    /**
     * 
     * @param jugador
     * @throws IOException
     */
    public void reportarBaja(Jugador jugador) throws IOException{
        System.out.println("\tEl pokemon "+jugador.getPeleador().apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate!\n");
        this.totalBajas+=1;

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEl pokemon "+jugador.getPeleador().apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate!\n#\n");
        reportar.close();        
    }
    
    /**
     * 
     * @param perdedor
     * @throws IOException
     */
    public void reportarAbandono(Jugador perdedor) throws IOException {
        System.out.println("\n\t"+perdedor.getNombre().toUpperCase()+" SE HA RENDIDO!\n");

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\n#\t"+perdedor.getNombre().toUpperCase()+" SE HA RENDIDO!\n#\n");
        reportar.close();
    }    
    
    /**
     * 
     * @param jugador
     * @throws IOException
     */
    public void reportarVictoria(Jugador jugador) throws IOException {
        System.out.println("\tHA TERMINADO EL COMBATE!\n");
        System.out.println("\t"+jugador.getNombre().toUpperCase()+" ha ganado!\n");

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tHA TERMINADO EL COMBATE!\n#\n");
        reportar.write("#\t"+jugador.getNombre().toUpperCase()+" ha ganado!\n#\n");
        reportar.close();        
        
        reportarFinal();
    }
    
    /**
     * 
     * @throws IOException
     */
    public void reportarFinal() throws IOException  {
        System.out.println("\n\t\t### ESTE ES EL RESUMEN DE LA PELEA ###\n");
        System.out.println("\tSe realizaron "+this.turnosTotales+" turnos durante todo el combate.\n");
        System.out.println("\tSe realizaron "+this.totalCambios+" cambios.\n");
        System.out.println("\tSe utilizaron "+this.pocionesUtilizadas+" pociones y hubo "+this.ataquesRealizados+" ataques.\n");
        System.out.println("\tSe debilitaron "+this.totalBajas+" pokemon.\n\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\n#\t\t### ESTE ES EL RESUMEN DE LA PELEA ###\n#\n");
        reportar.write("#\tSe realizaron "+this.turnosTotales+" turnos durante todo el combate.\n");
        reportar.write("#\tSe realizaron "+this.totalCambios+" cambios.\n");
        reportar.write("#\tSe utilizaron "+this.pocionesUtilizadas+" pociones y hubo "+this.ataquesRealizados+" ataques.\n");
        reportar.write("#\tSe debilitaron "+this.totalBajas+" pokemon.\n#\n");
        reportar.close();
    }
}
