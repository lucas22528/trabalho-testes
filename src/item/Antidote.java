package item;

import Models.Pokesal;

public final class Antidote implements Item {

    @Override
    public void usar(final Pokesal alvo) {
        alvo.curarStatus();
    }

    @Override
    public String getNome() {
        return "Antidote";
    }
}
