package item;

import Models.Pokesal;
import java.util.ArrayList;
import java.util.List;

public final class Mochila {
    public static final int LIMITE_DE_ITENS_POR_BATALHA = 2;

    private final List<Item> itensDisponiveis;
    private int itensUsados;

    public Mochila() {
        this.itensDisponiveis = new ArrayList<>();
        this.itensUsados = 0;
    }

    public void adicionarItem(final Item item) {
        itensDisponiveis.add(item);
    }

    public List<Item> getItensDisponiveis() {
        return new ArrayList<>(itensDisponiveis);
    }

    public int getItensUsados() {
        return itensUsados;
    }

    public void usarItem(final Item item, final Pokesal alvo) {
        if (itensUsados >= LIMITE_DE_ITENS_POR_BATALHA) {
            throw new LimiteItensExcedidoException(
                    "Limite de " + LIMITE_DE_ITENS_POR_BATALHA
                            + " itens por batalha excedido.");
        }
        item.usar(alvo);
        itensUsados++;
    }
}

