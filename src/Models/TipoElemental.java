package Models;

public enum TipoElemental {

    FOGO,
    AGUA,
    PLANTA;

    public static final double MULTIPLICADOR_SUPER_EFETIVO = 2.0;
    public static final double MULTIPLICADOR_POUCO_EFETIVO = 0.5;
    public static final double MULTIPLICADOR_NEUTRO = 1.0;

    public double multiplicadorContra(final TipoElemental defensor) {
        if (this == FOGO && defensor == PLANTA) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (this == FOGO && defensor == AGUA) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        if (this == AGUA && defensor == FOGO) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (this == AGUA && defensor == PLANTA) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        if (this == PLANTA && defensor == AGUA) {
            return MULTIPLICADOR_SUPER_EFETIVO;
        }
        if (this == PLANTA && defensor == FOGO) {
            return MULTIPLICADOR_POUCO_EFETIVO;
        }
        return MULTIPLICADOR_NEUTRO;
    }
}
