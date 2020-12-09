public class Pocion{
	String tipo;

	public Pocion(String tipo){
		this.tipo=tipo;
	}

	/**
	 * "USAR POCIÓN"
	 * La funcionalidad de este método será modificar
	 * los atributos del pokemon según el tipo de poción
	 * solicitado.
	 * @param pokemon
	 * @return
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
	}
}	