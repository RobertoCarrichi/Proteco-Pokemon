public class Pocion{
    /******************************************
     *        ATRIBUTOS DE INSTANCIA          *
     ******************************************/
	
	/**
	 * Indica el tipo de poción, puede ser de salud, ataque o defensa.
	 */
	private String tipo;
	
	/**
	 * Indica el stado de la poción a través de un booleano.
	 */
	private boolean estado;

    /******************************************
     *              CONSTRUCTOR               *
     ******************************************/
	/**
	 * Inicializa una instancia que será capaz de aumentar características de un pokemón.
	 * @param tipo
	 */
	public Pocion(String tipo){
		this.tipo = tipo;
		this.estado = false;
	}

	
	/** 
	 * Otiene tipo de poción.
	 * @return String
	 */
	public String getTipo() {
		return this.tipo;
	}

	
	/** 
	 * Asigna el tipo de poción.
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	/** 
	 * Obtiene el estado de la poción a través de un booleando.
	 * @return boolean
	 */
	public boolean getEstado() {
		return estado;
	}

	
	/** 
	 * Indica el estado de la poción a través de un booleando.
	 * @param estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * Modifica los atributos del pokemon según el tipo de poción que llama al método.
	 * @param pokemon Indica el pokemon al que se le aplicará la poción.
	 */
	public void usar(Pokemon pokemon){
		if(this.tipo.equals("Ataque")) {
			pokemon.setAtaque(pokemon.getAtaque() + (int)(pokemon.getAtaque()*0.1));
		}else if (this.tipo.equals("Defensa")) {
			pokemon.setDefensa(pokemon.getDefensa() + (int)(pokemon.getDefensa()*0.1));
		}else if (this.tipo.equals("Salud")) {
			pokemon.setVida(pokemon.getVida() + (int)(pokemon.getVida()*0.2));
		}else{
			System.out.println("ERROR! ¿Como puede haber una posion con tipo invalido?");
		}
		// Ya que si fue utilizada, el estado cambia.
		this.estado = true;
	}

	/**
	 * Indica la información general de una sola poción.
	 */
	public void info(){
		System.out.println("    POCION");
		System.out.println("Tipo: "+this.tipo);
	}
}	
