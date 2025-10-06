package org.lib.exception;

public class ValorNaoEncontradoException extends RuntimeException{

    public ValorNaoEncontradoException(){
        super("A árvore está vazia.");
    }

    public ValorNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
