package batalha;

import item.Item;
import Models.Pokesal;
import Models.StatusEfeito;
import Models.Terreno;
import treinador.Treinador;
import java.util.ArrayList;
import java.util.List;

public final class Batalha {
    private final Treinador treinadorUm;
    private final Treinador treinadorDois;
    private final Terreno terreno;
    private int numeroDoTurno;

    public Batalha(final Treinador treinadorUm, final Treinador treinadorDois,
                   final Terreno terreno) {
        this.treinadorUm = treinadorUm;
        this.treinadorDois = treinadorDois;
        this.terreno = terreno;
        this.numeroDoTurno = 0;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public boolean batalhaTerminou() {
        return !treinadorUm.getPokesal().estaVivo() || !treinadorDois.getPokesal().estaVivo();
    }

    public Treinador getVencedor() {
        if (!batalhaTerminou()) {
            return null;
        }
        if (treinadorUm.getPokesal().estaVivo()) {
            return treinadorUm;
        }
        if (treinadorDois.getPokesal().estaVivo()) {
            return treinadorDois;
        }
        return null;
    }

    public List<String> executarTurno(final Acao acaoUm, final Item itemUm,
                                      final Acao acaoDois, final Item itemDois) {
        numeroDoTurno++;
        final List<String> log = new ArrayList<>();
        log.add("--- Turno " + numeroDoTurno + " (Terreno: " + terreno + ") ---");

        final boolean umPrimeiro = treinadorUm.getPokesal().getSpdAtual()
                >= treinadorDois.getPokesal().getSpdAtual();

        if (umPrimeiro) {
            executarAcao(treinadorUm, acaoUm, itemUm, treinadorDois, log);
            if (!batalhaTerminou()) {
                executarAcao(treinadorDois, acaoDois, itemDois, treinadorUm, log);
            }
        } else {
            executarAcao(treinadorDois, acaoDois, itemDois, treinadorUm, log);
            if (!batalhaTerminou()) {
                executarAcao(treinadorUm, acaoUm, itemUm, treinadorDois, log);
            }
        }

        aplicarEfeitosFimTurno(treinadorUm.getPokesal(), log);
        aplicarEfeitosFimTurno(treinadorDois.getPokesal(), log);

        return log;
    }

    private void executarAcao(final Treinador quemAge, final Acao acao, final Item item,
                              final Treinador alvo, final List<String> log) {
        final Pokesal pokesalQueAge = quemAge.getPokesal();
        if (!pokesalQueAge.estaVivo()) {
            return;
        }
        if (pokesalQueAge.falhaTurnoParalisia()) {
            log.add(pokesalQueAge.getNome() + " esta paralisado e nao conseguiu agir!");
            return;
        }

        if (acao == Acao.USAR_ITEM) {
            quemAge.getMochila().usarItem(item, pokesalQueAge);
            log.add(quemAge.getNome() + " usou " + item.getNome()
                    + " em " + pokesalQueAge.getNome() + ".");
            return;
        }

        final Pokesal pokesalAlvo = alvo.getPokesal();
        final int dano = CalculadoraDano.calcular(pokesalQueAge, pokesalAlvo, terreno);
        pokesalAlvo.receberDano(dano);
        log.add(pokesalQueAge.getNome() + " atacou " + pokesalAlvo.getNome()
                + " causando " + dano + " de dano. (HP restante: "
                + pokesalAlvo.getHpAtual() + "/" + pokesalAlvo.getHpMaximo() + ")");

        if (!pokesalAlvo.estaVivo()) {
            log.add(pokesalAlvo.getNome() + " foi derrotado!");
        }
    }

    private void aplicarEfeitosFimTurno(final Pokesal pokesal, final List<String> log) {
        if (!pokesal.estaVivo()) {
            return;
        }
        final int hpAntes = pokesal.getHpAtual();
        final StatusEfeito status = pokesal.getStatus();

        pokesal.aplicarEfeitoStatusFimTurno();
        if (status == StatusEfeito.QUEIMADO || status == StatusEfeito.ENVENENADO) {
            final int perdido = hpAntes - pokesal.getHpAtual();
            if (perdido > 0) {
                log.add(pokesal.getNome() + " sofreu " + perdido
                        + " de dano por estar " + status + ".");
            }
        }

        final int hpAntesTerreno = pokesal.getHpAtual();
        pokesal.aplicarEfeitoTerrenoFimTurno(terreno);
        final int recuperado = pokesal.getHpAtual() - hpAntesTerreno;
        if (recuperado > 0) {
            log.add(pokesal.getNome() + " recuperou " + recuperado
                    + " de HP com o efeito do Canteiro Central.");
        }
    }
}

