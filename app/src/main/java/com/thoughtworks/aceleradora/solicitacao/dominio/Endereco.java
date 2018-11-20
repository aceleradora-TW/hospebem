package com.thoughtworks.aceleradora.solicitacao.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String uf;

    public Endereco(){
    }

    public Endereco(String rua, String numero, String cidade, String bairro, String uf) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.bairro = bairro;
        this.uf = uf;
    }

    public Long getId(){
        return id;
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

    public void setId(Long id){
        this.id = id;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}


