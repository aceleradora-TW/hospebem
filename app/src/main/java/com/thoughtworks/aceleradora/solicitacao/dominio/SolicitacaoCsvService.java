package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.stereotype.Service;

@Service
public class SolicitacaoCsvService {

    private SolicitacaoRepository repository;

    public SolicitacaoCsvService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public String solicitacoesNegadas() {
        return "";
    }

    public String solicitacoesAceitas() {
        return "";
    }
}
