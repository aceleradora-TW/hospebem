package com.thoughtworks.aceleradora.listaSolicitacoesCasa.controllers;

import com.thoughtworks.aceleradora.listaSolicitacoesCasa.dominio.ListaSolicitacaoCasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class ListaSolicitacaoCasaController {

    private ListaSolicitacaoCasaRepository repositorio;

    @Autowired
    public ListaSolicitacaoCasaController(ListaSolicitacaoCasaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/listaCasa")
    public String listaSolicitacao(Model model){

        System.out.println(repositorio.findAll());
        model.addAttribute("solicitacoesCasa", repositorio.findAll());

        return "listaSolicitacaoCasa/listaSolicitacaoCasa";

    }
}
