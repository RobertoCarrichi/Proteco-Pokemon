public class Pokemon {
    String apodo;
    int puntosVida;
    int ataque;
    int defensa;
    int velocidad;
    boolean estado; // Vivo o muerto
    String tipo;
    String movimiento;

    public Pokemon(String apodo){
    	this.apodo = apodo;
    	this.asignarValores();
    }

    public float calcularMultiplicadorAtaque(Pokemon oponente){
        
    }

    public void asignarValores(){
    	/**
    	*	La función de este método es asignarle los valores iniciales
    	*	que tiene el pokemon al momento de su construcción.
    	*/
    	
    }
}