package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "acompanhante")
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    public Acompanhante() {

    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Acompanhante(String nome, String genero, Solicitacao solicitacao) {
        this.nome = nome;
        this.genero = genero;
        this.solicitacao = solicitacao;
    }
}
