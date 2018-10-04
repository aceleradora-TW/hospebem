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

    private SolicitacaoRepository repositorio;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro() {
        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Model model, Solicitacao solicitacao) {
        repositorio.save(solicitacao);

        model.addAttribute("solicitacoes", repositorio.findAll());

        return "solicitacao/lista";
    }

}
