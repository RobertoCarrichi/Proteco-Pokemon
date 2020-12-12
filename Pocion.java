/**
 * Indica la estructura básica que tiene una poción durante el combate, esta tiene una estructura
 * simple, pues solo tien un tipo, un estado y puede ser aplicada en un pokémon determinado.
 */
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
	 * @param tipo Representa una cadena de caracteres que expresa si es de tipo salud, ataque o
	 * defensa.
	 */
	public Pocion(String tipo){
		this.tipo = tipo;
		this.estado = false;
	}

	
	/** 
	 * Permite obtener el tipo de poción.
	 * @return Retorna la cadena de caracteres que indica el tipo de poción de la instancia
	 * que llama al método.
	 */
	public String getTipo() {
		return this.tipo;
	}

	/** 
	 * Permite asignar el tipo de poción.
	 * @param tipo Indica la cadena que ahora representará al tipo de poción.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/** 
	 * Obtiene el estado de la poción a través de un booleano.
	 * @return Retorna un valor booleano, si es un "true" indica que la poción ya fue utilizada
	 * y un "false" en caso contrario.
	 */
	public boolean getEstado() {
		return estado;
	}

	
	/** 
	 * Permite asignar el estado de la poción, durante la construcción o después de ser utilizada.
	 * @param estado Requiere de un valor booleano que represente si la poción ya fue utilizada o si no
	 * "true" para caso verdadero y "false" para caso contrario.
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
