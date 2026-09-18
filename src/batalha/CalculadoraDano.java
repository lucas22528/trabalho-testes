package batalha;

import Models.Pokesal;
import Models.Terreno;

public final class CalculadoraDano {
    private static final double FATOR_BASE = 0.5;

    private static final int DANO_MINIMO = 1;

    private CalculadoraDano() {
    }

    public static int calcular(final Pokesal atacante, final Pokesal defensor,
                               final Terreno terreno) {
        final double danoBase = atacante.getAtkAtual() * FATOR_BASE
                - defensor.getDefBase() * (FATOR_BASE / 2);
        final double multiplicadorTipo = atacante.getTipo().multiplicadorContra(
                defensor.getTipo());
        final double multiplicadorTerreno = terreno.bonusDeDanoPara(atacante.getTipo());

        final double danoFinal = Math.max(DANO_MINIMO, danoBase)
                * multiplicadorTipo * multiplicadorTerreno;

        return (int) Math.max(DANO_MINIMO, Math.round(danoFinal));
    }
}

