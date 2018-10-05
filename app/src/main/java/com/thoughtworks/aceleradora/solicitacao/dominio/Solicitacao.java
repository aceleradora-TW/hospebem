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
    private String sexo_paciente;
    private String sexo_acompanhante1;
    private String sexo_acompanhante2;
    private String situacao_transplante;

//    public Solicitacao() {
//    }

    public Solicitacao(String nome, String rua, String numero, String cidade, String bairro, String uf, String nome_acompanhante1, String nome_acompanhante2, String sexo_paciente, String sexo_acompanhante1, String sexo_acompanhante2, String situacao_transplante) {
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
        this.nome_acompanhante1 = nome_acompanhante1;
        this.nome_acompanhante2 = nome_acompanhante2;
        this.sexo_paciente = sexo_paciente;
        this.sexo_acompanhante1 = sexo_acompanhante1;
        this.sexo_acompanhante2 = sexo_acompanhante2;
        this.situacao_transplante = situacao_transplante;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getUf() {
        return uf;
    }

    public String getNome_acompanhante1() {
        return nome_acompanhante1;
    }

    public String getNome_acompanhante2() {
        return nome_acompanhante2;
    }

    public String getSexo_paciente() {
        return sexo_paciente;
    }

    public String getSexo_acompanhante1() {
        return sexo_acompanhante1;
    }

    public String getSexo_acompanhante2() {
        return sexo_acompanhante2;
    }

    public String getSituacao_transplante() {
        return situacao_transplante;
    }
}