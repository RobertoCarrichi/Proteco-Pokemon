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
    public Jugador(int numJugador) {
        System.out.println("\nBienvenido al juego 'Jugador" + numJugador + "'!");
        System.out.printf("Antes de todo, elige un mejor nombre:");
        setNombre();
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
        this.pociones.add(new Pocion("Vida"));
    }

    /**
     * 
     * @return
     */
    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    /************************************************
     *            MÉTODOS DE INSTANCIA              *
     ************************************************/

    /**
     * Mostrar en consola el equipo de pokémon que tiene el jugador.
     */
    public void mostrarPokemon() {
        for (Pokemon pokemon : pokemones) {
            pokemon.mostrarInfo();
        }
    }
}