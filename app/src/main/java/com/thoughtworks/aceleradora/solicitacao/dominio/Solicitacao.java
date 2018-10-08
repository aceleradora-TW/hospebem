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
    private String genero;
    private String situacao;
    private String generoAcomp1;
    private String generoAcomp2;

    public Solicitacao() {
    }

    public Solicitacao(String nome, Endereco endereco, String genero, String situacao, String generoAcomp1, String generoAcomp2) {
        this.nome = nome;
        this.endereco = endereco;
        this.genero = genero;
        this.situacao = situacao;
        this.generoAcomp1 = generoAcomp1;
        this.generoAcomp2 = generoAcomp2;

    }

    public String getGeneroAcomp2() {
        return generoAcomp2;
    }

    public void setGeneroAcomp2(String generoAcomp2) {
        this.generoAcomp2 = generoAcomp2;
    }

    public String getGeneroAcomp1() {
        return generoAcomp1;
    }

    public void setGeneroAcomp1(String generoAcomp1) {
        this.generoAcomp1 = generoAcomp1;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}