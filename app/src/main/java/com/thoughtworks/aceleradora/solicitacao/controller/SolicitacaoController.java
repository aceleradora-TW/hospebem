package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository repositorioSolicitacao;


    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorioSolicitacao ) {

        this.repositorioSolicitacao = repositorioSolicitacao;

    }

    public SolicitacaoController() {
    }

    @GetMapping("/cadastro")
    public String formularioCadastro() {
        return "solicitacao/cadastro";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {
        
        model.addAttribute("solicitacoesCasa", repositorioSolicitacao.findAll());

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", repositorioSolicitacao.findAll());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public String salvaSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacao.save(solicitacao);

        return "redirect:/inicio" ;
    }
}
