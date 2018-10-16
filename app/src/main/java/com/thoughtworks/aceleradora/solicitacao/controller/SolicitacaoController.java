package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.ListaSolicitacaoCasaRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.ListaSolicitacaoHospitalRepository;
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
    private ListaSolicitacaoCasaRepository repositorioCasa;
    private ListaSolicitacaoHospitalRepository repositorioHospital;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorioSolicitacao, ListaSolicitacaoCasaRepository repositorioCasa, ListaSolicitacaoHospitalRepository repositorioHospital) {

        this.repositorioSolicitacao = repositorioSolicitacao;
        this.repositorioCasa = repositorioCasa;
        this.repositorioHospital = repositorioHospital;
    }

    public SolicitacaoController() {
    }

    @GetMapping("/cadastro")
    public String formularioCadastro() {
        return "solicitacao/cadastro";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {

        System.out.println(repositorioCasa.findAll());
        model.addAttribute("solicitacoesCasa", repositorioCasa.findAll());

        return "listaSolicitacao/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        System.out.println(repositorioHospital.findAll());
        model.addAttribute("solicitacoesHospital", repositorioHospital.findAll());

        return "listaSolicitacao/listaSolicitacaoHospital";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public String salvaSolicitacao(Solicitacao solicitacao) {
        repositorioSolicitacao.save(solicitacao);

        return "salvou:" + solicitacao.getNome();
    }
}
