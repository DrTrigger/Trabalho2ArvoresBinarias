package org.tpa.model;

import java.util.Objects;

public class Aluno {
    private String nome;
    private Integer matricula;


    public Aluno(String nome, Integer matricula){
        this.nome = Objects.requireNonNull(nome);
        this.matricula = Objects.requireNonNull(matricula);
    }


    public void setMatricula(Integer matricula) {
        this.matricula = Objects.requireNonNull(matricula);;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Objects.requireNonNull(nome);;
    }
}
