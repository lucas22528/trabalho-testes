package Models;

public abstract class Pokesal {
    private final String nome;
    private final int hpMaximo, atkBase, defBase, spdBase;
    private final TipoElemental tipo;

    private int hpAtual, atkAtual, spdAtual;
    private StatusEfeito status;
    private int turnoComStatus;

    protected Pokesal(final String nome, final int hp, final int atk, final int def, final int spd, final TipoElemental tipo) {
        this.nome = nome;
        this.hpMaximo = hp;
        this.atkBase = atk;
        this.defBase = def;
        this.spdBase = spd;
        this.tipo = tipo;

        this.hpAtual = hp;
        this.atkAtual = atk;
        this.spdAtual = spd;
        this.status = StatusEfeitos.NENHUM;
        this.turnoComStatus = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public int getAtkBase() {
        return atkBase;
    }

    public int getAtkAtual() {
        return atkAtual;
    }

    public int getDefBase() {
        return defBase;
    }

    public int getSpdBase() {
        return spdBase;
    }

    public int getSpdAtual() {
        return spdAtual;
    }

    public TipoElemental getTipo() {
        return tipo;
    }

    public StatusEfeito getStatus() {
        return status;
    }

    public boolean estaVivo() {
        return hpAtual > 0;
    }


}
