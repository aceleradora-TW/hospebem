package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

@Service
public class SolicitacaoCsvService {

    private static final String CABECALHO = Stream.of(
            "Nome", "Status", "Gênero", "Data de Nascimento", "Situacao", "Órgão", "Rua", "Número",
            "Cidade", "Bairro", "UF", "Cadeirante", "Telefone", "Data de Entrada", "Data de Saída",
            "Nome Acompanhante", "Gênero Acompanhante", "Data Nascimento Acompanhante",
            "Nome Acompanhante2", "Gênero Acompanhante2", "Data Nascimento Acompanhante2"
    ).collect(joining(","));

    private SolicitacaoRepository repository;

    @Autowired
    public SolicitacaoCsvService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public String solicitacoesRelatorio() {
        List<Solicitacao> solicitacoes = repository.findAll();

        String listaSolicitacoesRelatorio = solicitacoes
                .stream()
                .filter(solicitacao -> solicitacao.getStatus() == (Solicitacao.Status.HOSPEDE) ||
                        solicitacao.getStatus() == (Solicitacao.Status.EX_HOSPEDE) ||
                        solicitacao.getStatus() == (Solicitacao.Status.NEGADO)
                )
                .map(solicitacao -> juntar(
                        paciente(solicitacao),
                        endereco(solicitacao.getEndereco()),
                        acompanhantes(solicitacao))
                )
                .collect(joining("\n"));

        return String.join("\n", CABECALHO, listaSolicitacoesRelatorio);
    }

    private String paciente(Solicitacao solicitacao) {
        return juntar(
                solicitacao.getNome(), solicitacao.getStatus().toString(),
                solicitacao.getGenero(), solicitacao.getDataNascimento().toString(),
                solicitacao.getSituacao(), solicitacao.getOrgao(), solicitacao.getCadeirante(),
                solicitacao.getTelefone(), solicitacao.getDataEntrada().toString(),
                solicitacao.getDataSaida().toString()
        );
    }

    private String endereco(Endereco endereco) {
        return juntar(
                endereco.getRua(), endereco.getNumero(), endereco.getCidade(),
                endereco.getBairro(), endereco.getUf()
        );
    }

    private String acompanhantes(Solicitacao solicitacao) {
        return solicitacao
                .getAcompanhantes()
                .stream()
                .map(acompanhante -> juntar(
                        acompanhante.getNome(), acompanhante.getGenero(),
                        acompanhante.getDataNascimento().toString()))
                .collect(joining(","));
    }

    private String juntar(String... valores) {
        return Stream.of(valores).collect(joining(","));
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
