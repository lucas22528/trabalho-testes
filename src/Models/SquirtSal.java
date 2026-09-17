package Models;

public final class SquirtSal extends Pokesal {

    private static final int HP = 44;
    private static final int ATK = 48;
    private static final int DEF = 65;
    private static final int SPD = 43;

    public SquirtSal() {
        super("SquirtSal", HP, ATK, DEF, SPD, TipoElemental.AGUA);
    }
}