package com.thoughtworks.aceleradora.quarto.controller;

import com.thoughtworks.aceleradora.quarto.dominio.*;
import com.thoughtworks.aceleradora.solicitacao.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

            model.addAttribute("numeroHospedes", hospedesPresentes(solicitacao) - 1);
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

            model.addAttribute("numeroHospedes", hospedesPresentes(solicitacao) - 1);
            model.addAttribute("solicitacao" , solicitacao);
            model.addAttribute("quarto" , quarto);
            model.addAttribute("ocupantes", ocupantes(quarto.getSolicitacoes()));

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
            solicitacao.setStatus(Solicitacao.Status.NEGADO.toString());
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

    public  List<Solicitacao> ocupantes(List<Solicitacao> solicitacoes){
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