public class Pokemon {
    String apodo;
    private int puntosVida;
    private int ataque;
    private int defensa;
    private int velocidad;
    boolean estado; // Vivo o muerto
    String tipo;
    String movimiento;
    int danio;

    public Pokemon(String apodo) {
        this.apodo = apodo;
        this.asignarValores();
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	// public float calcularMultiplicadorAtaque(Pokemon oponente){

    // }

    public void asignarValores(){
    	/**
    	*	La función de este método es asignarle los valores iniciales
    	*	que tiene el pokemon al momento de su construcción.
    	*/
        if(this.apodo.equals("Pikachu")){
            setAtaque(100);
            setDefensa(70);
            setPuntosVida(80);
            setVelocidad(130);
        }else if (this.apodo.equals("Otro nombre")){

        }
    }

    public void mostrarInfo() {
        System.out.println("Nombre: "+this.apodo);
        System.out.println("Ataque: "+this.ataque);
        System.out.println("Defensa: "+this.defensa);
    }

}