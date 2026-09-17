package Models;

public final class TotoSal extends Pokesal {

    private static final int HP = 50;
    private static final int ATK = 65;
    private static final int DEF = 64;
    private static final int SPD = 43;

    public TotoSal() {
        super("TotoSal", HP, ATK, DEF, SPD, TipoElemental.AGUA);
    }
}
