package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PostMapping("/cadastro")
    @ResponseBody
    public String salvaSolicitacao(Model model, @ModelAttribute("solicitacao") Solicitacao solicitacao) {
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));
        solicitacao.setCancelamento(false);
        solicitacao.setStatus("Pendente");
        solicitacao.setDataAtualizacao(LocalDateTime.now());
        solicitacaoRepository.save(solicitacao);

        model.addAttribute("solicitacoes", solicitacaoRepository.findAll());

        return "redirect:/";
    }

    @GetMapping("/dados/id")
    public String mostraDadosPaciente(Model model, @RequestParam("id") Long id, Solicitacao solicitacao) {
        model.addAttribute("solicitacao", solicitacaoRepository.findById(id));
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));

        return "/solicitacao/a";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {

        model.addAttribute("solicitacoesCasa", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", solicitacaoRepository.findAll());
        model.addAttribute("formata", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @GetMapping("/casa/lista/aceitos")
    public String gerenciarHospedesAceitos(Model model) {

        model.addAttribute("solicitacoesAceitas", solicitacaoRepository.findAll());

        return "solicitacao/GerenciarHospedeAceitos";
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

    @GetMapping("/aceitar")
    public String aceitaSolicitacaoCasa(Model model){
        model.addAttribute("status", solicitacaoRepository.findAll());
        return "solicitacao/aceitar/hospital/lista";
    }

    @PostMapping("/cancelar")
    public String cancelarSolicitacao(Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if(solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setCancelamento(true);
            solicitacaoRepository.save(solicitacao);

            return "redirect:/solicitacao/hospital/lista";
        }
        return "404";
    }
    @GetMapping("/gerenciaHospede/listagemHospede")
    public String listaGerenciamentoHospede(Model model) {

        model.addAttribute("gerenciaHospede", solicitacaoRepository.findAllByStatus("aceito"));

        return "solicitacao/listaHospede/listaGerenciamentoHospede";
    }
}
