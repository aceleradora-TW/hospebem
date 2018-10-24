package com.thoughtworks.aceleradora.login.dominio;

import javax.persistence.*;

@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String senha;

    private String tipo;

    public Usuario(){

    }

    public Usuario(String nome, String senha, String tipo){
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
}
