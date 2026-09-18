import batalha.Acao;
import batalha.Batalha;
import item.Antidote;
import item.Item;
import item.Mochila;
import item.Potion;
import item.SuperPotion;
import Models.BulbaSal;
import Models.CharSal;
import Models.ChikoSal;
import Models.CyndaSal;
import Models.Pokesal;
import Models.SquirtSal;
import Models.Terreno;
import Models.TotoSal;
import treinador.Treinador;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=== Torneio PokeSal - Estacionamento da UCSal Pituacu ===");

        final Treinador treinadorUm = criarTreinador(scanner, "Treinador 1");
        final Treinador treinadorDois = criarTreinador(scanner, "Treinador 2");
        final Terreno terreno = escolherTerreno(scanner);

        final Batalha batalha = new Batalha(treinadorUm, treinadorDois, terreno);

        System.out.println();
        System.out.println("A batalha vai comecar em: " + terreno);
        System.out.println(treinadorUm.getNome() + ": " + treinadorUm.getPokesal());
        System.out.println(treinadorDois.getNome() + ": " + treinadorDois.getPokesal());

        while (!batalha.batalhaTerminou()) {
            System.out.println();
            final Acao acaoUm = perguntarAcao(scanner, treinadorUm);
            final Item itemUm = acaoUm == Acao.USAR_ITEM
                    ? escolherItem(scanner, treinadorUm) : null;

            final Acao acaoDois = perguntarAcao(scanner, treinadorDois);
            final Item itemDois = acaoDois == Acao.USAR_ITEM
                    ? escolherItem(scanner, treinadorDois) : null;

            final List<String> log = batalha.executarTurno(acaoUm, itemUm, acaoDois, itemDois);
            log.forEach(System.out::println);
        }

        final Treinador vencedor = batalha.getVencedor();
        System.out.println();
        if (vencedor != null) {
            System.out.println(vencedor.getNome() + " venceu a batalha com "
                    + vencedor.getPokesal().getNome() + "!");
        } else {
            System.out.println("A batalha terminou em empate.");
        }

        scanner.close();
    }

    private static Treinador criarTreinador(final Scanner scanner, final String rotulo) {
        System.out.println();
        System.out.println("--- " + rotulo + " ---");
        System.out.print("Nome do treinador: ");
        final String nome = scanner.nextLine();

        final Pokesal pokesal = escolherPokesal(scanner);
        final Treinador treinador = new Treinador(nome, pokesal);

        treinador.getMochila().adicionarItem(new Potion());
        treinador.getMochila().adicionarItem(new SuperPotion());
        treinador.getMochila().adicionarItem(new Antidote());

        return treinador;
    }

    private static Pokesal escolherPokesal(final Scanner scanner) {
        System.out.println("Escolha o seu Pokesal inicial:");
        System.out.println("1 - BulbaSal (Planta)   4 - ChikoSal (Planta)");
        System.out.println("2 - CharSal  (Fogo)     5 - CyndaSal (Fogo)");
        System.out.println("3 - SquirtSal (Agua)    6 - TotoSal  (Agua)");
        System.out.print("Opcao: ");

        final int opcao = lerInteiro(scanner, 1, 6);
        switch (opcao) {
            case 1:
                return new BulbaSal();
            case 2:
                return new CharSal();
            case 3:
                return new SquirtSal();
            case 4:
                return new ChikoSal();
            case 5:
                return new CyndaSal();
            default:
                return new TotoSal();
        }
    }

    private static Terreno escolherTerreno(final Scanner scanner) {
        System.out.println();
        System.out.println("Escolha o terreno do Estacionamento da UCSal:");
        System.out.println("1 - Asfalto Quente (Dia)");
        System.out.println("2 - Poca de Chuva / Piso Escorregadio");
        System.out.println("3 - Canteiro Central");
        System.out.println("4 - Nenhum efeito de terreno");
        System.out.print("Opcao: ");

        final int opcao = lerInteiro(scanner, 1, 4);
        switch (opcao) {
            case 1:
                return Terreno.ASFALTO_QUENTE;
            case 2:
                return Terreno.POCA_DE_CHUVA;
            case 3:
                return Terreno.CANTEIRO_CENTRAL;
            default:
                return Terreno.NENHUM;
        }
    }

    private static Acao perguntarAcao(final Scanner scanner, final Treinador treinador) {
        System.out.println();
        System.out.println(treinador.getNome() + " (" + treinador.getPokesal().getNome()
                + ", HP " + treinador.getPokesal().getHpAtual() + "/"
                + treinador.getPokesal().getHpMaximo() + ") - escolha uma acao:");
        System.out.println("1 - Atacar");
        System.out.println("2 - Usar item ("
                + treinador.getMochila().getItensUsados() + "/"
                + Mochila.LIMITE_DE_ITENS_POR_BATALHA + " ja usados)");
        System.out.print("Opcao: ");

        final int opcao = lerInteiro(scanner, 1, 2);
        return opcao == 1 ? Acao.ATACAR : Acao.USAR_ITEM;
    }

    private static Item escolherItem(final Scanner scanner, final Treinador treinador) {
        final List<Item> itens = treinador.getMochila().getItensDisponiveis();
        System.out.println("Itens disponiveis:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + " - " + itens.get(i).getNome());
        }
        System.out.print("Opcao: ");

        final int opcao = lerInteiro(scanner, 1, itens.size());
        return itens.get(opcao - 1);
    }

    private static int lerInteiro(final Scanner scanner, final int minimo, final int maximo) {
        while (true) {
            final String entrada = scanner.nextLine().trim();
            try {
                final int valor = Integer.parseInt(entrada);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (final NumberFormatException excecao) {                assert excecao != null;
            }
            System.out.print("Opcao invalida, tente novamente (" + minimo + "-" + maximo + "): ");
        }
    }
}
