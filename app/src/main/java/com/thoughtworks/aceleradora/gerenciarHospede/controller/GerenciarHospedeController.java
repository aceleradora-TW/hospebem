package com.thoughtworks.aceleradora.gerenciarHospede.controller;

import com.thoughtworks.aceleradora.gerenciarHospede.dominio.GerenciarHospedeRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gerenciarHospede")
public class GerenciarHospedeController {

    public GerenciarHospedeController() {

    }

    private GerenciarHospedeRepository gerenciarHospedeRepository;

    @Autowired
    public GerenciarHospedeController(GerenciarHospedeRepository repositorio) {
        this.gerenciarHospedeRepository = repositorio;
    }


    @GetMapping("/listagenHospede")
    public String listaSolicitacoesDaCasa(Model model) {

        model.addAttribute("solicitacoesCasa", gerenciarHospedeRepository.findBySituacao("pre"));

        return "listaHospede/listaGerenciamentoHospede";
    }
}
