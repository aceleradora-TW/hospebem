package com.thoughtworks.aceleradora.quarto.controller;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.quarto.helpers.QuartoHelp;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import com.thoughtworks.aceleradora.email.component.EmailComponent;
import com.thoughtworks.aceleradora.solicitacao.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.*;

@Controller
@RequestMapping("/quarto")
public class QuartoController{
    private QuartoRepository quartoRepository;
    private SolicitacaoRepository solicitacaoRepository;
    private EmailComponent emailComponent;

    private QuartoHelp quartoHelp = new QuartoHelp();

    public QuartoController() {
    }

    @Autowired
    public QuartoController(QuartoRepository repositorio, SolicitacaoRepository solicitacaoRepository, EmailComponent emailComponent) {
        this.quartoRepository = repositorio;
        this.solicitacaoRepository = solicitacaoRepository;
        this.emailComponent = emailComponent;
    }

    @GetMapping("/{idSolicitacao}/listaQuartos")
    public String listaQuartos(Model model, @PathVariable Long idSolicitacao) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(idSolicitacao);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();

            model.addAttribute("numeroHospedes", quartoHelp.hospedesPresentes(solicitacao) - 1);
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

            model.addAttribute("numeroHospedes", quartoHelp.hospedesPresentes(solicitacao) - 1);
            model.addAttribute("solicitacao" , solicitacao);
            model.addAttribute("quarto" , quarto);
            model.addAttribute("ocupantes", quartoHelp.ocupantes(quarto.getSolicitacoes()));

            return "quarto/quarto";
        }
        return "404";
    }

    @PostMapping("/aceitar")
    public String aceitaSolicitacao(Long id, Long idQuarto) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);
        Quarto quarto = quartoRepository.getOne(idQuarto);

        quartoHelp.limitaQuartos(solicitacao, quarto, solicitacaoRepository, quartoRepository);

        return "redirect:/listacheckincheckout";
    }

    @PostMapping("/negar")
    public String negaSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus(Solicitacao.Status.NEGADO.toString());

            solicitacaoRepository.save(solicitacao);
            emailComponent.notificaHospital(solicitacao);

            return "redirect:/solicitacao/casa/lista";
        }
        return "404";
    }
}