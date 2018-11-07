package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository solicitacaoRepository;


    public SolicitacaoController() {

    }

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.solicitacaoRepository = repositorio;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        Solicitacao novaSolicitacao = new Solicitacao();
        novaSolicitacao.setAcompanhantes(Arrays.asList(new Acompanhante(), new Acompanhante()));
        model.addAttribute("solicitacao", novaSolicitacao);
        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Solicitacao solicitacao) {
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));

        solicitacaoRepository.save(solicitacao);

        return "redirect:/solicitacao/hospital/lista";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {

        model.addAttribute("solicitacoesCasa", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @GetMapping("/listagemHospede")
    public String listaGerenciamentoHospede(Model model) {

        model.addAttribute("solicitacoesAceitas", solicitacaoRepository.findAllByStatus("aceito"));

        return "solicitacao/listagens/listaGerenciamentoHospede";
    }

    @GetMapping("/{id}/editar")
    public String editaDadosHospede(Model model, @PathVariable Long id){
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if(solicitacaoOptional.isPresent()){
            Solicitacao solicitacao = solicitacaoOptional.get();

            model.addAttribute("formata", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            model.addAttribute("hospede" , solicitacao);

            return "editaPaciente";
        }
        return "404";
    }

    @PostMapping("/{id}/editar")
    public String salvarDadoEditadoHospede(Model model, @PathVariable Long id, Solicitacao solicitacao){
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if(solicitacaoOptional.isPresent()){
            Solicitacao solicitacaoAtu = solicitacaoOptional.get();
            solicitacaoAtu.setNome(solicitacao.getNome());
            solicitacaoAtu.setTelefone(solicitacao.getTelefone());
            solicitacaoAtu.setEndereco(solicitacao.getEndereco());
            solicitacaoAtu.setSituacao(solicitacao.getSituacao());
            solicitacaoAtu.setGenero(solicitacao.getGenero());
            solicitacaoAtu.setPeso((Float)solicitacao.getPeso());
            solicitacaoAtu.setDataNascimento(solicitacao.getDataNascimento());
            solicitacaoAtu.setDataTransplante(solicitacao.getDataTransplante());

             solicitacaoRepository.save(solicitacaoAtu);
            return "solicitacao/listagens/listaSolicitacaoHospital";
        }
        return "404";
    }
}