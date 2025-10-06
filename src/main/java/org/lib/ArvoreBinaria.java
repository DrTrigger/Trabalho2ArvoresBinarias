package org.lib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.lib.exception.ArvoreVaziaException;
import org.lib.exception.ValorNaoEncontradoException;

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
                // Caso exista, desce para a esquerda e continua a procura.
                atual = atual.getFilhoEsquerda();
            } else { // Se cmp >= 0, segue para a direita (duplicatas vão para a direita por convenção aqui).
                if (atual.getFilhoDireita() == null) { // Se não existe filho direito...
                    atual.setFilhoDireita(new No<>(novoValor)); // Insere aqui como novo filho direito.
                    return; // Inserção concluída.
                }
                // Caso exista, desce para a direita e continua a procura.
                atual = atual.getFilhoDireita();
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        Objects.requireNonNull(valor, "Valor a pesquisar não pode ser nulo.");
        if (raiz == null) throw new ArvoreVaziaException("A árvore está vazia, não é possível pesquisar valores");

        No<T> atual = raiz;

        while (atual != null){
            int cmp = comparador.compare(valor, atual.getValor());

            // Se = 0 Então encontrou
            if (cmp == 0) {
                return atual.getValor();
            }
            // Se < 0 então vai para esquerda
            else if (cmp < 0) {
                atual = atual.getFilhoEsquerda();
            }
            // Se > 0 então vai para direita
            else {
                atual = atual.getFilhoDireita();
            }
        }

        // Utilizei uma exception, mas, pode mudar futuramente
        throw new ValorNaoEncontradoException("Valor não encontrado");
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        Objects.requireNonNull(valor, "Valor a pesquisar não pode ser nulo.");
        if (raiz == null) throw new ArvoreVaziaException("A árvore está vazia, não é possível pesquisar valores");

        No<T> atual = raiz;

        while (atual != null){
            int cmp = comparador.compare(valor, atual.getValor());
            // Se = 0 Então encontrou
            if (cmp == 0) return atual.getValor();
                // Se < 0 então vai para esquerda
            else if (cmp < 0) atual = atual.getFilhoEsquerda();
                // Se > 0 então vai para direita
            else atual = atual.getFilhoDireita();
        }

        // Utilizei uma exception, mas, pode mudar futuramente
        throw new ValorNaoEncontradoException("Valor não encontrado");
    }

    @Override
    public T remover(T valor) {

        // TODO Esse método é muito mais complexo do que parece.
        //  vou precisar de ajuda para este
        return valor;
    }

    @Override
    public int altura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(No<T> no) {
        if (no == null) {
            return 0; // árvore vazia, altura 0
        }
        int alturaEsquerda = calcularAltura(no.getFilhoEsquerda());
        int alturaDireita = calcularAltura(no.getFilhoDireita());
        return 1 + Math.max(alturaEsquerda, alturaDireita);

        // TODO Esse método ficou elegante mas complexo
        //  Irá percorrer todas as folhas a partir da raiz recursivamente
        //  Com acesso a todas as folhas, irá subir camada a camada
        //  o nó de cima pegará o maior valor entre os filhos e somará 1
        //  ao final se terá a altura da árvore
    }

    @Override
    public int quantidadeNos() {
        return calcularQuantidade(raiz);
    }

    private int calcularQuantidade(No<T> no){
        if (no == null) {
            return 0; // Chegou ao fim ou está vazia
        }
        int Esquerda = calcularQuantidade(no.getFilhoEsquerda());
        int Direita = calcularQuantidade(no.getFilhoDireita());
        return Esquerda + Direita + 1;

        // TODO mesma lógica do código acima
    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder();
        percorrerEmOrdem(raiz, sb);
        return sb.toString();
    }

    private void percorrerEmOrdem(No<T> no, StringBuilder string){
        if (no == null) return;

        percorrerEmOrdem(no.getFilhoEsquerda(), string);
        string.append(no.getValor()).append(" "); // Adicionando espaço vazio para não terem elementos "grudados"
        percorrerEmOrdem(no.getFilhoDireita(), string);

        /*
        TODO
         Primeiro dar print nos elementos à esquerda do nó,
         o próprio nó
         e depois os elementos da direita
         */
    }
}