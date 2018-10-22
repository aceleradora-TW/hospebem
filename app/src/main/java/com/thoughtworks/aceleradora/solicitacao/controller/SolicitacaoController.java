package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository repositorio;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.repositorio = repositorio;
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

        model.addAttribute("solicitacoesCasa", repositorio.findAll());

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", repositorio.findAll());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Model model, @ModelAttribute("solicitacao") Solicitacao solicitacao) {
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));

        repositorio.save(solicitacao);

        return "redirect:/" ;
    }

}
