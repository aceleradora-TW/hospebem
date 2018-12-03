package com.thoughtworks.aceleradora.quarto.helpers;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuartoHelper{
    private static final int NUMERO_MAXIMO_HOSPEDES = 3;
    private QuartoRepository quartoRepository;
    private SolicitacaoRepository solicitacaoRepository;

    public QuartoHelper() { }

    @Autowired
    public QuartoHelper(QuartoRepository quartoRepository, SolicitacaoRepository solicitacaoRepository){
        this.quartoRepository = quartoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public long hospedesPresentes(Solicitacao solicitacao){
        return solicitacao
                .getAcompanhantes()
                .stream()
                .count() + 1;
    }

    public void limitaQuartos(Solicitacao solicitacao, Quarto quarto){
        long numeroHospedes = hospedesPresentes(solicitacao) == NUMERO_MAXIMO_HOSPEDES ? 2 : hospedesPresentes(solicitacao);

        if (numeroHospedes <= quarto.leitosDisponiveis()) {
            solicitacao.setStatus(Solicitacao.Status.ACEITO);
            solicitacao.setQuarto(quarto);
            quarto.getSolicitacoes().add(solicitacao);
            quarto.leitosDisponiveis();

            if (quarto.leitosDisponiveis() <= 0) {
                quarto.setStatus(Quarto.Status.INDISPONIVEL);
            }
        }
        solicitacaoRepository.save(solicitacao);
        quartoRepository.save(quarto);
    }

    public Set<Solicitacao> ocupantes(List<Solicitacao> solicitacoes){
        return solicitacoes
                .stream()
                .filter(solicitacao -> solicitacao.getStatus() == Solicitacao.Status.EX_HOSPEDE)
                .peek(solicitacao -> solicitacoes.remove(solicitacao))
                .collect(Collectors.toSet());
    }

    public Quarto aumentaLeitosDisponiveis(Quarto quarto) {
        quarto.setQuantidadeLeitos(quarto.leitosDisponiveis() + 2);
        quartoRepository.save(quarto);
        return quarto;
    }
}