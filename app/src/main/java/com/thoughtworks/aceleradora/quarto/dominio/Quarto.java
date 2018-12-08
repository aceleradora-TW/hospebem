package com.thoughtworks.aceleradora.quarto.dominio;

import com.thoughtworks.aceleradora.quarto.helpers.QuartoHelper;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "quartos")
public class Quarto {

    public enum Status{
        DISPONIVEL, INDISPONIVEL;
    }

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long id;

    @Column(name = "nome_quarto")
    private String nomeQuarto;

    @Column (name = "status_quartos")
    @Enumerated(EnumType.STRING)
    private Status status;

    private String tipo;

    @Column (name = "quantidade_leitos")
    private int quantidadeLeitos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "quarto")
    private List<Solicitacao> solicitacoes;

    public int leitosDisponiveis() {
        QuartoHelper quartoHelper = new QuartoHelper();
        return quantidadeLeitos - (quartoHelper.ocupantes(solicitacoes).size() * 2);
    }

    public Quarto() {
    }

    public Quarto(String nomeQuarto,
                  Status status,
                  String tipo,
                  int quantidadeLeitos,

                  List<Solicitacao> solicitacoes)
    {
        this.nomeQuarto = nomeQuarto;
        this.status = status;
        this.tipo = tipo;
        this.quantidadeLeitos = quantidadeLeitos;

        this.solicitacoes = solicitacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomeQuarto() {
        return nomeQuarto;
    }

    public void setNomeQuarto(String nomeQuarto) {
        this.nomeQuarto = nomeQuarto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeLeitos() {
        return quantidadeLeitos;
    }

    public int setQuantidadeLeitos(int quantidadeLeitos) {
        this.quantidadeLeitos = quantidadeLeitos;
        return quantidadeLeitos;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public void setSolicitacoes(List<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }
}
