package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;


@Service
public class SolicitacaoCsvService {

    private SolicitacaoRepository repository;

    @Autowired
    public SolicitacaoCsvService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public String solicitacoesRelatorio() {
        List<Solicitacao> solicitacaos = repository.findAll();

        String listaSolicitacoesRelatorio = solicitacaos
                .stream()
                .filter(solicitacao -> {
                    return solicitacao.getStatus().equalsIgnoreCase("Hospede") || solicitacao.getStatus().equalsIgnoreCase("Ex hospede") || solicitacao.getStatus().equals("Negado");
                })
                .map(solicitacao -> String.join(",",solicitacao.getNome(),solicitacao.getStatus(),solicitacao.getGenero(),solicitacao.getDataNascimento().toString(),
                        solicitacao.getSituacao(),solicitacao.getOrgao(),solicitacao.getEndereco().getRua(),solicitacao.getEndereco().getNumero(),
                        solicitacao.getEndereco().getCidade(),solicitacao.getEndereco().getBairro(),solicitacao.getEndereco().getUf(),
                        solicitacao.getCadeirante(),solicitacao.getTelefone(), !solicitacao.getStatus().equals("Negado") ? solicitacao.getDataEntrada().toString() : ""))
                .collect(joining("\n"));

        return String.join("\n",cabecalho(), listaSolicitacoesRelatorio);
    }

    private String cabecalho(){
        return Stream.of(
                "Nome", "Status", "Genero", "Data de Nascimento", "Situacao", "Orgao", "Rua", "Numero",
                "Cidade", "Bairro", "UF", "Cadeirante", "Telefone"
        ).collect(joining(","));

    }

}
