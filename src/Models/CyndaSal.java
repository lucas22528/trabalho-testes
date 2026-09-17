package Models;

public final class CyndaSal extends Pokesal {

    private static final int HP = 39;
    private static final int ATK = 52;
    private static final int DEF = 43;
    private static final int SPD = 65;

    public CyndaSal() {
        super("CyndaSal", HP, ATK, DEF, SPD, TipoElemental.FOGO);
    }
}