package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String situacao;

    private String telefone;

    private String cadeirante;

    private float peso;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToOne(cascade=ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "solicitacao")
    private List<Acompanhante> acompanhantes;

    public Solicitacao() {
    }

    public Solicitacao(String nome, String genero, String situacao, String telefone, String cadeirante, float peso, LocalDate dataNascimento, Endereco endereco, List<Acompanhante> acompanhantes) {
        this.nome = nome;
        this.genero = genero;
        this.situacao = situacao;
        this.telefone = telefone;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.acompanhantes = acompanhantes;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCadeirante() {
        return cadeirante;
    }

    public void setCadeirante(String cadeirante) {
        this.cadeirante = cadeirante;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }


    public void setAcompanhantes(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", situacao='" + situacao + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cadeirante='" + cadeirante + '\'' +
                ", peso=" + peso +
                ", dataNascimento=" + dataNascimento +
                ", endereco=" + endereco +
                ", acompanhantes=" + acompanhantes +
                '}';
    }
}