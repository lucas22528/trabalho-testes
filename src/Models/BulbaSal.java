package Models;

public final class BulbaSal extends Pokesal {

    private static final int HP = 45;
    private static final int ATK = 49;
    private static final int DEF = 49;
    private static final int SPD = 45;

    public BulbaSal() {
        super("BulbaSal", HP, ATK, DEF, SPD, TipoElemental.PLANTA);
    }
}
