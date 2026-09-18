package treinador;

import item.Mochila;
import Models.Pokesal;

public final class Treinador {

    private final String nome;
    private final Pokesal pokesal;
    private final Mochila mochila;

    public Treinador(final String nome, final Pokesal pokesal) {
        this.nome = nome;
        this.pokesal = pokesal;
        this.mochila = new Mochila();
    }

    public String getNome() {
        return nome;
    }

    public Pokesal getPokesal() {
        return pokesal;
    }

    public Mochila getMochila() {
        return mochila;
    }
}
