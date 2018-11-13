package com.thoughtworks.aceleradora.login.dominio;

public enum Cargo {
    ADMINISTRADOR("Administrador"),
    ASSISTENTE_SOCIAL("Assistente Social");

    private String nome;

    Cargo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
