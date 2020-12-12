/**
 * Clase que presenta toda la esctructura que tiene un pokémon, expresando sus atributos y métodos
 * de forma individual.
 */
public class Pokemon {

    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/

    /**
     * Indica el nombre del pokémon.
     */
    String apodo;
    
    /**
     * Indica el tipo al que pertenece el pokémon, puede ser de tipo agua, hierba, fuego o eléctrico.
     */
    private String tipo;
    
    /**
     * Representará la cantidad de puntos de vida que tiene el pokémon después de que fuera asignado por el
     * método aleatorio( ).
     */
    private int vida;
    
    /**
     * Indica la cantidad de puntos de ataque que tiene el pokémon.
     */
    private int ataque;
    
    /**
     * Indica la cantidad de puntos de defensa que tendrá el pokémon.
     */
    private int defensa;
    
    /**
     * Representa la cantidad de puntos de velocidad que tiene el pokémon, principalmente para saber si es más velóz que 
     * su oponente para hacerle daño antes que recibirlo.
     */
    private int velocidad;
    
    /**
     * Se trata del nombre del movimiento base que tiene el pokémon para poder luchar.
     */
    private String [] movimientos = new String[2];

    /**
     * Indica si se encuentra en buen estado (true) o en mal estado (false).
     */
    private boolean estado; 

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/

    /**
     * Tiene el objetivo de inferir el tipo al que pertenece el pokémon mediante su "apodo" y posteriormente asignarle
     * valores.
     * @param apodo Requiere un nombre que identifique al pokémon.
     */
    public Pokemon(String apodo) {
        this.apodo = apodo;
        this.tipo = identificarTipo(apodo);
        this.asignarValores();
    }
    
    /************************************************
     *              GETTERS Y SETTERS               *
     ************************************************/
    
	/**
     * Permitirá poder asignar un tipo al pokemon, alguno como "Agua", "Fuego", "Hierba" o "Eléctrico".
     * @param tipo Se requiere de una cadena que será asignada al atributo "tipo" del pokémon.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Permitirá tener acceso al tipo al que pertenece el pokémon.
     * @return Retorna una cadena que indica el tipo de pokémon, puede ser "Agua", "Fuego", "Hierba" o "Eléctirico".
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Permite asignar movimientos de tipo base y cargados al pokémon durante su construcción.
     */
    public void setMovimientos() {
        if(this.identificarTipo(this.apodo).equals("Agua")){
            this.movimientos[0] = "Burbuja";
            this.movimientos[1] = "Hidrocañón";
        }else if(this.identificarTipo(this.apodo).equals("Fuego")){
            this.movimientos[0] = "Nitrocarga";
            this.movimientos[1] = "Anillo Igneo";
        }else if(this.identificarTipo(this.apodo).equals("Hierba")){
            this.movimientos[0] = "Hoja aguda";
            this.movimientos[1] = "Planta feroz";
        }else if(this.identificarTipo(this.apodo).equals("Eléctrico")){
            this.movimientos[0] = "Puño trueno";
            this.movimientos[1] = "Voltio cruel";
        }
    }

    /**
     * Permitirá el acceso al atributo "movimiento" de un pokémon.
     * @param opcion Requiere de un valor de tipo entero para saber si se trata de llamar
     * al ataque base o al ataque cargado.
     * @return Retorna una cadena que indica el nombre con el que está asociado el pokémon.
     */
    public String getMovimiento(int opcion) {
        if (opcion == 0) {
            return this.movimientos[0];
        } else {
            return this.movimientos[1];   
        }

    }

    /**
     * Asigna un valor en el atributo de "velocidad" al pokemon que llamó al método. 
     * La única forma de mandar a llamar a este método es durante la construcción de un pokemon 
     * durante la asignación de sus atributos.
     * 
     * @param velocidad Representa el valor entero que tendrá el pokémon en su estadística de velocidad.
     */
     public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Método que permite obtener el valor del atributo "velocidad" del pokemon.
     * @return Retorna la cantidad de puntos de velocidad que tiene el pokémon.
     */
    public int getVelocidad() {
        return this.velocidad;
    }

    /**
     * Permite asignar un valor entero a la estadística de defensa.
     * @param defensa Es un valor entero que será necesario para asignanrlo al atributo "defensa" del pokémon.
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Método que retornará un valor entero el cuál representará los puntos de defensa que tiene el pokémon.  
     * @return Retorna la cantidad de puntos de defensa del pokémon que llamó al método.
     */
    public int getDefensa() {
        return this.defensa;
	}
    
    /**
     * Permite asignar un valor a la estadística de la defensa del pokémon. La única forma de acceder
     * a este método es durante la construcción de la instancia.
     * @param ataque Un valor entero que será asignado como la cantidad de puntos de ataque que tendŕa el pokémon.
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    /**
     * Proporciona al jugador la cantidad de ataque que tiene actualmente el pokemon que llama al método.
     * @return Retorna el valor del atributo "ataque" del pokémon.
     */
    public int getAtaque() {
        return this.ataque;
    }

