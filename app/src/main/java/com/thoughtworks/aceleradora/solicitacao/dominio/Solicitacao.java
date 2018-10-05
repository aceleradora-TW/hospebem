package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(cascade=ALL)
    private Endereco endereco;

    public Solicitacao() {
    }

    public Solicitacao(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }


}