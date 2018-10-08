package com.thoughtworks.aceleradora.listaSolicitacoesHospital.controller;

import com.thoughtworks.aceleradora.listaSolicitacoesHospital.dominio.ListaSolicitacaoHospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ListaSolicitacaoHospitalController {

    private ListaSolicitacaoHospitalRepository repositorio;

    @Autowired
    public ListaSolicitacaoHospitalController(ListaSolicitacaoHospitalRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/listaHospital")
    public String listaSolicitacao(Model model){

        System.out.println(repositorio.findAll());
        model.addAttribute("solicitacoesHospital", repositorio.findAll());

        return "listaSolicitacaoHospital/listaSolicitacaoHospital";
    }
}
