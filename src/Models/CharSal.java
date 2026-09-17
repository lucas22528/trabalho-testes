package Models;

public final class CharSal extends Pokesal {

    private static final int HP = 39;
    private static final int ATK = 52;
    private static final int DEF = 43;
    private static final int SPD = 65;

    public CharSal() {
        super("CharSal", HP, ATK, DEF, SPD, TipoElemental.FOGO);
    }
}

