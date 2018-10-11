package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String situacao;

    @OneToOne(cascade=ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "solicitacao")
    private List<Acompanhante> acompanhantes;

    public Solicitacao() {
    }

    public Solicitacao(String nome, Endereco endereco, String genero, String situacao, List<Acompanhante> acompanhantes) {
        this.nome = nome;
        this.endereco = endereco;
        this.genero = genero;
        this.situacao = situacao;
        this.acompanhantes = acompanhantes;

    }

    public Solicitacao(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public List<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
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