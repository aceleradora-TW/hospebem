package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

    public String solicitacoesRelatorio(String status) {
         List<Solicitacao> solicitacaos = repository.findAllByStatus(status);

    String listaSolicitacoesRelatorio = solicitacaos
                .stream()
                .map(solicitacao -> String.join(",", solicitacao.getNome(),solicitacao.getStatus(), solicitacao.getGenero(),solicitacao.getDataNascimento().toString(),
                        solicitacao.getSituacao(), solicitacao.getOrgao(), solicitacao.getEndereco().getRua(),solicitacao.getEndereco().getNumero(),solicitacao.getEndereco().getCidade(),
                        solicitacao.getEndereco().getBairro(),solicitacao.getEndereco().getUf(), solicitacao.getCadeirante(), solicitacao.getTelefone()))
                .collect(joining("\n"));

        return listaSolicitacoesRelatorio;
    }

}
