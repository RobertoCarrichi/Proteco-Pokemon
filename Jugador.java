import java.util.ArrayList;

public class Jugador {
    ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();

    public Jugador() {
        
    }

    public void mostrarPokemon() {
        for (Pokemon pokemon : pokemones) {
            // Instrucciones
            pokemon.mostrarInfo();
        }
    }
}