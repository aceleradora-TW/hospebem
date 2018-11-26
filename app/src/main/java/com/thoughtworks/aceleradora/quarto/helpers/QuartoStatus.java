package com.thoughtworks.aceleradora.quarto.helpers;

public enum QuartoStatus{
    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível");

    private final String status;

    QuartoStatus(final String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
