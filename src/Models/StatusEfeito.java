package Models;

public enum StatusEfeito {

    NENHUM,
    QUEIMADO,
    ENVENENADO,
    PARALISADO;

    public static final double DANO_QUEIMADURA_POR_TURNO = 0.05;
    public static final double REDUCAO_ATK_QUEIMADURA = 0.10;
    public static final double DANO_BASE_VENENO = 1.0 / 16.0;
    public static final double REDUCAO_SPD_PARALISIA = 0.50;
    public static final double CHANCE_FALHAR_TURNO_PARALISIA = 0.25;
}
