import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa la estructura básica de un jugador (llamado en el jugo como un "entrenador").
 * Esta indica los atributos que tiene y los métodos que puede utilizar.
 */
public class Jugador {
    /******************************************
     *         INSTANCIAS DE APOYO            *
     ******************************************/
    /**
     * Una instancia de apoyo para que el usuario pueda elegir opciones al momento de la construcción
     * de algún jugador.
     */
    Scanner scan = new Scanner(System.in);

    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/

    /**
     * Cadena de caracteres que expresa el nombre que el usuario asignó durante la "construcción" de 
     * un jugador. Este será el que se exprese en múltiples ocasiones durante la creación del reporte
     * del combate y estará en el nombre del archivo para que el nombre no se repita con otro archivo.
     */
    private String nombre;
    
    /**
     * Lista que almacena los pokémon de equipo que tendrá el jugador durante el combate.
     */
    private ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
    
    /**
     * Lista de pociones que almacena elementos de tipo POCION y serán estos elementos de los que podrá
     * hacer uso el jugador para ayudar a sus pokémon durante el combate.
     */
    private ArrayList<Pocion> pociones = new ArrayList<Pocion>();
    
    /**
     * Valor entero que hará referencia al índice al que será nombrado como "peleador" del entrenador
     * hasta que decida cambiarlo.
     */
    private int peleador;

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

    /**
     * Este constructor solicitará el llenado de datos y de los pokémon que quiere
     * tener en su equipo, en dado caso que no quiera elegir equipo se elegirán
     * aleatoriamente 6 diferentes.
     * 
     * @param numJugador Indica el numero de jugador que se esta registrando para
     * que tengan nombres distintos por defecto.
     * @throws InterruptedException Avisa una posible interrupción la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
     */
    public Jugador(int numJugador) throws InterruptedException{
        System.out.println("Bienvenido al juego 'Jugador" + numJugador + "'!");
        System.out.printf("Antes de todo, elige un mejor nombre:");
        setNombre();
        setPociones();
        mostrarPociones();
        setPokemones();
        mostrarPokemon();
        setPeleador();
        System.out.println("\tGuardando datos...\n");
        Thread.sleep(4000);
        System.out.println("\t"+this.getNombre().toUpperCase()+" está listo/a para luchar!\n");
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
        System.out.println("\n\tActualmente tu nombre de entrenador es: "+this.getNombre().toUpperCase()+"\n");
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

    /**
     * Método llamado durante la construcción de un jugador el cual solicitará una opción
     * para saber si los pokémon serán elegidos mediante la elección del jugador o aleatoriamente.
     * @throws InterruptedException Avisa una posible interrupción la cual se requiere para poder
     * crear pausar y poder hacer más amigable la interacción con el usuario.
     */
    public void setPokemones() throws InterruptedException{
        Menu menu = new Menu();
        System.out.println(" Cada jugador tiene derecho de tener 6 pokemon en su equipo, quieres elegirlos?");
        System.out.printf(" Se elegira aleatoriamente el equipo si escribes 'No' y se tomara esta opcion si dejas la respuesta en blanco. [Si/No] (No)\n ~> ");
        String eleccion = scan.nextLine();
        if ( ! eleccion.isEmpty() && (eleccion.charAt(0) == 'S' || eleccion.charAt(0) == 's')){
            menu.eleccionPokemon(this, false);
        }else{
            menu.eleccionPokemon(this, true);
        }
    }

    /**
     * Permite tener acceso a la lista de pokémon del jugador.
     * @return Retorna una lista de instancias de tipo POKEMON, las cuales representan el equipo
     * que tiene el jugador para combatir.
     */
    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }

    /**
     * Hace posible poder elegir un índice que represente al pokémon peleador del jugador que llame
     * a este método.
     */
    public void setPeleador() {
        System.out.printf("Con que pokemon quieres comenzar el combate? (Elige con el numero)");
        System.out.printf("\n ~> ");
        int eleccion = scan.nextInt();
        if (eleccion == 1 || eleccion == 2 || eleccion == 3 || eleccion == 4 || eleccion == 5 || eleccion == 6) {
            System.out.println("\n\tSe ha asignado a "+pokemones.get(eleccion-1).apodo.toUpperCase()+" como el pokemon con el que luchara "+this.nombre.toUpperCase()+"\n");
            this.peleador = eleccion-1;
        } else {
            System.out.println("\n\tOH NO! Has tenido un error al elegir un pokemon para pelear. Intenta otra vez.\n");
            setPeleador();
        }
    }

    /**
     * Permite asignar un índice que indique al pokémon peleador del jugador.
     * @param peleador Valor de tipo entero que tomará el valor del índice que represente al pokémon 
     * peleador de un jugador.
     */
    public void setPeleador(int peleador){
        this.peleador = peleador;
    }

    /**
     * Hace posible tener acceso al pokémon peleador del jugador que llama al método.
     * @return Retorna una instancia de tipo POKEMON que representa al peleador del entrenador.
     */
    public Pokemon getPeleador(){
        return this.pokemones.get(this.peleador);
    }

    /************************************************
     *            MÉTODOS DE INSTANCIA              *
     ************************************************/

    /**
     * Muesta en consola el equipo de pokémon que tiene el jugador.
     */
    public void mostrarPokemon() {
        System.out.println("\t\t##### ESTE ES TU EQUIPO DE POKEMON #####\n");
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("[%d]\t\t\t",i+1);
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("- %s -\t\t",pokemones.get(i).apodo.toUpperCase());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Tipo: %s\t\t",pokemones.get(i).getTipo());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Vida: %3d\t\t",pokemones.get(i).getVida());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Ataque: %d\t\t",pokemones.get(i).getAtaque());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Defensa: %d\t\t",pokemones.get(i).getDefensa());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Velocidad: %d\t\t",pokemones.get(i).getVelocidad());
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("Ataques: %s\t",pokemones.get(i).getMovimiento(0));
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            System.out.printf("         %s\t",pokemones.get(i).getMovimiento(1));
        }
        System.out.println();
        for (int i = 0; i < pokemones.size(); i++) {
            if (pokemones.get(i).getEstado()) {
                System.out.printf("Estado: OK\t\t");
            } else {
                System.out.printf("Estado: Debilidado\t");
            }
        }
        System.out.println("\n");
    }

    /**
     * Muestra las pociones que tiene disponibles para utilizar el jugador.
     */
    public void mostrarPociones() {
        System.out.println("\t\t##### ESTE ES TU CONJUNTO DE POCIONES ACTUAL #####\n");
        for (int i = 0; i < pociones.size(); i++) {
            System.out.printf("[%d]\t\t\t",i+1);
        }
        System.out.println();
        for (int i = 0; i < pociones.size(); i++) {
            System.out.printf("- %s -\t\t",pociones.get(i).getTipo().toUpperCase());
        }
        System.out.println();
        for (int i = 0; i < pociones.size(); i++) {
            if (pociones.get(i).getEstado()) {
                System.out.printf("Estado: UTILIZADA\t");
            } else {
                System.out.printf("Estado: SIN UTILIZAR\t");
            }
        }
        System.out.println("\n");
    }

    /**
     * Permite añadir pokémon a la lista del jugador externamente.
     * 
     * @param pokemon Se indica el pokémon que será añadido a la lista.
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