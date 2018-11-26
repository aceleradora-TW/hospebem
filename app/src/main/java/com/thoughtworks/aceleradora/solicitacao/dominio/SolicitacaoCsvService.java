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
        List<Solicitacao> solicitacoes = repository.findAll();

        String listaSolicitacoesRelatorio = solicitacoes
                .stream()
                .filter(solicitacao -> solicitacao.getStatus().equalsIgnoreCase("Hospede") ||
                        solicitacao.getStatus().equalsIgnoreCase("Ex hospede") ||
                        solicitacao.getStatus().equals("Negado")
                )
                .map(solicitacao -> String.join(",", solicitacao.getNome(), solicitacao.getStatus(),
                        solicitacao.getGenero(), solicitacao.getDataNascimento().toString(),
                        solicitacao.getSituacao(), solicitacao.getOrgao(),
                        solicitacao.getEndereco().getRua(), solicitacao.getEndereco().getNumero(),
                        solicitacao.getEndereco().getCidade(),
                        solicitacao.getEndereco().getBairro(), solicitacao.getEndereco().getUf(),
                        solicitacao.getCadeirante(), solicitacao.getTelefone(),
                        solicitacao.getDataEntrada().toString(), solicitacao.getDataSaida().toString(),
                        getAcompanhantesToString(solicitacao))
                )

                .collect(joining("\n"));


        return String.join("\n",cabecalho(), listaSolicitacoesRelatorio);
    }

    private String cabecalho(){
        return Stream.of(
                "Nome", "Status", "Gênero", "Data de Nascimento", "Situacao", "Órgão", "Rua", "Número",
                "Cidade", "Bairro", "UF", "Cadeirante", "Telefone", "Data de Entrada", "Data de Saída",
                "Nome Acompanhante", "Gênero Acompanhante", "Data Nascimento Acompanhante",
                "Nome Acompanhante2", "Gênero Acompanhante2", "Data Nascimento Acompanhante2"
        ).collect(joining(","));

    }

    private String getAcompanhantesToString(Solicitacao solicitacao) {
        StringBuilder retorno = new StringBuilder(" ");
        if(solicitacao !=null) {

            for (Acompanhante acompanhante : solicitacao.getAcompanhantes()) {
                if (acompanhante != null) {

                    retorno
                            .append(acompanhante.getNome())
                            .append(",")
                            .append(acompanhante.getGenero())
                            .append(",")
                            .append(acompanhante.getDataNascimento().toString())
                            .append(",");
                }
            }
        }
        return retorno.toString();
    }

}
