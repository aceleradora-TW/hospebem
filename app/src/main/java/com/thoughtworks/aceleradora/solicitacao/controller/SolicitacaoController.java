package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public String salvaSolicitacao(Solicitacao solicitacao) {
        repositorio.save(solicitacao);

        return "salvou:" + solicitacao.getNome();
    }

}
