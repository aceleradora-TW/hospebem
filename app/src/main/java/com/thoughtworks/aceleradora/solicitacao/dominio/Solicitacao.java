package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;

    private Solicitacao() {
    }

    public Solicitacao(String nome) {

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
