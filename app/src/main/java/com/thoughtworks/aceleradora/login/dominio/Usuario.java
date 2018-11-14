package com.thoughtworks.aceleradora.login.dominio;

import org.hibernate.annotations.Columns;

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
    @Column(name ="nome_assistente")
    private String nomeAssistente;
    private String email;
    private String telefone;
    @Column(name="hospital_referencia")
    private String hospitalReferencia;

    public Usuario(){

    }

    public Usuario(String nome, String senha, Cargo cargo, String nomeAssistente, String email, String telefone, String hospitalReferencia) {
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
        this.nomeAssistente = nomeAssistente;
        this.email = email;
        this.telefone = telefone;
        this.hospitalReferencia = hospitalReferencia;
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

    public String getNomeAssistente() {
        return nomeAssistente;
    }

    public void setNomeAssistente(String nomeAssistente) {
        this.nomeAssistente = nomeAssistente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHospitalReferencia() {
        return hospitalReferencia;
    }

    public void setHospitalReferencia(String hospitalReferencia) {
        this.hospitalReferencia = hospitalReferencia;
    }
}
