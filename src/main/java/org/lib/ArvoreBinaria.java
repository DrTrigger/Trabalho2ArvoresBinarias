package org.lib; // Define o pacote onde esta classe está localizada (organiza o código por módulos).

import java.util.*; // Importa todas as classes do pacote java.util (Deque, ArrayDeque, Comparator, etc.).
import java.util.Objects; // Importa explicitamente a classe Objects (redundante com o wildcard, mas ok).

// Declara uma classe genérica ArvoreBinaria que implementa a interface IArvoreBinaria<T>.
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;      // Referência para o nó raiz da árvore; começa vazia (null).
    protected Comparator<T> comparador; // Comparator usado para definir a ordem dos elementos na árvore.

    // Construtor que exige um Comparator para definir a ordenação dos elementos de T.
    public ArvoreBinaria(Comparator<T> comp) {
        // Garante que o comparador não é nulo; lança NullPointerException com mensagem se for.
        this.comparador = Objects.requireNonNull(comp, "Comparator não pode ser nulo.");
    }

    @Override
    public void adicionar(T novoValor) {
        // Valida que o valor a inserir não é nulo.
        Objects.requireNonNull(novoValor, "Valor a inserir não pode ser nulo.");
        // Se a árvore está vazia, o novo nó vira a raiz e finaliza.
        if (raiz == null) {
            raiz = new No<>(novoValor); // Cria um nó folha com o valor.
            return;                     // Encerra pois já inseriu na raiz.
        }
        // Começa a partir da raiz para achar a posição correta.
        No<T> atual = raiz;
        // Loop infinito controlado por retornos internos quando inserir.
        while (true) {
            // Compara o novo valor com o valor do nó atual usando o comparador da árvore.
            int cmp = comparador.compare(novoValor, atual.getValor());
            if (cmp < 0) { // Se novoValor é "menor" segundo o comparador, deve ir para a subárvore esquerda.
                if (atual.getFilhoEsquerda() == null) { // Se não existe filho esquerdo...
                    atual.setFilhoEsquerda(new No<>(novoValor)); // Insere aqui como novo filho esquerdo.
                    return; // Inserção concluída.
                }
                // Caso exista, desce para a esquerda e continua a procurar.
                atual = atual.getFilhoEsquerda();
            } else { // Se cmp >= 0, segue para a direita (duplicatas vão para a direita por convenção aqui).
                if (atual.getFilhoDireita() == null) { // Se não existe filho direito...
                    atual.setFilhoDireita(new No<>(novoValor)); // Insere aqui como novo filho direito.
                    return; // Inserção concluída.
                }
                // Caso exista, desce para a direita e continua a procurar.
                atual = atual.getFilhoDireita();
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        // Valida que o valor de busca não é nulo.
        Objects.requireNonNull(valor, "Valor de busca não pode ser nulo.");
        // Começa a busca a partir da raiz.
        No<T> atual = raiz;
        // Enquanto não chegar ao fim (null), tenta encontrar o valor.
        while (atual != null) {
            // Compara a chave buscada com o valor do nó atual usando o comparador da ÁRVORE (o índice).
            int cmp = comparador.compare(valor, atual.getValor());
            if (cmp == 0) return atual.getValor(); // Achou o elemento; retorna o valor armazenado.
            // Se chave < atual, vai para a esquerda; senão, vai para a direita (regra da BST).
            atual = (cmp < 0) ? atual.getFilhoEsquerda() : atual.getFilhoDireita();
        }
        // Se saiu do loop, não encontrou o valor; retorna null.
        return null;
    }

    @Override
    @SuppressWarnings({"rawtypes","unchecked"}) // Suprime avisos por usar Comparator "raw" recebido externamente.
    public T pesquisar(T valor, Comparator outroComparador) {
        // Valida que o comparator alternativo foi fornecido.
        Objects.requireNonNull(outroComparador, "Comparator da busca não pode ser nulo.");
        // Valida que o valor de busca não é nulo.
        Objects.requireNonNull(valor, "Valor de busca não pode ser nulo.");
        // Se a árvore está vazia, não há o que procurar.
        if (raiz == null) return null;

        // Faremos uma varredura completa (BFS) porque a árvore foi indexada por OUTRO comparador.
        Deque<No<T>> fila = new ArrayDeque<>(); // Fila para percorrer em nível (largura).
        fila.add(raiz); // Começa pela raiz.
        while (!fila.isEmpty()) { // Enquanto houver nós a visitar...
            No<T> n = fila.removeFirst(); // Retira o primeiro da fila (próximo nível da árvore).
            // Compara usando o comparador ALTERNATIVO fornecido apenas para esta busca.
            if (outroComparador.compare(valor, n.getValor()) == 0) {
                return n.getValor(); // Achou um elemento que "equivale" segundo o comparador alternativo.
            }
            // Enfileira filhos (se existirem) para continuar o percurso em nível.
            if (n.getFilhoEsquerda() != null) fila.addLast(n.getFilhoEsquerda());
            if (n.getFilhoDireita() != null) fila.addLast(n.getFilhoDireita());
        }
        // Se terminou a BFS e nada bateu, retorna null (não encontrado).
        return null;
    }

    @Override
    public T remover(T valor) {
        return null;
    }


    @Override
    public int altura() {
        // Retorna a altura da árvore inteira chamando a versão recursiva na raiz.
        return altura(raiz);
    }

    // Calcula a altura de forma recursiva: nó nulo = -1; nó folha = 0; demais = 1 + max(alturas dos filhos).
    private int altura(No<T> n) {
        if (n == null) return -1; // Convenção da especificação: árvore só com raiz tem altura 0, logo null => -1.
        int he = altura(n.getFilhoEsquerda()); // Altura do filho esquerdo.
        int hd = altura(n.getFilhoDireita());  // Altura do filho direito.
        return 1 + Math.max(he, hd);           // Altura do nó atual é 1 + a maior altura entre os filhos.
    }

    @Override
    public int quantidadeNos() {
        // Retorna o total de nós na árvore chamando o método recursivo a partir da raiz.
        return contar(raiz);
    }

    @Override
    public String caminharEmNivel() {
        return "";
    }

    // Conta nós recursivamente: nulo conta 0; caso contrário 1 + esquerda + direita.
    private int contar(No<T> n) {
        if (n == null) return 0; // Base: subárvore vazia tem zero nós.
        return 1 + contar(n.getFilhoEsquerda()) + contar(n.getFilhoDireita()); // Soma 1 (nó atual) com as contagens dos filhos.
    }



    @Override
    public String caminharEmOrdem() {
        // Constrói uma string com os valores em ordem (in-order), separados por " \n ", dentro de colchetes.
        StringBuilder sb = new StringBuilder("["); // Inicia com "[".
        boolean[] first = { true }; // Usa array de 1 posição para passar "por referência" o estado de primeiro elemento.
        inOrder(raiz, sb, first);   // Chama o percurso recursivo em-ordem iniciando na raiz.
        sb.append("]");             // Fecha com "]".
        return sb.toString();       // Retorna a string completa.
    }

    // Percurso em ordem (esquerda, nó, direita): imprime ordenado conforme o comparador da árvore.
    private void inOrder(No<T> n, StringBuilder sb, boolean[] first) {
        if (n == null) return; // Caso base: subárvore vazia não adiciona nada.
        inOrder(n.getFilhoEsquerda(), sb, first); // Visita recursivamente a subárvore esquerda.
        if (!first[0]) sb.append(" \n "); else first[0] = false; // Coloca separador após o primeiro elemento.
        sb.append(String.valueOf(n.getValor())); // Adiciona o valor do nó atual.
        inOrder(n.getFilhoDireita(), sb, first); // Visita recursivamente a subárvore direita.
    }
}