    /**
     * Con este método es posible asignar un valor en el atributo "Vida" el cual representará los puntos
     * de salud que tendrá el pokémon.  
     * @param vida Valor entero que se le asignará al atributo "vida" del pokémon.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Hace posible tener acceso a la cantidad de puntos de vida que tiene un pokemon actualmente.
     * @return Retorna el valor que representa la cantidad de vida actual que tiene el pokémon.
     */
	public int getVida() {
		return this.vida;
	}

    /**
     * Permite asignar el estado del pokémon, si sus puntos de vida son mayores a cero, este estado será representado
     * por un "true" y en consola se vería como con "OK", en caso que su estado será "false" entonces se encuentra 
     * debilidado o "BAD".
     * @param estado Valor boobleano que será asignado al atributo "estado" de un pokémon.
     */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

    /**
     * Permite tener un acceso a el parámetro "estado" que tiene un pokémon para saber si está en condiciones de pelear
     * o si no lo está.
     * @return Devuelve el valor que representa el estado actual  del pokémon.
     */
    public boolean getEstado() {
		return this.estado;
	}

    /**************************************
    *       MÉTODOS DE INSTANCIA          *
    ***************************************/

    /**
     * Asigna los valores iniciales que tendrá el pokemon al momento de su construcción.
     */
    public void asignarValores(){
        this.setVida(aleatorio(200,250));
        this.setAtaque(aleatorio(150,200));
        this.setDefensa(aleatorio(150,200));
        this.setVelocidad(aleatorio(100,150));
        this.setMovimientos();
        this.setEstado(true);
    }

    /**
     * Genera un número aleatorio dado un rango de valores y a este resultado se le suba un valor entre -20 y 20 para 
     * mantener igualdad de condiciones entre pokémon.
     * @param min Indica el valor mínimo del rango.
     * @param max Indica el valor máximo del rango.
     * @return Retorna el valor que será asignado a alguna de las estadísticas del pokémon (vida, ataque, defensa o velocidad).
     */
    public int aleatorio(int min, int max){
        /**
         * RANGO indica el la cantidad de números posibles entre el valor mínimo y el máximo evitando que exista un valor cero.
         */
        int rango = max - min;
        /**
         * NORMAL representará el valor aleatorio inicial en el rango específicado.
         */
        int normal = (int)((Math.random() * rango) + min);
        /**
         * EXTRA indica el valor que puede hacer que varíe la estadística entre un valor de 0 y 20;
         */
        int extra = (int)(Math.random()*20);
        /**
         * AUX representa un valor aleatorio entre 0 y 2, dependiendo su valor se sabrá si el valor de EXTRA se restará
         * (un valor de -20 a 0) o si se sumará (un valor de 0 a 20) a la estadística NORMAL (la que se asignará al atributo
         * del pokémon).
         */
        double aux = Math.random()*2; 
        /**
         * RESULTADO representará el valor final con el que se asignará al atributo del pokémon.
         */
        int resultado;
        /**
         * En esta condición si el valor de AUX es de 0 hasta 1 significa que el valor de EXTRA se restará, si el valor de 
         * AUX es de 1 a 2 entonces el valor EXTRA se sumará.
         */
        if (aux>=1){
            resultado = normal+extra;
        } else {
            resultado = normal-extra;
        }
        return resultado;
    }

    /**
     * Identificará el tipo que tiene el pokémon según su apodo, puede ser de tipo Agua, Fuego, Hierba o Eléctrico.
     * @param apodo Representa el nombre que eligió el jugador al momento de elegir a su equipo de pokémon.
     * @return Se retornará la cadena que represente al tipo del pokémon.
     */
    public String identificarTipo(String apodo) {
        if(apodo.equals("Wartortle")){
            return "Agua";
        }else if(apodo.equals("Golduck")){
            return "Agua";
        }else if(apodo.equals("Poliwhirl")){
            return "Agua";
        }else if(apodo.equals("Charmeleon")){
            return "Fuego";
        }else if(apodo.equals("Ninetales")){
            return "Fuego";
        }else if(apodo.equals("Rapidash")){
            return "Fuego";
        }else if (apodo.equals("Ivysaur")){
            return "Hierba";
        }else if (apodo.equals("Bayleef")){
            return "Hierba";
        }else if (apodo.equals("Grovyle")){
            return "Hierba";
        }else if (apodo.equals("Raichu")){
            return "Eléctrico";
        }else if (apodo.equals("Electabuzz")){
            return "Eléctrico";
        }else if (apodo.equals("Jolteon")){
            return "Eléctrico";
        }
        return "No registrado";
    }

