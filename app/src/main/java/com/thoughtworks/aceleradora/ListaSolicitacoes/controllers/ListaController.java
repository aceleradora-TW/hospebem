package com.thoughtworks.aceleradora.ListaSolicitacoes.controllers;

import com.thoughtworks.aceleradora.ListaSolicitacoes.dominio.ListaSolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class ListaController {

    private ListaSolicitacaoRepository repositorio;

    @Autowired
    public ListaController(ListaSolicitacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public String listaSolicitacao(Model model){

        System.out.println(repositorio.findAll());
        model.addAttribute("solicitacoes", repositorio.findAll());

        return "listaSolicitacao/lista";

    }
}
