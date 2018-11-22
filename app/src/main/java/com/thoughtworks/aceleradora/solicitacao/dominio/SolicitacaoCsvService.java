package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;

@Service
public class SolicitacaoCsvService {

    private SolicitacaoRepository repository;

    @Autowired
    public SolicitacaoCsvService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public String solicitacoesNegadas() {
         List<Solicitacao> negada = repository.findAll();

        String listaSolicitacoesNegadas = negada
                .stream()
                .map(solicitacao -> join(solicitacao.getSituacao()).join(solicitacao.getOrgao()))
                .collect(joining(","));


        return listaSolicitacoesNegadas;

    }

    public String solicitacoesAceitas() {
    return "";
    }
}
