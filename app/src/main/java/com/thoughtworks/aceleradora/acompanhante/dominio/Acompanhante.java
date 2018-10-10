package com.thoughtworks.aceleradora.acompanhante.dominio;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acompanhantes")
public class Acompanhante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    public Acompanhante() {}

    public Acompanhante(String nome, String genero, Solicitacao solicitacao) {
        this.nome = nome;
        this.genero = genero;
        this.solicitacao = solicitacao;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
