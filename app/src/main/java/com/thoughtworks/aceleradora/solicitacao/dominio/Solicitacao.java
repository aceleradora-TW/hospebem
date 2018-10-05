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
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String uf;
    private String nome_acompanhante1;
    private String nome_acompanhante2;
    private String group1;

    public String getGroup1() {
        return group1;
    }

    private Solicitacao() {
    }

    public String getNome_acompanhante1() {
        return nome_acompanhante1;
    }

    public String getNome_acompanhante2() {
        return nome_acompanhante2;
    }

    public Solicitacao(String nome, String rua, String numero, String cidade, String bairro, String uf, String nome_acompanhante1, String nome_acompanhante2) {
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.nome_acompanhante1 = nome_acompanhante1;
        this.nome_acompanhante2 = nome_acompanhante2;
        this.group1 = nome_acompanhante2;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() { return rua; }

    public String getNumero() { return numero; }

    public String getCidade() { return cidade; }

    public String getBairro() { return bairro; }

    public String getUf() {
        return uf; }
}
