package com.thoughtworks.aceleradora.quarto.helpers;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class QuartoHelp{

    public QuartoHelp(){

    }

    public int hospedesPresentes(Solicitacao solicitacao){
        int numeroHospedes = 1;

        for(Acompanhante acompanhante: solicitacao.getAcompanhantes()){
            if(!acompanhante.getNome().isEmpty()){
                numeroHospedes++;
            }
        }
        return numeroHospedes;
    }

    public void limitaQuartos(Solicitacao solicitacao, Quarto quarto, SolicitacaoRepository solicitacaoRepository, QuartoRepository quartoRepository){
        int numeroHospedes = hospedesPresentes(solicitacao) == 3 ? 2 : hospedesPresentes(solicitacao);

        if(numeroHospedes <= quarto.getLeitosDisponiveis()) {
            solicitacao.setStatus(Solicitacao.Status.ACEITO.toString());
            solicitacao.setQuarto(quarto);
            quarto.getSolicitacoes().add(solicitacao);
            quarto.setLeitosDisponiveis(quarto.getLeitosDisponiveis() - numeroHospedes);

            if (quarto.getLeitosDisponiveis() <= 0) {
                quarto.setStatus(Quarto.Status.INDISPONIVEL.toString());
            }
        }
        solicitacaoRepository.save(solicitacao);
        quartoRepository.save(quarto);
    }

    public List<Solicitacao> ocupantes(List<Solicitacao> solicitacoes){
        List<Solicitacao> ocupantesQuarto = new ArrayList<>();
        Solicitacao solicitacao = null;
        for (Solicitacao s: solicitacoes){
            if (!s.equals(solicitacao)){
                ocupantesQuarto.add(s);
                solicitacao = s;
            }
        }
        return ocupantesQuarto;
    }
}