    /**
     * Identifica que tan efectivo será o no un ataque del pokemon que llamó a la función hacia el
     * pokemon que recibe como parámetro.
     * @param oponente Instancia de tipo "Pokemon" que representará al oponente al que atacará.
     * @return Retorna el equivalente al multiplicador que indicará si afectará más o menos a su oponente.
     */
    public double calcularMultiplicadorElemental(Pokemon oponente){
        if (this.tipo.equals("Agua")) {
            /**
             * Se verifica si el tipo del pokemon de la instancia es favorable ante el tipo
             * del oponente.
             */
            if (oponente.getTipo().equals("Fuego")) {
                return 2;
            }else if(oponente.getTipo().equals("Agua")||oponente.getTipo().equals("Hierba")){
                return 0.5;
            }else{
                return 1;
            }
        }else if(this.tipo.equals("Fuego")){
            if (oponente.getTipo().equals("Hierba")) {
                return 2;
            }else if(oponente.getTipo().equals("Agua")||oponente.getTipo().equals("Fuego")){
                return 0.5;
            }else{
                return 1;
            }            
        }else if(this.tipo.equals("Hierba")){
            if (oponente.getTipo().equals("Agua")) {
                return 2;
            }else if(oponente.getTipo().equals("Fuego")||oponente.getTipo().equals("Hierba")){
                return 0.5;
            }else{
                return 1;
            }
        }else if(this.tipo.equals("Eléctrico")){
            if (oponente.getTipo().equals("Agua")) {
                return 2;
            }else if(oponente.getTipo().equals("Hierba")||oponente.getTipo().equals("Eléctrico")){
                return 0.5;
            }else{
                return 1;
            }
        }else{
            // Si se llega aquí es que existe una instancia con un tipo que no está definido.
            return -1;
        }
    }

    /**
     * Muesta al jugador los atributos que tiene su equipo de pokémon al momento de elegir,
     * durante un combate o para solo conocer sus características.
     */
    public void mostrarInfo() {
        System.out.println("      "+this.apodo.toUpperCase());
        System.out.println("   Tipo: "+this.tipo);
        System.out.println("   Vida: "+this.vida);
        System.out.println("   Ataque: "+this.ataque);
        System.out.println("   Defensa: "+this.defensa);
        System.out.println("   Velocidad: "+this.velocidad);
        System.out.println("   Ataques: "+this.movimientos[0]);
        System.out.println("            "+this.movimientos[1]);
        if ( getEstado() ) {
            System.out.println("   Estado: OK");
        } else {
            System.out.println("   Estado: Debilitado");
        }
        System.out.println();
    }

    /**
     * Permite ejecutar un ataque desde la instancia que llame a este método hacia un oponente
     * dado como parámetro y con un ataque elegido previamene.
     * @param oponente Instancia de tipo JUGADOR la cual representa al entrenador enemigo.
     * @param ataqueElegido Valor entero que indica uno de los dos ataques que tiene disponible cada pokémon.
     * Está representado por un 1 y un 0, donde el cero hace referencia al ataque base y el uno a un ataque cargado.
     * @return Retorna el daño que será aplicado al pokémon del oponente.
     */
    public int atacar(Jugador oponente, int ataqueElegido){
        oponente.getPeleador().getVida();
        int danioAplicado;
        if( ataqueElegido == 0 ){
            // El jugador aplicará el ataque básico en el oponente.
            danioAplicado = (int)( ( getAtaque() - oponente.getPeleador().getDefensa() )* calcularMultiplicadorElemental(oponente.getPeleador()) );
            if( getAtaque() - oponente.getPeleador().getDefensa() <= 0 ){
                // Si el ataque menos la defensa en menor a 0 el pokémon no recibe daño.
            }else if ( danioAplicado >= oponente.getPeleador().getVida() ) {
                // Si el daño que se aplicará es mayor o igual a la vida del oponente este se debilitará.
                oponente.getPeleador().setVida(0);
            }else{
                oponente.getPeleador().setVida( oponente.getPeleador().getVida() - danioAplicado );
            }
        }else{
            // El jugador aplicará el ataque cargado en el oponente.
            danioAplicado = getAtaque() - oponente.getPeleador().getDefensa();
            if( danioAplicado <= 0 ){
                // Si el ataque menos la defensa en menor a 0 el pokémon no recibe daño.
            }else if ( ( getAtaque() - oponente.getPeleador().getDefensa() ) >= oponente.getPeleador().getVida() ) {
                // Si el daño que se aplicará es mayor o igual a la vida del oponente este se debilitará.
                oponente.getPeleador().setVida(0);
                oponente.getPeleador().setEstado(false);
            }else{
                oponente.getPeleador().setVida( oponente.getPeleador().getVida() - danioAplicado );
            }
        }
        oponente.getPeleador().estaDebilitado();
        oponente.getPeleador().getVida();
        return danioAplicado;
    }

    /**
     * Indica el estado del pokémon que llama a la función y si la vida de este es menor a 0 se le
     * asignará un valor que represente que el pokémon se encuentra debilitado.
     * @return Retorna el estado del pokémon, donde un "true" indica que está en condiciones de
     * luchar y un "false" cuando no es así.
     */
    public boolean estaDebilitado(){
        if( this.getVida() <= 0 ){
            this.setEstado(false);
        }
        return this.estado;
    }
}