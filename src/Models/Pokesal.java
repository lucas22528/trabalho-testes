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
        this.status = StatusEfeito.NENHUM;
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

    public void aplicarStatus(final StatusEfeito novoStatus) {
        this.status = novoStatus;
        this.turnoComStatus = 0;
        if (novoStatus == StatusEfeito.QUEIMADO) {
            this.atkAtual = (int) Math.round(atkBase * (1.0 - StatusEfeito.REDUCAO_ATK_QUEIMADURA));
        } else if (novoStatus == StatusEfeito.PARALISADO) {
            this.spdAtual = (int) Math.round(spdBase * (1.0 - StatusEfeito.REDUCAO_SPD_PARALISA));
        }
    }

    public void curarStatus() {
        this.status = StatusEfeito.NENHUM;
        this.turnoComStatus = 0;
        this.atkAtual = atkBase;
        this.spdAtual = spdBase;
    }

    public void receberDano(final int dano) {
        this.hpAtual = Math.max(0, this.hpAtual - dano);
    }

    public void curar(final int cura) {
        this.hpAtual = Math.min(hpMaximo, this.hpAtual + cura);
    }

    public void aplicarEfeitoStatusFimTurno() {
        if (status == StatusEfeito.NENHUM || !estaVivo()) {
            return;
        }
        turnoComStatus++;
        if (status == StatusEfeito.QUEIMADO) {
            final int dano = (int) Math.round(hpMaximo * StatusEfeito.DANO_QUEIMADURA);
            receberDano(dano);
        } else if (status == StatusEfeito.ENVENENADO) {
            final int dano = (int) Math.round(hpMaximo * StatusEfeito.DANO_VENENO * turnoComStatus):
            receberDano(dano);
        }
    }

    public void aplicarEfeitoTerrenoFimTurno(final Terreno terreno ) {
        if(terreno == Terreno.CANTEIRO_CENTRAL && tipo == tipoElemental.PLANTA && estaVivo()){
            final int cura = (int) Math.round(hpMaximo * Terreno.CURA_CANTEIRO_CENTRAL);
            curar(cura);
        }
    }

    public boolean falhaTurnoParalisia(){
        if(status != StatusEfeito.PARALISADO){
            return false;
        } return Math.random() < StatusEfeito.CHANCE_FALHA;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] HP:%d/%d ATK:%d DEF:%d SPD:%d Status:%s", nome, tipo, hpAtual, hpMaximo,atkAtual, defBase, spdAtual, status);
    }

}
