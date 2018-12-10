package com.thoughtworks.aceleradora.quarto.controller;

import com.thoughtworks.aceleradora.quarto.dominio.*;
import com.thoughtworks.aceleradora.quarto.helpers.QuartoHelper;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import com.thoughtworks.aceleradora.email.component.EmailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/quarto")
public class QuartoController{
    private QuartoHelper quartoHelper;
    private QuartoRepository quartoRepository;
    private SolicitacaoRepository solicitacaoRepository;
    private EmailComponent emailComponent;

    public QuartoController() {
    }

    @Autowired
    public QuartoController(QuartoHelper quartoHelper, QuartoRepository repositorio, SolicitacaoRepository solicitacaoRepository, EmailComponent emailComponent) {
        this.quartoHelper = quartoHelper;
        this.quartoRepository = repositorio;
        this.solicitacaoRepository = solicitacaoRepository;
        this.emailComponent = emailComponent;
    }

    @GetMapping("/quartos")
    public String mapaQuartos(Model model) {
            model.addAttribute("listaQuartos", quartoRepository.findAll());

            return "quarto/listagens/mapaQuartos";
    }

    @GetMapping("/quartos/{idQuarto}")
    public String umQuarto (Model model, @PathVariable Long idQuarto){
        Optional<Quarto> quartoOptional = quartoRepository.findById(idQuarto);

        if (quartoOptional.isPresent()) {
            Quarto quarto = quartoOptional.get();

            model.addAttribute("quarto" , quarto);
            model.addAttribute("ocupantes", quartoHelper.ocupantes(quarto.getSolicitacoes()));

            return "quarto/umQuarto";
        }
        return "404";
    }

    @GetMapping("/{idSolicitacao}/listaQuartos")
    public String listaQuartos(Model model, @PathVariable Long idSolicitacao) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(idSolicitacao);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();

            model.addAttribute("numeroHospedes", quartoHelper.hospedesPresentes(solicitacao) - 1);
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

            model.addAttribute("numeroHospedes", quartoHelper.hospedesPresentes(solicitacao) - 1);
            model.addAttribute("solicitacao" , solicitacao);
            model.addAttribute("quarto" , quarto);
            model.addAttribute("ocupantes", quartoHelper.ocupantes(quarto.getSolicitacoes()));

            return "quarto/quarto";
        }
        return "404";
    }

    @PostMapping("/aceitar")
    public String aceitaSolicitacao(Long id, Long idQuarto) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);
        Quarto quarto = quartoRepository.getOne(idQuarto);

        quartoHelper.limitaQuartos(solicitacao, quarto);

        return "redirect:/listacheckincheckout";
    }

    @PostMapping("/negar")
    public String negaSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus(Solicitacao.Status.NEGADO);

            solicitacaoRepository.save(solicitacao);
            emailComponent.notificaHospital(solicitacao);

            return "redirect:/solicitacao/casa/lista";
        }
        return "404";
    }
}