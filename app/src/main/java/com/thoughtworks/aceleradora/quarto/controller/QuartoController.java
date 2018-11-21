package com.thoughtworks.aceleradora.quarto.controller;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/quarto")
public class QuartoController {
    private QuartoRepository quartoRepository;
    private SolicitacaoRepository solicitacaoRepository;

    public QuartoController() {
    }

    @Autowired
    public QuartoController(QuartoRepository repositorio, SolicitacaoRepository solicitacaoRepository) {
        this.quartoRepository = repositorio;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @GetMapping("/{idSolicitacao}/listaQuartos")
    public String listaQuartos(Model model, @PathVariable Long idSolicitacao) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(idSolicitacao);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();

            model.addAttribute("numeroHospedes", hospedesPresentes(solicitacao));
            model.addAttribute("solicitacao", solicitacao);
            model.addAttribute("listaQuartos", quartoRepository.findAll());

            return "quarto/listagens/listaQuartos";
        }
        return "404";
    }

    @GetMapping("/{idSolicitacao}/quarto/{idQuarto}")
    public String quarto (Model model, @PathVariable Long idSolicitacao, @PathVariable Long idQuarto){
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(idSolicitacao);
        Optional<Quarto> quartoOptional = quartoRepository.findById(idQuarto);

        if (solicitacaoOptional.isPresent() && quartoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            Quarto quarto = quartoOptional.get();
            model.addAttribute("numeroHospedes", hospedesPresentes(solicitacao));
            model.addAttribute("solicitacao" , solicitacao);
            model.addAttribute("quarto" , quarto);

            return "quarto/quarto";
        }
        return "404";
    }

    @PostMapping("/aceitar")
    public String aceitaSolicitacao(Long id, Long idQuarto) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);
        Quarto quarto = quartoRepository.getOne(idQuarto);

        limitaQuartos(solicitacao, quarto);

        return "redirect:/";
    }

    @PostMapping("/negar")
    public String negaSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus("Negado");
            solicitacaoRepository.save(solicitacao);

            return "redirect:/solicitacao/casa/lista";
        }
        return "404";
    }

    private int hospedesPresentes(Solicitacao solicitacao){
        int numeroHospedes = 1;

        for(Acompanhante acompanhante: solicitacao.getAcompanhantes()){
            if(!acompanhante.getNome().isEmpty()){
                numeroHospedes++;
            }
        }
        return numeroHospedes;
    }

    private void limitaQuartos(Solicitacao solicitacao, Quarto quarto){
        int numeroHospedes = hospedesPresentes(solicitacao);

        if(numeroHospedes <= quarto.getLeitosDisponiveis()) {
            solicitacao.setStatus("Aceito");
            solicitacao.setQuarto(quarto);
            quarto.getSolicitacoes().add(solicitacao);
            quarto.setLeitosDisponiveis(quarto.getLeitosDisponiveis() - numeroHospedes);

            if (quarto.getLeitosDisponiveis() <= 0) {
                quarto.setStatusQuartos("Indisponivel");
            }
        }
        solicitacaoRepository.save(solicitacao);
        quartoRepository.save(quarto);
    }
}