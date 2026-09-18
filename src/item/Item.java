package item;

import Models.Pokesal;

public interface Item {
    void usar(Pokesal alvo);

    String getNome();
}
