public class Pokemon {
    String apodo;
    private String tipo;
    private int vida;
    private int ataque;
    private int defensa;
    private int velocidad;
    private String movimiento;
    boolean estado; // Representará cómo se encuentra, si "OK" (true) o "BAD" (false).
    int danio;

    /**
     * CONSTRUCTOR: La finalidad es que a partir de un nombre elegido por el
     * jugador, este se busque en una cantidad de opciones y según el nombre puedan
     * ser asignados sus características.
     * 
     * @param apodo Representa el nombre que tiene el pokémon.
     */
    public Pokemon(String apodo) {
        this.apodo = apodo;
        this.tipo = identificarTipo(apodo);
        this.asignarValores();
    }
    
    /************************************************
     * GETTERS Y SETTERS                            *
     *                                              *
     * LA ÚNICA MANERA DE ACCEDER A LOS SETTERS ES  *
     * MEDIANTE LA CONSTRUCCIÓN DEL POKEMON.        *
     ************************************************/
    
    /**
     * Permitirá poder tener acceso al tipo al que pertenece el pokémon.
     * @return
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Permitirá poder asignar un tipo al pokemon, alguno como "Agua", 
     * "Fuego", "Hierba" o "Eléctico".
     * @param tipo Se requiere de una cadena que a la cual será asignada al atributo de la instancia.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Permitirá el acceso al atributo "Movimiento" de un pokémon.
     * @return
     */
    public String getMovimiento() {
        return movimiento;
    }

    /**
     * Permite asignar un movimiento base al pokémon.
     * @param movimiento Representa el nombre que tendrá el movimiento base.
     */
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Con este método será posible asignarle un valor en el atributo de "velocidad"
     * al pokemon. La única forma de mandar a llamar a este método es durante la
     * construcción de un pokemon durante la asignación de sus atributos.
     * 
     * @param velocidad Representa el valor entero que tendrá el pokémon en su estadística de velocidad.
     */
     public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Este método hace que podamos obtener el valor del atributo "velocidad" del pokemon actual.
     * @return
     */
    public int getVelocidad() {
        return this.velocidad;
    }

    /**
     * Permite asignar un valor entero a la estadística de "Defensa". Su valor tendrá el resultado
     * de una función de núemeros aleatorios, la cual será utilizada para todos los pokémon.
     * @param defensa Es un valor entero que será necesario para asignanrlo a la defensa del pokémon.
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Método que retornará el valor entero que representará el valor en la estadística de "Defensa".  
     * @return
     */
    public int getDefensa() {
        return this.defensa;
	}
    
    /**
     * Permite asignar un valor a la estadística de la defensa del pokémon. La única forma de acceder
     * a este método es durante la construcción de la instancia.
     * @param ataque Un valor entero que pronto será igual al valor de la estadística.
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    /**
     * La finalidad de este método es proporcionar al usuario el valor a la estadij
     * @return Retorna el valor del atributo "ataque" del pokémon.
     */
    public int getAtaque() {
        return this.ataque;
    }

    /**
     * Con este método es posible asignar un valor en el atributo "Vida" el cual representará los puntos
     * de salud que tendrá el pokémon.  
     * @param vida Valor que se le asignará al atributo de la instancia.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Hace posible tener acceso a la cantidad de puntos de vida que tiene un pokemon actualmente.
     * @return
     */
	public int getVida() {
		return this.vida;
	}

    /**************************************
    *       MÉTODOS DE INSTANCIA          *
    ***************************************/

    /**
     * La función de este método es asignarle los valores iniciales
     * que tiene el pokemon al momento de su construcción.
     */
    public void asignarValores(){
        this.setVida(aleatorio(150,250));
        this.setAtaque(aleatorio(150,200));
        this.setDefensa(aleatorio(150,200));
        this.setVelocidad(aleatorio(100,200));
    }

    public int aleatorio(int min, int max){
        int range = max - min + 1;
        int normal = (int)((Math.random() * range) + min);
        int extra = (int)(Math.random()*20);
        double aux = Math.random()*2; 
        System.out.println(aux);
        int resultado;
        if (aux>1){
            resultado = normal+extra;
        } else {
            resultado = normal-extra;
        }
        return resultado;
    }

    /**
     * Este método identificará el tipo que tiene el pokémon según su apodo.
     * Para este programa solo existirán los tipos Agua, Fuego, Hierba y Eléctrico.
     * @param apodo
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
        // Si se da el caso de que no se encuentre el pokémon se escribe como un "No registrado".
// DEBERÍA PASAR ALGO CRÍTICO AQUI.        
        return "No registrado";
    }

    /**
     * Este método permitirá elegir que tan efectivo será o no un ataque de la instancia actual
     * con un pokemon que recibe como parámetro.
     * @param oponente Instancia de tipo "Pokemon" que representará al oponente al que atacará.
     * @return Retorna el equivalente al multiplicador que indicará si afectará más o menos a su oponente.
     */
    public double calcularMultiplicadorElemental(Pokemon oponente){
        if (oponente.tipo.equals("Agua")) {
            /* 
                Se verifica si el tipo del pokemon de la instancia es favorable ante el tipo
                del oponente.
            */
            if (this.getTipo().equals("Hierba")||this.getTipo().equals("Eléctrico")) {
                return 2;
            }else if(this.getTipo().equals("Agua")||this.getTipo().equals("Fuego")){
                return 0.5;
            }else{
                return 1;
            }
        }else if(oponente.tipo.equals("Fuego")){
            if (this.getTipo().equals("Agua")) {
                return 2;
            }else if(this.getTipo().equals("Hierba")){
                return 0.5;
            }else{
                return 1;
            }            
        }else if(oponente.tipo.equals("Hierba")){
            if (this.getTipo().equals("Fuego")) {
                return 2;
            }else if(this.getTipo().equals("Hierba")||this.getTipo().equals("Eléctrico")||this.getTipo().equals("Agua")){
                return 0.5;
            }else{
                return 1;
            }
        }else if(oponente.tipo.equals("Eléctrico.")){
            if(this.getTipo().equals("Fuego")||this.getTipo().equals("Eléctrico")){
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
     * La finalidad de este método es proporcionar al jugador los atributos que
     * tiene un pokémon al momento de elegir durante un combate o para solo conocer
     * sus características.
     */
    public void mostrarInfo() {
        System.out.println("   "+this.apodo.toUpperCase());
        System.out.println("Vida: "+this.vida);
        System.out.println("Ataque: "+this.ataque);
        System.out.println("Defensa: "+this.defensa);
        System.out.println("Velocidad: "+this.defensa);
        
// AQUI FALTARIA IMPRIMIR LOS MOVIMIENTOS QUE TIENE EL POKEMON.
    }

}