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
    public void inicio() {
        System.out.println("\nINICIO EL COMBATE!");

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
                ejecutarTurno(0);
                /*            ^   
                              |   
                            Representa el jugador que ejecutará alguna acción.
                             0 -> jugador 1
                             1 -> jugador 2
                */
                ejecutarTurno(1); // Ahora le toca al jugador contrario.
                turno = 0; // Indica quién le toca el siguiente turno.
            }else{
                // El turno es para el jugador2
                ejecutarTurno(1);
                ejecutarTurno(0);
                turno = 1;
            }
            
            /*********************************************************************
             * SE VERIFICA CUÁL ES EL SIGUIENTE PASO, SI EL COMBATE CONTINUARÁ O *
             * SI ALGUNO DE LOS DOS ENTRENADORES YA NO PUEDE CONTINUAR.          * 
             *********************************************************************/
            if ( jugador1.estadoEquipo() && jugador2.estadoEquipo() ) {
                System.out.println("El combate continúa!");
            } else if(! jugador1.estadoEquipo()){
                System.out.println("\t"+jugador1.getNombre()+" ya no puede continuar, "+jugador2.getNombre()+" gano el combate!");
                reporte.reportarVictoria(jugador1);
            }else{
                System.out.println("\t"+jugador2.getNombre()+" ya no puede continuar, "+jugador1.getNombre()+" gano el combate!");
                reporte.reportarVictoria(jugador2);
            }

            for ( Pokemon pokemon : this.jugador2.getPokemones() ) {
                pokemon.setEstado(false);
            }
        }
    }

    public void ejecutarTurno(int jugador){
        int opcion = menu.mostrarOpcionesTurno();
        switch (opcion) {
            case 1:
                /* 
                    El jugador a elegido realizar un ataque
                */
                break;
            case 2:
                /*
                    El jugador a elegido utilizar una poción, entonces se mostrarán los pokémon
                    para que pueda elegir alguno de ellos.
                */
                int pocionElegida;
                int pokemonElegido; 
                if (jugador==0) {
                    pocionElegida = this.menu.elegirPocion(this.jugador1);
                    pokemonElegido = this.menu.elegirPokemon(this.jugador1); 
                    this.jugador1.getPociones().get(pocionElegida).usar(this.jugador1.getPokemones().get(pokemonElegido));
                    this.reporte.reportarPocion(this.jugador1, this.jugador1.getPociones().get(pocionElegida), this.jugador1.getPokemones().get(pokemonElegido));
                } else {
                    pocionElegida = this.menu.elegirPocion(this.jugador2);
                    pokemonElegido = this.menu.elegirPokemon(this.jugador2); 
                    this.jugador2.getPociones().get(pocionElegida).usar(this.jugador2.getPokemones().get(pokemonElegido));
                    this.reporte.reportarPocion(this.jugador2, this.jugador2.getPociones().get(pocionElegida), this.jugador2.getPokemones().get(pokemonElegido));
                }
                break;
            case 3:
                /*
                    El jugador a elegido ver sus pociones, así que podrá volver a elegir
                    que hacer (para volverlo a hacer se aplica recursividad).
                */
                break;
            case 4:
                /*
                    El jugador a elegido ver sus pokémon, por lo que después de verlos
                    podrá volver a elegir una acción (se aplicará recursividad).
                */
                break;
            case 5:
                /*
                    El jugador a elegido rendirse.
                */
                break;
            default:
                System.out.println("HA OCURRIDO UN ERROR FATAL!");
                break;
        }
    }
}
