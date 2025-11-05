package org.lib.utils;

import org.lib.ArvoreBinaria;
import org.lib.No;

import java.util.Comparator;
import java.util.Objects;

/**
 * AVL genérica que herda da sua ArvoreBinaria<T>.
 * - Sobrescreve apenas 'adicionar', mantendo o restante da API (pesquisar, caminhamentos etc.).
 * - Usa um nó especializado (NoAVL) que estende No<T> e armazena 'altura'.
 * - Duplicatas seguem a convenção da BST: vão para a direita.
 */
public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    /** Nó AVL com campo de altura. Null => -1; folha => 0. */
    private static class NoAVL<U> extends No<U> {
        private int altura;
        NoAVL(U valor) {
            super(valor);
            this.altura = 0;
        }
    }

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    // ====================== INSERÇÃO (override) ======================

    @Override
    public void adicionar(T novoValor) {
        Objects.requireNonNull(novoValor, "Valor a inserir não pode ser nulo.");
        // A raiz (protegida na classe-mãe) é do tipo No<T>, mas aqui vamos guardá-la como NoAVL<T>.
        raiz = inserir(asAVL(raiz), novoValor);
    }

    /** Inserção recursiva com rebalanceamento AVL. */
    private NoAVL<T> inserir(NoAVL<T> no, T valor) {
        if (no == null) {
            return new NoAVL<>(valor);
        }

        int cmp = comparador.compare(valor, no.getValor());
        if (cmp < 0) {
            no.setFilhoEsquerda(inserir(asAVL(no.getFilhoEsquerda()), valor));
        } else {
            // cmp == 0 (duplicata) também vai para a direita por convenção
            no.setFilhoDireita(inserir(asAVL(no.getFilhoDireita()), valor));
        }
        //call<11, 30> : r = ?

        atualizarAltura(no);
        return balancear(no);
    }

    // ====================== AVL: ALTURA / BALANCEAMENTO ======================

    /** Altura do nó para fins de AVL (NoAVL.altura). Null => -1. */
    private int alturaDe(No<T> n) {
        if (n == null) return -1;
        return asAVL(n).altura;
    }

    /** Recalcula a altura de um nó com base nas alturas dos filhos. */
    //passa<20>
    private void atualizarAltura(NoAVL<T> n) {
        int he = alturaDe(n.getFilhoEsquerda()); //0
        int hd = alturaDe(n.getFilhoDireita());  //0
        n.altura = 1 + Math.max(he, hd); //0
    }

    /** Fator de balanceamento = altura(esq) - altura(dir). */
    private int fatorBalanceamento(NoAVL<T> n) {
        return alturaDe(n.getFilhoEsquerda()) - alturaDe(n.getFilhoDireita());
    }
    //-1 - 1 = -2
    /**
     * Reequilibra uma subárvore em 'n' se necessário:
     * - fb > 1  → pesado à esquerda (LL ou LR)
     * - fb < -1 → pesado à direita (RR ou RL)
     * Decidimos entre simples/dupla comparando alturas dos netos (sem depender do valor recém inserido).
     */
    //stack <11>
    private NoAVL<T> balancear(NoAVL<T> n) {
        int fb = fatorBalanceamento(n); //-2

        // Pesado à esquerda
        if (fb > 1) {
            NoAVL<T> esq = asAVL(n.getFilhoEsquerda());
            // LL (altura(esq.esq) >= altura(esq.dir)) → rotação simples à direita
            if (alturaDe(esq.getFilhoEsquerda()) >= alturaDe(esq.getFilhoDireita())) {
                return rotacaoDireita(n);
            }
            // LR → rotação à esquerda no filho esquerdo, depois à direita em n
            n.setFilhoEsquerda(rotacaoEsquerda(esq));
            return rotacaoDireita(n);
        }

        // Pesado à direita
        //-2 < -1 : true
        if (fb < -1) {
            NoAVL<T> dir = asAVL(n.getFilhoDireita()); // ref 20
            // RR (altura(dir.dir) >= altura(dir.esq)) → rotação simples à esquerda

            //0 >= -1 //true
            if (alturaDe(dir.getFilhoDireita()) >= alturaDe(dir.getFilhoEsquerda())) {
                return rotacaoEsquerda(n);
            }
            // RL → rotação à direita no filho direito, depois à esquerda em n
            n.setFilhoDireita(rotacaoDireita(dir));
            return rotacaoEsquerda(n);
        }

        // Já balanceado
        return n;
    }

    // ====================== ROTAÇÕES ======================

    private NoAVL<T> rotacaoDireita(NoAVL<T> y) {
        NoAVL<T> x  = asAVL(y.getFilhoEsquerda()); //null
        NoAVL<T> T2 = asAVL(x.getFilhoDireita());  //null

        // Rotaciona
        x.setFilhoDireita(y);
        y.setFilhoEsquerda(T2);

        // Atualiza alturas (primeiro o inferior)
        atualizarAltura(y);
        atualizarAltura(x);

        return x; // nova raiz da subárvore
    }
    //no<11>
    private NoAVL<T> rotacaoEsquerda(NoAVL<T> x) {
        NoAVL<T> y  = asAVL(x.getFilhoDireita()); //n<20>
        NoAVL<T> T2 = asAVL(y.getFilhoEsquerda()); //n<20, fe = null>

        // Rotaciona
        y.setFilhoEsquerda(x); //possui referencia null
        x.setFilhoDireita(T2); //possui referencia 20

        // Atualiza alturas
        atualizarAltura(x); //
        atualizarAltura(y); //

        return y; // nova raiz da subárvore
    }

    // ====================== HELPERS ======================

    /** Cast centralizado: toda subárvore dentro da AVL é de NoAVL<T>. */
    @SuppressWarnings("unchecked")
    private NoAVL<T> asAVL(No<T> n) {
        return (NoAVL<T>) n;
    }

    // Remoção na AVL é opcional nesta etapa.
    // Se decidir implementar, sobrescreva 'remover(T)' e rebalanceie ao subir a recursão.
}
