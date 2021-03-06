import java.io.IOException;

/**
 * Clase que estará encargada de administrar las acciones que realiza cada jugador en su turno y
 * la cuál estará reportando lo que suceda durante el combate a una instancia de tipo REPORTE.
 */
public class Combate {
    /**
     * Instancia que representa globalmente el reporte que estará guargando todo lo que pasa en el 
     * combate.
     */
    Reporte reporte = null;
    
    /**
     * Instancia de tipo JUGADOR que representa al primer jugador registrado.
     */
    Jugador jugador1 = null;
    
    /**
     * Instancia de tipo JUGADOR que representa al segundo jugador registrado.
     */
    Jugador jugador2 = null;
    
    /**
     * Instancia encargada en presentar los menús al jugador en turno y en la solicitud de información
     * desde el teclado.
     */
    Menu menu = new Menu();

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

    /**
     * Permite inicializar a los dos jugadores que estarán interactuando durante el combate.
     * @param jugador1 Representa al primer jugador registrado.
     * @param jugador2 Representa al segundo jugador registrado.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public Combate(Jugador jugador1, Jugador jugador2) throws IOException{
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.reporte = new Reporte(this.jugador1,this.jugador2);
    }
    
    /** 
     * Método que indica el inicio del combate entre los dos jugadores presentados en el constructor.
     * @throws InterruptedException Avisa una posible interrupción la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void inicio() throws InterruptedException, IOException{
        System.out.println("\n\t\t##########################################");
        System.out.println("\t\t#         COMIENZA EL COMBATE            #");
        System.out.println("\t\t##########################################\n");

        /* 
            ELECCIÓN DEL TURNO
            INICIO tendrá un valor entre 0 y 1 el cual representará quién será el jugador que comience.
            0 -> jugador1
            1 -> jugador2
        */
        int turno = (int)(Math.random() * 2);

        if (turno==0) {
            this.reporte.reportarTurnoInicial(jugador1);
            Thread.sleep(1500);
        } else {
            this.reporte.reportarTurnoInicial(jugador2);
            Thread.sleep(1500);
        }

