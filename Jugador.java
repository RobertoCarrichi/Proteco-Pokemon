import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
    /******************************************
     *         INSTANCIAS DE APOYO            *
     ******************************************/
    Scanner scan = new Scanner(System.in);

    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/
    private String nombre;
    private ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
    private ArrayList<Pocion> pociones = new ArrayList<Pocion>();

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

    /**
     * Este constructor solicitará el llenado de datos y de los pokémon que quiere
     * tener en su equipo, en dado caso que no quiera elegir equipo se elegirán
     * aleatoriamente 6 diferentes.
     * 
     * @param numJugador Indica el numero de jugador que se esta registrando para
     *                   que tengan nombres distintos por defecto.
     */
    public Jugador(int numJugador) throws InterruptedException{
        System.out.println("\nBienvenido al juego 'Jugador" + numJugador + "'!");
        System.out.printf("Antes de todo, elige un mejor nombre:");
        setNombre();
        setPociones();
        setPokemones();
        System.out.println("\t"+this.nombre+" está listo/a para luchar!");
    }

    /************************************************
     *              GETTERS Y SETTERS               *
     ************************************************/

    /**
     * Permite asignar un nombre a un jugador, no requiere de parámetros porque
     * mediante este mismo se hace el proceso de validación y asignación.
     */
    public void setNombre() {
        while(this.nombre == null){
            System.out.printf("\n ~> ");
            this.nombre = scan.nextLine();
            if(this.nombre.isEmpty()){
                System.out.printf("\tOH NO! No puedes dejar este campo vacio, lo siento. Intenta otra vez.");
                this.nombre = null;
            }
        }
        System.out.println("\n\tActualmente tu nombre de entrenador es: "+this.getNombre()+"\n");
        System.out.printf("Quieres cambiarlo? [Si/No] (No): \n ~> ");
        String eleccion = scan.nextLine();
        if ( ! eleccion.isEmpty() && (eleccion.charAt(0) == 'S' || eleccion.charAt(0) == 's')){
            System.out.println("\n\tEntendido! Elige tu nuevo nombre: ");
            // Se asigna un valor nulo a la instanca para que pueda volver a entrar al ciclo que permite elegir un nuevo
            // nombre.
            this.nombre = null;
            setNombre();
        }else{
            System.out.println("\n\tDe acuerdo, entonces sigamos.\n");
        }
    }
    
    /**
     * Permite tener acceso al nombre del entrenador.
     * @return Devuelve el nombre del entrenador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Genera las pociones que debe tener cada jugador por defecto. Por lo que no requiere de parámetros.
     */
    public void setPociones() {
        this.pociones.add(new Pocion("Salud"));
        this.pociones.add(new Pocion("Salud"));
        this.pociones.add(new Pocion("Ataque"));
        this.pociones.add(new Pocion("Ataque"));
        this.pociones.add(new Pocion("Defensa"));
        this.pociones.add(new Pocion("Defensa"));
    }

    /**
     * Permite obtener la lista de pociones que tiene el jugador.
     * @return Retorna una estructura de tipo ArrayList que contiene 6 pociones por defecto, a menos que sean utilizadas.
     */
    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    public void setPokemones() throws InterruptedException{
        Menu menu = new Menu();
        System.out.println(" Cada jugador tiene derecho de tener 6 pokemon en su equipo, quieres elegirlos?");
        System.out.println(" Se elegira aleatoriamente el equipo si escribes 'No' y se tomara esta opcion si dejas la respuesta en blanco. [Si/No] (No)");
        System.out.printf(" \n ~> ");
        String eleccion = scan.nextLine();
        if ( ! eleccion.isEmpty() && (eleccion.charAt(0) == 'S' || eleccion.charAt(0) == 's')){
            menu.eleccionPokemon(this, false);
        }else{
            menu.eleccionPokemon(this, true);
        }

        /* 
            Se mostrará el equipo que tiene el jugador.
FALTA DAR MEJOR FORMATO
        */
        // System.out.println(" \n\tEste es tu equipo: ");
        // mostrarPokemon();
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }

    /************************************************
     *            MÉTODOS DE INSTANCIA              *
     ************************************************/

    /**
     * Muesta en consola el equipo de pokémon que tiene el jugador.
     */
    public void mostrarPokemon() {
        for (Pokemon pokemon : pokemones) {
            pokemon.mostrarInfo();
        }
    }

    /**
     * Muestra las pociones que tiene disponibles para utilizar el jugador.
     */
    public void mostrarPociones() {
        int cantidad = 1;
        for (Pocion pocion : pociones) {
            System.out.println("["+cantidad+"]");
            pocion.info();
            System.out.println();
            cantidad+=1;
        }
    }

    /**
     * Permite añadir pokémon a la lista del jugador externamente.
     */
    public void addPokemon(Pokemon pokemon){
        this.pokemones.add(pokemon);
    }

    /**
     * Verifica el estado de cada uno de los pokémon del equipo del jugador y si alguno de ellos aún tiene un estado
     * "OK" retorna un "true", en caso que todos sus pokemon estén debilidados, se retornará un false;
     * @return Valor booleano que representa si el jugador aún puede seguir en el combate o si no.
     */
    public boolean estadoEquipo() {
        for (Pokemon pokemon : pokemones) {
            if(pokemon.getEstado()){
                return true;
            }
        }
        return false;
    }
}