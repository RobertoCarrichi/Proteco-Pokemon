public class Pokemon {
    String apodo;
    private int vida;
    private int ataque;
    private int defensa;
    private int velocidad;
    boolean estado; // Vivo o muerto
    private String tipo;
    private String movimiento;
    int danio;

    /**
     * CONSTRUCTOR: La finalidad es que a partir de un nombre elegido por el
     * jugador, este se busque en una cantidad de opciones y según el nombre puedan
     * ser asignados sus características.
     * 
     * @param apodo
     */
    public Pokemon(String apodo) {
        this.apodo = apodo;
        this.tipo = identificarTipo(apodo);
        this.asignarValores();
    }
    
    /***********************************************
     * GETTERS Y SETTERS *
     ************************************************/
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    /**
     * Con este método será posible asignarle un valor en el atributo de "velocidad"
     * al pokemon. La única forma de mandar a llamar a este método es durante la
     * construcción de un pokemon durante la asignación de sus atributos.
     * 
     * @param velocidad
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
     * 
     * @param defensa
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * 
     * @return
     */
    public int getDefensa() {
		return this.defensa;
	}

    /**
     * 
     * @param ataque
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    /**
     * 
     * @return
     */
	public int getAtaque() {
		return this.ataque;
	}

    /**
     * 
     * @param vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * 
     * @return
     */
	public int getVida() {
		return this.vida;
	}


	// public float calcularMultiplicadorAtaque(Pokemon oponente){

    // }

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
        }else if (apodo.equals("Charmeleon")){
            return "Fuego";
        }

        return "No registrado";
    }



    /**
     * La finalidad de este método es proporcionar al jugador los atributos que
     * tiene un pokémon al momento de elegir durante un combate o para solo conocer
     * sus características.
     */
    public void mostrarInfo() {
        System.out.println("Nombre: "+this.apodo);
        System.out.println("Ataque: "+this.ataque);
        System.out.println("Defensa: "+this.defensa);
    }

}