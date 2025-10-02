package org.lib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author victoriocarvalho
 */
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
                // Caso exista, desce para a esquerda e continua procurando.
                atual = atual.getFilhoEsquerda();
            } else { // Se cmp >= 0, segue para a direita (duplicatas vão para a direita por convenção aqui).
                if (atual.getFilhoDireita() == null) { // Se não existe filho direito...
                    atual.setFilhoDireita(new No<>(novoValor)); // Insere aqui como novo filho direito.
                    return; // Inserção concluída.
                }
                // Caso exista, desce para a direita e continua procurando.
                atual = atual.getFilhoDireita();
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public int quantidadeNos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmOrdem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}