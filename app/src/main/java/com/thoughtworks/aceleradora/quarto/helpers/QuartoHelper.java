package com.thoughtworks.aceleradora.quarto.helpers;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuartoHelper{
    private static final int NUMERO_MAXIMO_HOSPEDES = 3;
    private QuartoRepository quartoRepo;

    public int hospedesPresentes(Solicitacao solicitacao){
        int numeroHospedes = 1;

        for (Acompanhante acompanhante: solicitacao.getAcompanhantes()) {
            if (!acompanhante.getNome().isEmpty()) {
                numeroHospedes++;
            }
        }
        return numeroHospedes;
    }

    public void limitaQuartos(Solicitacao solicitacao, Quarto quarto, SolicitacaoRepository solicitacaoRepository, QuartoRepository quartoRepository){
        int numeroHospedes = hospedesPresentes(solicitacao) == NUMERO_MAXIMO_HOSPEDES ? 2 : hospedesPresentes(solicitacao);

        if (numeroHospedes <= quarto.leitosDisponiveis()) {
            solicitacao.setStatus(Solicitacao.Status.ACEITO.toString());
            solicitacao.setQuarto(quarto);
            quarto.getSolicitacoes().add(solicitacao);
            quarto.leitosDisponiveis();

            if (quarto.leitosDisponiveis() <= 0) {
                quarto.setStatus(Quarto.Status.INDISPONIVEL.toString());
            }
        }
        solicitacaoRepository.save(solicitacao);
        quartoRepository.save(quarto);
    }

    public List<Solicitacao> ocupantes(List<Solicitacao> solicitacoes){
        Quarto quarto = new Quarto();
        List<Solicitacao> ocupantesQuarto = new ArrayList<>();
        Solicitacao solicitacao = null;
        for (Solicitacao s: solicitacoes){
            if (!s.equals(solicitacao)){
                ocupantesQuarto.add(s);
                solicitacao = s;
            }

            if (s.getStatus().equals(Solicitacao.Status.EX_HOSPEDE.toString())){
                ocupantesQuarto.remove(s);
            }
        }
        return ocupantesQuarto;
    }

    public Quarto aumentaLeitosDisponiveis(Quarto quarto) {
        quarto.setLeitosDisponiveis(quarto.leitosDisponiveis() + 2);
        quartoRepo.save(quarto);
        return quarto;
    }
}