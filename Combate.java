public class Combate {
    Reporte reporte = null;
    Jugador jugador1 = null;
    Jugador jugador2 = null;
    Menu menu = new Menu();

    public Combate(Jugador jugador1, Jugador jugador2){
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.reporte = new Reporte(this.jugador1,this.jugador2);
    }
    public void inicio() throws InterruptedException{
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
        } else {
            this.reporte.reportarTurnoInicial(jugador2);
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
                ejecutarTurno(0);
                this.reporte.reportarFinTurno(this.jugador1);
                /*            ^   
                              |   
                            Representa el jugador que ejecutará alguna acción.
                             0 -> jugador 1
                             1 -> jugador 2
                */
                turno = 1; // Indica quién le toca el siguiente turno.
            }else{
                // El turno es para el jugador2
                this.reporte.reportarInicioTurno(this.jugador2);
                ejecutarTurno(1);
                this.reporte.reportarFinTurno(this.jugador2);
                turno = 0;
            }

            /*********************************************************************
             * SE VERIFICA CUÁL ES EL SIGUIENTE PASO, SI EL COMBATE CONTINUARÁ O *
             * SI ALGUNO DE LOS DOS ENTRENADORES YA NO PUEDE CONTINUAR.          * 
             *********************************************************************/
            if ( jugador1.estadoEquipo() && jugador2.estadoEquipo() ) {
                System.out.println("El combate continúa!");
            } else if(! jugador1.estadoEquipo()){
                System.out.println("\t"+jugador1.getNombre().toUpperCase()+" ya no puede continuar.\n");
                reporte.reportarVictoria(jugador2);
            }else{
                System.out.println("\t"+jugador2.getNombre().toUpperCase()+" ya no puede continuar.\n");
                reporte.reportarVictoria(jugador1);
            }
        }
    }

    public void ejecutarTurno(int jugador) throws InterruptedException{
        if ( jugador == 0 ) {
            this.jugador1.mostrarPociones();   
            this.jugador1.mostrarPokemon();
        } else {
            this.jugador2.mostrarPociones();
            this.jugador2.mostrarPokemon();
        }
        int opcion = menu.mostrarOpcionesTurno();
        switch (opcion) {
            case 1:
                /* 
                    El jugador a elegido realizar un ataque por lo que se seguirán los siguientes pasos:
                    1. Mostrar sus pokémon peleador.
                    2. Atacar al peleador del jugador contrario.
                    3. Reportar el ataque.
                */
                if ( jugador == 0 ) {
                    System.out.println("\tSe realizara un combate entre los siguientes pokemon: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).apodo.toUpperCase(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tVida: %s\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getTipo(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVida(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getAtaque(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getDefensa(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVelocidad(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVelocidad());
                    this.jugador1.getPokemones().get(this.jugador1.getPeleador()).atacar(this.jugador2.getPokemones().get(this.jugador2.getPeleador()));
                    this.reporte.reportarAtaque(this.jugador1, this.jugador1.getPokemones().get(this.jugador1.getPeleador()), this.jugador2.getPokemones().get(this.jugador2.getPeleador()));
                    if (this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVida() <= 0 ){
                        // Si entra aquí significa que el pokémon se ha debilidado.
                        this.jugador2.getPokemones().get(this.jugador2.getPeleador()).setVida(0);
                        this.reporte.reportarBaja(this.jugador2, this.jugador2.getPokemones().get(this.jugador2.getPeleador()));
                    }
                    // Deben volver a mostrarse a los pokémon
                    System.out.println("\tAsi quedaron los pokemones despues del combate: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).apodo.toUpperCase(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tVida: %s\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getTipo(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVida(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getAtaque(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getDefensa(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n\n",this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVelocidad(),this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVelocidad());
                } else {
                    System.out.println("\tSe realizara un combate entre los siguientes pokemon: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).apodo.toUpperCase(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getTipo(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVida(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getAtaque(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getDefensa(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVelocidad(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVelocidad());
                    this.jugador2.getPokemones().get(this.jugador2.getPeleador()).atacar(this.jugador2.getPokemones().get(this.jugador2.getPeleador()));
                    this.reporte.reportarAtaque(this.jugador2, this.jugador2.getPokemones().get(this.jugador2.getPeleador()), this.jugador1.getPokemones().get(this.jugador1.getPeleador()));

                    // Se debe verificar si el pokémon del oponente no quedó debilitado.
                    if (this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVida() <= 0 ){
                        this.jugador1.getPokemones().get(this.jugador1.getPeleador()).setVida(0);
                        this.reporte.reportarBaja(this.jugador1, this.jugador1.getPokemones().get(this.jugador1.getPeleador()));
                    }

                    System.out.println("\tAsi quedaron los pokemones despues del combate: \n");
                    System.out.println("\t  ATACANTE\t\t  DEFENSOR\n");
                    System.out.printf("\t- %s -\t\t- %s -\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).apodo.toUpperCase(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).apodo.toUpperCase());
                    System.out.printf("\tTipo: %s\t\tTipo: %s\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getTipo(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getTipo());
                    System.out.printf("\tVida: %d\t\tVida: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVida(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVida());
                    System.out.printf("\tAtaque: %d\t\tAtaque: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getAtaque(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getAtaque());
                    System.out.printf("\tDefensa: %d\t\tDefensa: %d\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getDefensa(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getDefensa());
                    System.out.printf("\tVelocidad: %d\t\tVelocidad: %d\n\n",this.jugador2.getPokemones().get(this.jugador2.getPeleador()).getVelocidad(),this.jugador1.getPokemones().get(this.jugador1.getPeleador()).getVelocidad());
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
                    // Thread.sleep(1500);
                    System.out.println(" Pocion aplicada con exito! \n");
                    this.reporte.reportarPocion(this.jugador1, this.jugador1.getPociones().get(pocionElegida-1), this.jugador1.getPokemones().get(pokemonElegido-1));
                } else {
                    System.out.println("\n     Comienza eligiendo la pocion que quieres utilizar: \n");
                    pocionElegida = this.menu.elegirPocion(this.jugador2);
                    System.out.println("\n     Ahora deberas elegir en que pokemon utilizar la pocion: \n");
                    pokemonElegido = this.menu.elegirPokemon(this.jugador2);
                    this.jugador2.getPociones().get(pocionElegida-1).usar(this.jugador2.getPokemones().get(pokemonElegido-1));
                    System.out.println("\n Aplicando... \n");
                    // Thread.sleep(1500);
                    System.out.println("\n Pocion aplicada con exito! \n");
                    this.reporte.reportarPocion(this.jugador2, this.jugador2.getPociones().get(pocionElegida-1), this.jugador2.getPokemones().get(pokemonElegido-1));
                }
                break;
            case 3:
                /*  
                    El jugador quiere cambiar de pokémon.                
                */
                int peleador;
                if ( jugador == 0 ) {
                    peleador = this.menu.elegirPeleador(this.jugador1);
                    this.jugador1.setPeleador(peleador);
                    this.reporte.reportarCambioPeleador(this.jugador1);
                } else {
                    peleador = this.menu.elegirPeleador(this.jugador2);
                    this.jugador2.setPeleador(peleador);
                    this.reporte.reportarCambioPeleador(this.jugador2);
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
                } else {
                    for ( Pokemon pokemon : this.jugador2.getPokemones() ) {
                        pokemon.setEstado(false);
                    }
                    this.reporte.reportarAbandono(this.jugador2);
                }
                break;
            default:
                System.out.println("HA OCURRIDO UN ERROR FATAL!");
                break;
        }
    }
}
