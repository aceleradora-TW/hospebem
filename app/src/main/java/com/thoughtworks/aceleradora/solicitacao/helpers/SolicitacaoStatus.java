package com.thoughtworks.aceleradora.solicitacao.helpers;

public enum SolicitacaoStatus{
    PENDENTE("Pendente"),
    ACEITO("Aceito"),
    NEGADO("Negado"),
    HOSPEDE("Hospede"),
    EX_HOSPEDE("Ex Hospede");

    public final String status;

    SolicitacaoStatus(final String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
