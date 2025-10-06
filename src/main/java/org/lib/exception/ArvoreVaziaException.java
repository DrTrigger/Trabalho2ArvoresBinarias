package org.lib.exception;

public class ArvoreVaziaException extends RuntimeException{

    public ArvoreVaziaException(){
        super("A árvore está vazia.");
    }

    public ArvoreVaziaException(String mensagem){
        super(mensagem);
    }
}
