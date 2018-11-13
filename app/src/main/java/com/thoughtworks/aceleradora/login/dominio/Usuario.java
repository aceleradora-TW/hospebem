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

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    public Usuario(){

    }

    public Usuario(String nome, String senha, Cargo cargo){
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
