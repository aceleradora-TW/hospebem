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


        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public String salvaSolicitacao(Solicitacao solicitacao) {

        System.out.println(solicitacao.getAcompanhantes().size());

        repositorio.save(solicitacao);

        return "salvou:" + solicitacao.getNome();
    }

}
