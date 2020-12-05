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
		if(this.tipo.toLowerCase()=="ataque") {
			
		}else if (this.tipo.toLowerCase()=="defensa") {
			
		}else if (this.tipo.toLowerCase()=="salud") {
			
		}else{
			System.out.println("ERROR! ¿Como puede haber una posion con tipo invalido?");
		}
	}
}	