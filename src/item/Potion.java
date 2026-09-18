package item;

import Models.Pokesal;

/** Item de cura basica: recupera uma quantidade fixa de HP. */
public final class Potion implements Item {

    private static final int CURA_HP = 20;

    @Override
    public void usar(final Pokesal alvo) {
        alvo.curar(CURA_HP);
    }

    @Override
    public String getNome() {
        return "Potion";
    }
}
