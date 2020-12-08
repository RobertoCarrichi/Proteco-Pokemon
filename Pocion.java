public class Pocion{
	String tipo;

	public Pocion(String tipo){
		this.tipo=tipo;
	}

	public String usar(Pokemon pokemon){
		/**
		*	La funcionalidad de este método será modificar
		*	los atributos del pokemon según el tipo de poción
		*	solicitado.
		*/
		if(this.tipo.equals("Ataque")) {
			pokemon.ataque = pokemon.ataque + (int)(pokemon.ataque*0.1);
		}else if (this.tipo.equals("Defensa")) {
			pokemon.defensa = pokemon.defensa + (int)(pokemon.defensa*0.1);
		}else if (this.tipo.equals("Salud")) {
			pokemon.puntosVida = pokemon.puntosVida + (int)(pokemon.puntosVida*0.2);
		}else{
			System.out.println("ERROR! ¿Como puede haber una posion con tipo invalido?");
		}
	}
}	