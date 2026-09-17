package Models;

public final class ChikoSal extends Pokesal {

    private static final int HP = 45;
    private static final int ATK = 49;
    private static final int DEF = 65;
    private static final int SPD = 45;

    public ChikoSal() {
        super("ChikoSal", HP, ATK, DEF, SPD, TipoElemental.PLANTA);
    }
}

