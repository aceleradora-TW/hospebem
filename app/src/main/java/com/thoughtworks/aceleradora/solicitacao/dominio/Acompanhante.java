package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "acompanhantes")
public class Acompanhante {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    public Acompanhante() {

    }

    public Acompanhante(String nome, String genero, LocalDate dataNascimento, Solicitacao solicitacao) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.solicitacao = solicitacao;
    }

    public Long getId() {
        return id;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public String toString() {
        return "Acompanhante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", solicitacao=" + solicitacao +
                '}';
    }
}
