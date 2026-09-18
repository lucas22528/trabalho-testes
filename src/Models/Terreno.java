package Models;

public enum Terreno {

    ASFALTO_QUENTE,
    POCA_DE_CHUVA,
    CANTEIRO_CENTRAL,
    NENHUM;

    public static final double BONUS_DANO_ASFALTO_QUENTE = 0.15;
    public static final double BONUS_DANO_POCA_DE_CHUVA = 0.10;
    public static final double CURA_CANTEIRO_CENTRAL = 0.05;

    public double bonusDeDanoPara(final TipoElemental tipoDoAtaque) {
        if (this == ASFALTO_QUENTE && tipoDoAtaque == TipoElemental.FOGO) {
            return 1.0 + BONUS_DANO_ASFALTO_QUENTE;
        }
        if (this == POCA_DE_CHUVA && tipoDoAtaque == TipoElemental.AGUA) {
            return 1.0 + BONUS_DANO_POCA_DE_CHUVA;
        }
        return 1.0;
    }
}
