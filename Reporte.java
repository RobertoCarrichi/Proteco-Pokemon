import java.io.FileWriter;
import java.io.File;
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
     * Indica todos los turnos que hubo durante todo el combate.
     */
    int turnosTotales;

    /**
     * Indica todos los ataques que fueron realizados globalmente.
     */
    int ataquesRealizados;

    /**
     * Representa la cantidad total de pociones cuando de los dos jugadores decidió utilizar una poción.
     */
    int pocionesUtilizadas;

    /**
     * Hace referencia a la cantidad de pokémon debilitados durante el combate.
     */
    int totalBajas;

    /**
     * Indica la cantidad de cambios de pokémon peleador que ocurrieron durante todo el combate.
     */
    int totalCambios;

    /**
     * Hace referencia al nombre que tiene el archivo donde se estará guardando todo lo que sucede
     * en el combate.
     */
    String nombreArchivo;

    /**
     * Una instancia de tipo FILE que representará a el archivo como tal y en el cual se estarán
     * añadiendo todos los reportes.
     */
    File reporteFinal = null;

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

    /**
     * Constructor dedicado a inicializar el nombre del archivo y para reportar a los dos jugadores
     * que estarán combatiendo.
     * @param jugador1 Parámetro de tipo JUGADOR del cual se requerirá su nombre para el archivo de
     * reportes.
     * @param jugador2 Se trata del segundo jugador registrado al inicio del pograma.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
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
     * Reporta a los jugadores que presentarán el combate.
     * @param jugador1 Hará referecia al primer jugador registrado.
     * @param jugador2 Hará referencia al segundo jugador registrado el comienzo del pograma.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate. 
     */
    public void reportarJugadores(Jugador jugador1, Jugador jugador2) throws IOException {
        System.out.println("\t#### Los jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase()+" ####\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("######################################################################################################\n");
        reportar.write("#\n#\t#### Los jugadores que van a luchar son: "+jugador1.getNombre().toUpperCase()+" y "+jugador2.getNombre().toUpperCase()+" ####\n#\n");
        reportar.close();
    }


    /**
     * Reporta el jugador que tuvo la suerte de tener el primer turno en todo el combate.
     * @param jugador Parámetro de tipo JUGADOR que representa a el jugador que ganó el primer
     * turno en el combate.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarTurnoInicial(Jugador jugador) throws IOException {
        System.out.println("\tEl primer turno en el combate es para "+jugador.getNombre().toUpperCase()+"!\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEl primer turno en el combate es para "+jugador.getNombre().toUpperCase()+"!\n#\n");
        reportar.close();
    }
    
    /**
     * Reporta al jugador del cuál comenzará su turno.
     * @param jugador Se debe indicar al jugador que iniciará su turno.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarInicioTurno(Jugador jugador) throws IOException{
        System.out.println("\tEs turno de "+jugador.getNombre().toUpperCase()+"!\n");
        this.turnosTotales+=1;
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEs turno de "+jugador.getNombre().toUpperCase()+"!\n#\n");
        reportar.close();
    }
    
    /**
     * Reporta al jugador del cuál terminará su turno.
     * @param jugador Se debe indicar al jugador que terminará su turno.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarFinTurno(Jugador jugador) throws IOException{
        System.out.println("\tTermino el turno de "+jugador.getNombre().toUpperCase()+".");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tTermino el turno de "+jugador.getNombre().toUpperCase()+".\n#\n");
        reportar.close();
    }
    
    /**
     * Método que reporta que una poción ha sido utilizada, hará que se sume en una unidad al total de
     * pociones utilizadas durante el combate.
     * @param jugador Se debe indicar el jugador que está tratando de utilizar la poción.
     * @param pocion Referencia a la poción que se utilizará.
     * @param pokemon Referencia al pokémon que recibirá el beneficio de la poción.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarPocion(Jugador jugador, Pocion pocion, Pokemon pokemon) throws IOException{
        System.out.println("\t"+jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase()+"\n");
        this.pocionesUtilizadas+=1;

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\t"+jugador.getNombre().toUpperCase()+" ha utilizado una pocion de "+pocion.getTipo().toUpperCase()+" en "+pokemon.apodo.toUpperCase()+"\n#\n");
        reportar.close();
    }
    
    /**
     * Método que reporta que se presenció un ataque entre los jugadores.
     * @param jugador1 Referencia al jugador que será "atacante".
     * @param jugador2 Referencia al jugador que será "defensor".
     * @param ataque Referencia a el ataque que el jugador eligió antes de ejecutar el ataque.
     * @param danio Valor que indica la cantidad de daño que va a recibir el pokémon.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
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
     * Reporta que se precensió un imprevisto, ya que el pokémon defensor resultó ser más veloz
     * y le ganó el turno al pokémon atacante.
     * @param atacante Instancia de tipo POKÉMON que representa al pokémon que resultará dañado 
     * por ser más lento que el defensor.
     * @param defensor Referencia que representa al pokémon que será el que realiza el ataque.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarImprevisto(Pokemon atacante, Pokemon defensor) throws IOException{
        System.out.println("\tOH NO! "+defensor.apodo.toUpperCase()+" es más veloz que "+atacante.apodo.toUpperCase()+", le quito el turno y ataco antes!\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tOH NO! "+defensor.apodo.toUpperCase()+" es más veloz que "+atacante.apodo.toUpperCase()+", le quito el turno y ataco antes!\n#\n");
        reportar.close();
    }


    /**
     * Reporta en pantalla y en el documento que hubo un cambio de pokémon peleador.
     * @param jugador Debe inidicarse al jugador que hizo el cambio.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarCambioPeleador(Jugador jugador) throws IOException {
        System.out.println("\tAhora "+jugador.getPeleador().apodo.toUpperCase()+" es el pokemon con el que estara luchando "+jugador.getNombre().toUpperCase()+"\n");
        
        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tAhora "+jugador.getPeleador().apodo.toUpperCase()+" es el pokemon con el que estara luchando "+jugador.getNombre().toUpperCase()+"\n#\n");
        reportar.close();
    }
    
    /**
     * Reporta que un pokemón quedó debilitado después de un ataque durante el combate.
     * @param jugador Representa al jugador que perdió a su pokémon peleador.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarBaja(Jugador jugador) throws IOException{
        System.out.println("\tEl pokemon "+jugador.getPeleador().apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate!\n");
        this.totalBajas+=1;

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\tEl pokemon "+jugador.getPeleador().apodo.toUpperCase()+" de "+jugador.getNombre().toUpperCase()+" ha quedado fuera de combate!\n#\n");
        reportar.close();        
    }
    
    /**
     * Reporta el ABANDONO de un jugador de la partida!
     * @param perdedor Se requiere una instancia de tipo JUGADOR que represente al jugador que quiso
     * returarse.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void reportarAbandono(Jugador perdedor) throws IOException {
        System.out.println("\n\t"+perdedor.getNombre().toUpperCase()+" SE HA RENDIDO!\n");

        FileWriter reportar = new FileWriter(reporteFinal, true);
        reportar.write("#\n#\t"+perdedor.getNombre().toUpperCase()+" SE HA RENDIDO!\n#\n");
        reportar.close();
    }    
    
    /**
     * Reporta que un jugador a salido vistorioso del combate.
     * @param jugador Se utiliza para saber qué jugador es el que ha ganado el
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
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
     * Se trata del último reporte que se añade al combate, pues esta será el que indique los
     * resultados globales durante todo el combate (en pantalla y en el documento).
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
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
        reportar.write("######################################################################################################\n");
        reportar.close();
    }
}