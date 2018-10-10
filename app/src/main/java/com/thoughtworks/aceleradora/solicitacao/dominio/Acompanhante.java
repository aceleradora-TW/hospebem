package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    public Acompanhante() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero1() {
        return genero;
    }

    public void setGenero1(String genero1) {
        this.genero = genero1;
    }

    public Acompanhante(String nome, String genero1) {
        this.nome = nome;
        this.genero = genero1;
    }
}
