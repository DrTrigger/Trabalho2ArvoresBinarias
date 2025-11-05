package org.tpa;

import java.util.Scanner;

import org.lib.GeradorDeArvores;
import org.lib.IArvoreBinaria;
// ===> ATENÇÃO: deixe apenas UMA das opções abaixo descomentada, conforme seu projeto:
import org.lib.utils.ArvoreAVL;
import org.lib.utils.ComparadorAlunoPorMatricula;
import org.lib.utils.ComparadorAlunoPorNome;
// import org.lib.ArvoreAVL;
// import org.lib.ComparadorAlunoPorMatricula;
// import org.lib.ComparadorAlunoPorNome;

import org.tpa.model.Aluno;

public class AppCRUDArvoreAVL {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Índice padrão por matrícula (AVL organizada por matrícula)
        ComparadorAlunoPorMatricula compMat = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compNome = new ComparadorAlunoPorNome();

        IArvoreBinaria<Aluno> arv = new ArvoreAVL<>(compMat);
        GeradorDeArvores gerador = new GeradorDeArvores();

        System.out.println("==== Árvore AVL de Alunos (CRUD) ====");
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
                        arv = gerarDegeneradaAVL(gerador, compMat);  // reatribui!
                        break;

                    case 8:
                        arv = gerarBalanceadaAVL(gerador, compMat); // reatribui!
                        break;

                    case 9:
                        arv = limparAVL(compMat);                   // reatribui!
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
        System.out.println("==== MENU (AVL) ====");
        System.out.println("1) Adicionar aluno");
        System.out.println("2) Listar (caminharEmOrdem)");
        System.out.println("3) Pesquisar por matrícula");
        System.out.println("4) Pesquisar por nome (Comparator alternativo)");
        System.out.println("5) Remover por matrícula");
        System.out.println("6) Estatísticas (tamanho e altura)");
        System.out.println("7) Popular: gerar árvore DEGENERADA (n) [inserção em ordem]");
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

    // ---------- Popular/Limpar (RETORNAM nova árvore e o main reatribui) ----------

    private static IArvoreBinaria<Aluno> gerarDegeneradaAVL(GeradorDeArvores gerador,
                                                            ComparadorAlunoPorMatricula compMat) {
        int n = lerInt("Gerar DEGENERADA com n elementos: ");
        IArvoreBinaria<Aluno> nova = new ArvoreAVL<>(compMat);
        gerador.geraArvoreDegenerada(n, nova);
        System.out.println("Árvore AVL (degenerada como entrada) gerada com " + n + " elementos.");
        System.out.println("Altura (esperado ~ log2 n): " + nova.altura());
        return nova;
    }

    private static IArvoreBinaria<Aluno> gerarBalanceadaAVL(GeradorDeArvores gerador,
                                                            ComparadorAlunoPorMatricula compMat) {
        int n = lerInt("Gerar BALANCEADA com n elementos: ");
        IArvoreBinaria<Aluno> nova = new ArvoreAVL<>(compMat);
        gerador.geraArvorePerfeitamenteBalanceada(1, n, nova);
        System.out.println("Árvore AVL balanceada gerada com " + n + " elementos.");
        System.out.println("Altura (esperado ~ floor(log2 n)): " + nova.altura());
        return nova;
    }

    private static IArvoreBinaria<Aluno> limparAVL(ComparadorAlunoPorMatricula compMat) {
        System.out.println("Limpando árvore (AVL)...");
        return new ArvoreAVL<>(compMat);
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
        return "Aluno{nome='" + a.getNome() + "', matricula=" + a.getMatricula() + "}";
    }
}
