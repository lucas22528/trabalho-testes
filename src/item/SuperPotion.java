package item;

import Models.Pokesal;

public final class SuperPotion implements Item {
    private static final int CURA_HP = 50;

    @Override
    public void usar(final Pokesal alvo) {
        alvo.curar(CURA_HP);
    }

    @Override
    public String getNome() {
        return "Super Potion";
    }
}