        /* 
         * El combate acaba hasta que alguno de los dos equipos se encuentre debilidado.     
         */
        while ( jugador1.estadoEquipo() && jugador2.estadoEquipo() ){
            /* 
                Cada ciclo se manejará como UN BLOQUE DE DOS TURNOS, uno para cada jugador.
            */
            if(turno==0){
                // El turno es para el jugador1
                this.reporte.reportarInicioTurno(this.jugador1);
                Thread.sleep(1500);
                ejecutarTurno(0);
                /*            ^   
                              |   
                            Representa el jugador que ejecutará alguna acción.
                             0 -> jugador 1
                             1 -> jugador 2
                */
                this.reporte.reportarFinTurno(this.jugador1);
                Thread.sleep(1500);
                turno = 1; // Indica quién le toca el siguiente turno.
            }else{
                // El turno es para el jugador2
                this.reporte.reportarInicioTurno(this.jugador2);
                Thread.sleep(1500);
                ejecutarTurno(1);
                this.reporte.reportarFinTurno(this.jugador2);
                Thread.sleep(1500);
                turno = 0;
            }

            /*********************************************************************
             * SE VERIFICA CUÁL ES EL SIGUIENTE PASO, SI EL COMBATE CONTINUARÁ O *
             * SI ALGUNO DE LOS DOS ENTRENADORES YA NO PUEDE CONTINUAR.          * 
             *********************************************************************/
            if ( jugador1.estadoEquipo() && jugador2.estadoEquipo() ) {
                // El combate continúa
                System.out.println("\n\tComenzara el siguiente turno en breve...\n");
                Thread.sleep(4000);
            } else if(! jugador1.estadoEquipo()){
                System.out.println("\t"+jugador1.getNombre().toUpperCase()+" ya no puede continuar.\n");
                Thread.sleep(2000);
                reporte.reportarVictoria(jugador2);
            }else{
                System.out.println("\t"+jugador2.getNombre().toUpperCase()+" ya no puede continuar.\n");
                Thread.sleep(2000);
                reporte.reportarVictoria(jugador1);
                Thread.sleep(2000);
            }
        }
    }
    
    
    /** 
     * Muestra las opciones que tiene un jugador para realizar en un turno individual.
     * @param jugador Requiere de un valor de tipo entero que exprese que jugador está utilizando
     * su turno.
     * @throws InterruptedException Avisa una posible interrupción la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void ejecutarTurno(int jugador) throws InterruptedException, IOException{
        if ( jugador == 0 ) {
            this.jugador1.mostrarPociones();   
            Thread.sleep(2000);
            this.jugador1.mostrarPokemon();
            Thread.sleep(2000);
        } else {
            this.jugador2.mostrarPociones();
            Thread.sleep(2000);
            this.jugador2.mostrarPokemon();
            Thread.sleep(2000);
        }
        int opcion = menu.mostrarOpcionesTurno();
        ejecutarEleccion(jugador, opcion);
        Thread.sleep(2000);
    }
    
    
    /** 
     * Realiza la ejecución de la elección realizada por el usuario en una ejecución previa del 
     * método ejecutarTurno().
     * @param jugador Requiere de una referencia que indique que jugador está en turno.
     * @param opcion Representa la opción que eligió previamente en el método ejecutarTurno().
     * @throws InterruptedException Avisa una posible interrupción la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
     * @throws IOException Avisa una posible interrupción la cual se requiere para poder crear
     * el reporte que contenta el historial del combate.
     */
    public void ejecutarEleccion(int jugador, int opcion) throws InterruptedException, IOException {
        switch (opcion) {
            case 1:
                /* 
                    El jugador a elegido realizar un ataque por lo que se seguirán los siguientes pasos:
                    1. Mostrar sus pokémon peleador.
                    2. Atacar al peleador del jugador contrario.
                    3. Reportar el ataque.
                */
                int ataqueElegido;
                int danioAplicado;
                if ( jugador == 0 ) {
                    System.out.println("\tSe realizara un combate entre los siguientes pokemon: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador1.getPeleador().apodo.toUpperCase(),this.jugador2.getPeleador().apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador1.getPeleador().getTipo(),this.jugador2.getPeleador().getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador1.getPeleador().getVida(),this.jugador2.getPeleador().getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador1.getPeleador().getAtaque(),this.jugador2.getPeleador().getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador1.getPeleador().getDefensa(),this.jugador2.getPeleador().getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n",this.jugador1.getPeleador().getVelocidad(),this.jugador2.getPeleador().getVelocidad());
                    System.out.printf("\tAtaques: %s\tAtaques: %s\n",this.jugador1.getPeleador().getMovimiento(0),this.jugador2.getPeleador().getMovimiento(0));
                    System.out.printf("\t         %s\t         %s\n",this.jugador1.getPeleador().getMovimiento(1),this.jugador2.getPeleador().getMovimiento(1));
                    System.out.printf("\tEstado: %s\t\tEstado: %s\n\n",this.jugador1.getPeleador().getEstado(),this.jugador2.getPeleador().getEstado());
                    
                    // Después de mostrar a los pokémon, debe elegir el jugador que ataque utilizar
                    ataqueElegido = menu.elegirAtaque(this.jugador1);

                    if (this.jugador1.getPeleador().getVelocidad() < this.jugador2.getPeleador().getVelocidad() ){
                        // El peleador del jugador 1 es más lento que el del jugador 2.
                        // Como es más lento, se informa que se el peleador del jugador 2 atacará.
                        // Se ejecuta el ataque
                        this.reporte.reportarImprevisto(this.jugador1.getPeleador(), this.jugador2.getPeleador());
                        // Si le ganó el turno, solo puede ejecutar el ataque "BÁSICO"
                        danioAplicado = this.jugador2.getPeleador().atacar(this.jugador1,0);
                        System.out.println("\tAtacando...\n");
                        Thread.sleep(1500);
                            // Se reporta el ataque realizado
                        this.reporte.reportarAtaque(this.jugador2, this.jugador1, 0, danioAplicado);
                    } else {
                        // Se ejecuta el ataque sin imprevistos
                        danioAplicado = this.jugador1.getPeleador().atacar(this.jugador2,ataqueElegido);
                        System.out.println("\tAtacando...\n");
                        Thread.sleep(1500);
                        // Se reporta el ataque realizado
                        this.reporte.reportarAtaque(this.jugador1, this.jugador2, ataqueElegido, danioAplicado);
                        Thread.sleep(2000);
                    }
    
                    // Deben volver a mostrarse a los pokémon
                    System.out.println("\tAsi quedaron los pokemones despues del combate: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador1.getPeleador().apodo.toUpperCase(),this.jugador2.getPeleador().apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador1.getPeleador().getTipo(),this.jugador2.getPeleador().getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador1.getPeleador().getVida(),this.jugador2.getPeleador().getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador1.getPeleador().getAtaque(),this.jugador2.getPeleador().getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador1.getPeleador().getDefensa(),this.jugador2.getPeleador().getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n",this.jugador1.getPeleador().getVelocidad(),this.jugador2.getPeleador().getVelocidad());
                    System.out.printf("\tAtaques: %s\tAtaques: %s\n",this.jugador1.getPeleador().getMovimiento(0),this.jugador2.getPeleador().getMovimiento(0));
                    System.out.printf("\t         %s\t         %s\n",this.jugador1.getPeleador().getMovimiento(1),this.jugador2.getPeleador().getMovimiento(1));
                    System.out.printf("\tEstado: %s\t\tEstado: %s\n\n",this.jugador1.getPeleador().getEstado(),this.jugador2.getPeleador().getEstado());
                    
                    if ( ! this.jugador2.getPeleador().getEstado() ){
                        // Si entra aquí significa que el pokémon se ha debilidado.

                        // Se reporta la baja
                        this.reporte.reportarBaja(this.jugador2);
                        Thread.sleep(2000);
                        // Deberá elegir un nuevo pokemon para pelear
                        ejecutarEleccion(1, 3);
                    }
                } else {
                    System.out.println("\tSe realizara un combate entre los siguientes pokemon: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador2.getPeleador().apodo.toUpperCase(),this.jugador1.getPeleador().apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador2.getPeleador().getTipo(),this.jugador1.getPeleador().getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador2.getPeleador().getVida(),this.jugador1.getPeleador().getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador2.getPeleador().getAtaque(),this.jugador1.getPeleador().getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador2.getPeleador().getDefensa(),this.jugador1.getPeleador().getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n",this.jugador2.getPeleador().getVelocidad(),this.jugador1.getPeleador().getVelocidad());
                    System.out.printf("\tAtaques: %s\tAtaques: %s\n",this.jugador2.getPeleador().getMovimiento(0),this.jugador1.getPeleador().getMovimiento(0));
                    System.out.printf("\t         %s\t         %s\n",this.jugador2.getPeleador().getMovimiento(1),this.jugador1.getPeleador().getMovimiento(1));
                    System.out.printf("\tEstado: %s\t\tEstado: %s\n\n",this.jugador2.getPeleador().getEstado(),this.jugador1.getPeleador().getEstado());
                    
                    // Después de mostrar a los pokémon, debe elegir el jugador que ataque utilizar
                    ataqueElegido = menu.elegirAtaque(this.jugador2);
                    
                    if (this.jugador2.getPeleador().getVelocidad() < this.jugador1.getPeleador().getVelocidad() ){
                        // El peleador del jugador 1 es más lento que el del jugador 2.
                        // Como es más lento, se informa que se el peleador del jugador 2 atacará.
                        // Se ejecuta el ataque
                        this.reporte.reportarImprevisto(this.jugador2.getPeleador(), this.jugador1.getPeleador());
                        // Si le ganó el turno, solo puede ejecutar el ataque "BÁSICO"
                        danioAplicado = this.jugador1.getPeleador().atacar(this.jugador2,0);
                        System.out.println("\tAtacando...\n");
                        Thread.sleep(1500);
                            // Se reporta el ataque realizado
                        this.reporte.reportarAtaque(this.jugador1, this.jugador2, 0, danioAplicado);
                        Thread.sleep(2000);
                    } else {
                        // Se ejecuta el ataque sin imprevistos
                        danioAplicado = this.jugador2.getPeleador().atacar(this.jugador1,ataqueElegido);
                        System.out.println("\tAtacando...\n");
                        Thread.sleep(1500);
                        // Se reporta el ataque realizado
                        this.reporte.reportarAtaque(this.jugador2, this.jugador1, ataqueElegido, danioAplicado);
                        Thread.sleep(2000);
                    }
                    
                    System.out.println("\tAsi quedaron los pokemones despues del combate: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador2.getPeleador().apodo.toUpperCase(),this.jugador1.getPeleador().apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador2.getPeleador().getTipo(),this.jugador1.getPeleador().getTipo());
                    System.out.printf("\tVida: %3d\t\tVida: %3d\n",this.jugador2.getPeleador().getVida(),this.jugador1.getPeleador().getVida());
                    System.out.printf("\tAtaque: %3d\t\tAtaque: %3d\n",this.jugador2.getPeleador().getAtaque(),this.jugador1.getPeleador().getAtaque());
                    System.out.printf("\tDefensa: %3d\t\tDefensa: %3d\n",this.jugador2.getPeleador().getDefensa(),this.jugador1.getPeleador().getDefensa());
                    System.out.printf("\tVelocidad: %3d\t\tVelocidad: %3d\n",this.jugador2.getPeleador().getVelocidad(),this.jugador1.getPeleador().getVelocidad());
                    System.out.printf("\tAtaques: %s\tAtaques: %s\n",this.jugador2.getPeleador().getMovimiento(0),this.jugador1.getPeleador().getMovimiento(0));
                    System.out.printf("\t         %s\t         %s\n",this.jugador2.getPeleador().getMovimiento(1),this.jugador1.getPeleador().getMovimiento(1));
                    System.out.printf("\tEstado: %s\t\tEstado: %s\n\n",this.jugador2.getPeleador().getEstado(),this.jugador1.getPeleador().getEstado());

                    // Se debe verificar si el pokémon del oponente no quedó debilitado.
                    if ( ! this.jugador1.getPeleador().getEstado() ){
                        // Si entra aquí significa que el pokémon se ha debilidado.
                        // Se reporta la baja
                        this.reporte.reportarBaja(this.jugador2);
                        Thread.sleep(2000);
                        // Deberá elegir un nuevo pokemon para pelear
                        ejecutarEleccion(0, 3);
                    }
                }
                break;
            case 2:
                /*
                    El jugador a elegido utilizar una poción, entonces se mostrarán los pokémon
                    para que pueda elegir alguno de ellos.
                */
                int pocionElegida;
                int pokemonElegido; 
                if ( jugador == 0 ) {
                    System.out.println("\n     Comienza eligiendo la pocion que quieres utilizar: \n");
                    pocionElegida = this.menu.elegirPocion(this.jugador1);
                    System.out.println("\n     Ahora deberas elegir en que pokemon utilizar la pocion: \n");
                    pokemonElegido = this.menu.elegirPokemon(this.jugador1); 
                    this.jugador1.getPociones().get(pocionElegida-1).usar(this.jugador1.getPokemones().get(pokemonElegido-1));
                    System.out.println("\n Aplicando... \n");
                    Thread.sleep(1500);
                    System.out.println(" Pocion aplicada con exito! \n");
                    this.reporte.reportarPocion(this.jugador1, this.jugador1.getPociones().get(pocionElegida-1), this.jugador1.getPokemones().get(pokemonElegido-1));
                    Thread.sleep(2000);
                } else {
                    System.out.println("\n     Comienza eligiendo la pocion que quieres utilizar: \n");
                    pocionElegida = this.menu.elegirPocion(this.jugador2);
                    System.out.println("\n     Ahora deberas elegir en que pokemon utilizar la pocion: \n");
                    pokemonElegido = this.menu.elegirPokemon(this.jugador2);
                    this.jugador2.getPociones().get(pocionElegida-1).usar(this.jugador2.getPokemones().get(pokemonElegido-1));
                    System.out.println("\n Aplicando... \n");
                    Thread.sleep(1500);
                    this.reporte.reportarPocion(this.jugador2, this.jugador2.getPociones().get(pocionElegida-1), this.jugador2.getPokemones().get(pokemonElegido-1));
                    Thread.sleep(2000);
                }
                break;
            case 3:
                /*  
                    El jugador quiere cambiar de pokémon.                
                */
                int peleador;
                if ( jugador == 0 ) {
                    peleador = this.menu.elegirPeleador(this.jugador1);
                    if (peleador != -1) {
                        this.jugador1.setPeleador(peleador);
                        System.out.println("\tCambiando...\n");
                        Thread.sleep(1500);
                        this.reporte.reportarCambioPeleador(this.jugador1);
                        Thread.sleep(1500);
                    }
                } else {
                    peleador = this.menu.elegirPeleador(this.jugador2);
                    if (peleador != -1) {
                        System.out.println("\tCambiando...\n");
                        Thread.sleep(1500);
                        this.jugador2.setPeleador(peleador);
                        this.reporte.reportarCambioPeleador(this.jugador2);
                        Thread.sleep(1500);
                    }
                }
                break;
                case 4:
                /*
                El jugador a elegido rendirse.
                */
                if ( jugador == 0 ) {
                    for ( Pokemon pokemon : this.jugador1.getPokemones() ) {
                        pokemon.setEstado(false);
                    }
                    this.reporte.reportarAbandono(this.jugador1);
                    Thread.sleep(2000);
                } else {
                    for ( Pokemon pokemon : this.jugador2.getPokemones() ) {
                        pokemon.setEstado(false);
                    }
                    this.reporte.reportarAbandono(this.jugador2);
                    Thread.sleep(2000);
                }
                break;
            default:
                System.out.println("HA OCURRIDO UN ERROR FATAL!");
                break;
        }

    }
}