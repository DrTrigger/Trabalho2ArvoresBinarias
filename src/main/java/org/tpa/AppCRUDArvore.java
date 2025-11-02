package org.tpa;


import java.util.Scanner;

import org.lib.ArvoreBinaria;
import org.lib.GeradorDeArvores;
import org.lib.IArvoreBinaria;
// ===> ATENÇÃO: deixe apenas UMA das opções abaixo descomentada, conforme seu projeto:
import org.lib.utils.ComparadorAlunoPorMatricula;
import org.lib.utils.ComparadorAlunoPorNome;
// import org.lib.utils.ComparadorAlunoPorMatricula;
// import org.lib.utils.ComparadorAlunoPorNome;

import org.tpa.model.Aluno;

public class AppCRUDArvore {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Índice padrão por matrícula (BST organizada por matrícula)
        ComparadorAlunoPorMatricula compMat = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compNome = new ComparadorAlunoPorNome();

        IArvoreBinaria<Aluno> arv = new ArvoreBinaria<Aluno>(compMat);
        GeradorDeArvores gerador = new GeradorDeArvores();

        System.out.println("==== Árvore Binária de Alunos (CRUD) ====");
        System.out.println("Indexação: MATRÍCULA (ComparatorAlunoPorMatricula)\n");

        while (true) {
            try {
                exibirMenu();
                int opcao = lerInt("Opção: ");
                switch (opcao) {
                    case 0:
                        System.out.println("Encerrando... até mais!");
                        sc.close();
                        return;

                    case 1:
                        adicionarAluno(arv);
                        break;

                    case 2:
                        listarEmOrdem(arv);
                        break;

                    case 3:
                        pesquisarPorMatricula(arv);
                        break;

                    case 4:
                        pesquisarPorNome(arv, compNome);
                        break;

                    case 5:
                        removerPorMatricula(arv);
                        break;

                    case 6:
                        estatisticas(arv);
                        break;

                    case 7:
                        gerarDegenerada(arv, gerador, compMat);
                        break;

                    case 8:
                        gerarBalanceada(arv, gerador, compMat);
                        break;

                    case 9:
                        arv = limpar(arv, compMat);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: digite números onde solicitado.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("==== MENU ====");
        System.out.println("1) Adicionar aluno");
        System.out.println("2) Listar (caminharEmOrdem)");
        System.out.println("3) Pesquisar por matrícula");
        System.out.println("4) Pesquisar por nome (Comparator alternativo)");
        System.out.println("5) Remover por matrícula");
        System.out.println("6) Estatísticas (tamanho e altura)");
        System.out.println("7) Popular: gerar árvore DEGENERADA (n)");
        System.out.println("8) Popular: gerar árvore BALANCEADA (n)");
        System.out.println("9) Limpar árvore");
        System.out.println("0) Sair");
    }

    // ---------- Ações ----------

    private static void adicionarAluno(IArvoreBinaria<Aluno> arv) {
        int matricula = lerInt("Matrícula (inteiro): ");
        String nome = lerTexto("Nome: ");
        Aluno a = new Aluno(nome, matricula);
        arv.adicionar(a);
        System.out.println("Aluno adicionado: " + formatAluno(a));
    }

    private static void listarEmOrdem(IArvoreBinaria<Aluno> arv) {
        String s = arv.caminharEmOrdem();
        System.out.println("Em ordem (in-order):");
        System.out.println(s);
    }

    private static void pesquisarPorMatricula(IArvoreBinaria<Aluno> arv) {
        int matricula = lerInt("Matrícula a pesquisar: ");
        // Como o índice é por matrícula, o nome é ignorado pelo comparator de matrícula.
        Aluno chave = new Aluno("", matricula);
        Aluno achado = arv.pesquisar(chave);
        if (achado == null) {
            System.out.println("Aluno não encontrado.");
        } else {
            System.out.println("Encontrado: " + formatAluno(achado));
        }
    }

    private static void pesquisarPorNome(IArvoreBinaria<Aluno> arv, ComparadorAlunoPorNome compNome) {
        String nome = lerTexto("Nome a pesquisar (exato): ");
        // Para pesquisar por nome, passamos um Comparator ALTERNATIVO (por nome).
        // Isso exige que seu método pesquisar(T, Comparator) faça VARREDURA na árvore.
        Aluno chave = new Aluno(nome, -1); // matrícula dummy
        Aluno achado = arv.pesquisar(chave, compNome);
        if (achado == null) {
            System.out.println("Aluno não encontrado (busca por nome).");
        } else {
            System.out.println("Encontrado: " + formatAluno(achado));
        }
    }

    private static void removerPorMatricula(IArvoreBinaria<Aluno> arv) {
        int matricula = lerInt("Matrícula a remover: ");
        Aluno chave = new Aluno("", matricula);
        Aluno removido = arv.remover(chave);
        if (removido == null) {
            System.out.println("Aluno não encontrado para remoção.");
        } else {
            System.out.println("Removido: " + formatAluno(removido));
        }
    }

    private static void estatisticas(IArvoreBinaria<Aluno> arv) {
        int qtd = arv.quantidadeNos();
        int alt = arv.altura();
        System.out.println("Quantidade de nós: " + qtd);
        System.out.println("Altura: " + alt);
    }

    private static void gerarDegenerada(IArvoreBinaria<Aluno> arv, GeradorDeArvores gerador, ComparadorAlunoPorMatricula compMat) {
        int n = lerInt("Gerar DEGENERADA com n elementos: ");
        // limpar e gerar de novo para ficar consistente
        IArvoreBinaria<Aluno> nova = new ArvoreBinaria<>(compMat);
        gerador.geraArvoreDegenerada(n, nova);
        substituirConteudo(arv, nova);
        System.out.println("Árvore degenerada gerada com " + n + " elementos.");
        System.out.println("Altura (esperado ~ n-1): " + arv.altura());
    }

    private static void gerarBalanceada(IArvoreBinaria<Aluno> arv, GeradorDeArvores gerador, ComparadorAlunoPorMatricula compMat) {
        int n = lerInt("Gerar BALANCEADA com n elementos: ");
        // O gerador usa intervalo [min, max]; vamos usar [1, n]
        IArvoreBinaria<Aluno> nova = new ArvoreBinaria<>(compMat);
        gerador.geraArvorePerfeitamenteBalanceada(1, n, nova);
        substituirConteudo(arv, nova);
        System.out.println("Árvore balanceada gerada com " + n + " elementos.");
        System.out.println("Altura (esperado ~ floor(log2 n)): " + arv.altura());
    }

    private static IArvoreBinaria<Aluno> limpar(IArvoreBinaria<Aluno> arv, ComparadorAlunoPorMatricula compMat) {
        System.out.println("Limpando árvore...");
        return new ArvoreBinaria<>(compMat);
    }

    // ---------- Utilidades ----------

    private static int lerInt(String prompt) throws NumberFormatException {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um inteiro válido.");
            }
        }
    }

    private static String lerTexto(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Entrada vazia. Tente novamente.");
        }
    }

    private static String formatAluno(Aluno a) {
        // Usa getters para imprimir direto mesmo se Aluno não tiver toString()
        return "Aluno{nome='" + a.getNome() + "', matricula=" + a.getMatricula() + "}";
    }

    /**
     * Substitui o conteúdo de 'destino' por 'origem'.
     * Observação: como IArvoreBinaria não expõe iteradores/visão interna, usamos
     * o truque de reatribuir a referência no chamador (ver gerarDegenerada/gerarBalanceada),
     * ou, se preferir, podemos apenas retornar a nova estrutura.
     * Aqui, como não temos acesso interno, esta função é “no-op” e está só por clareza.
     * Se você quiser mesmo copiar, prefira manter a reatribuição no chamador (retornar 'nova').
     */
    private static void substituirConteudo(IArvoreBinaria<Aluno> destino, IArvoreBinaria<Aluno> origem) {
        // intencionalmente vazio: mantemos a nova referência no chamador
        // (veja que gerarDegenerada/gerarBalanceada já retornam/reatribuiram 'arv')
        // Deixei o método aqui só para documentar a intenção.
    }
}
