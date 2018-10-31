package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository solicitacaoRepository;


    public SolicitacaoController() {

    }

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.solicitacaoRepository = repositorio;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Solicitacao solicitacao, Model model) {
        Solicitacao novaSolicitacao = new Solicitacao();
        novaSolicitacao.setAcompanhantes(Arrays.asList(new Acompanhante(), new Acompanhante()));
        model.addAttribute("solicitacao", novaSolicitacao);
        return "solicitacao/cadastro";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {

        model.addAttribute("solicitacoesCasa", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public String salvaSolicitacao(Model model, @ModelAttribute("solicitacao") Solicitacao solicitacao) {
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));

        solicitacaoRepository.save(solicitacao);

        model.addAttribute("solicitacoes", solicitacaoRepository.findAll());

        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Solicitacao solicitacao) {
        solicitacaoRepository.save(solicitacao);

        return "redirect:/" ;
    }

    @PostMapping("/aceitar")
    public String aceitaSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if(solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus("Aceito: " + solicitacao.getNome());
            solicitacaoRepository.save(solicitacao);

            return "redirect:/solicitacao/casa/lista";
        }

        return "404";
    }

    @PostMapping("/negar")
    public String negaSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if(solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setStatus("Negado: " + solicitacao.getNome());
            solicitacaoRepository.save(solicitacao);

            return "redirect:/solicitacao/casa/lista";
        }

        return "404";
    }

    @GetMapping("/gerenciaHospede/listagemHospede")
    public String listaGerenciamentoHospede(Model model) {

        model.addAttribute("gerenciaHospede", solicitacaoRepository.findAllByStatus("aceito"));

        return "solicitacao/listaHospede/listaGerenciamentoHospede";
    }

}
