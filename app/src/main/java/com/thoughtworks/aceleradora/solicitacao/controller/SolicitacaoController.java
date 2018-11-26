package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio) {
        this.solicitacaoRepository = repositorio;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model, Solicitacao novaSolicitacao) {
        novaSolicitacao.setAcompanhantes(Arrays.asList(new Acompanhante(), new Acompanhante()));
        model.addAttribute("solicitacao", novaSolicitacao);
        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Solicitacao solicitacao) {
        solicitacao.getAcompanhantes().forEach(acompanhante -> acompanhante.setSolicitacao(solicitacao));

        Acompanhante acompanhante = solicitacao.getAcompanhantes().get(1);
        if (acompanhante.getNome().isEmpty() || acompanhante.getDataNascimento() ==null ) {
            solicitacao.getAcompanhantes().remove(1);
        }

        solicitacaoRepository.save(solicitacao);

        return "redirect:/solicitacao/hospital/lista";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {
        Function<LocalDate, Integer> calculadoraIdade = (dataNascimento) -> Period
                .between(dataNascimento, LocalDate.now())
                .getYears();

        model.addAttribute("calculadoraIdade", calculadoraIdade);
        model.addAttribute("solicitacoesCasa", solicitacaoRepository.findAllByStatus("Pendente"));

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/listagemHospede")
    public String listaGerenciamentoHospede(Model model) {
        model.addAttribute("solicitacoesAceitas", solicitacaoRepository.findAllByStatus("Aceito"));

        return "solicitacao/listagens/listaGerenciamentoHospede";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {

        model.addAttribute("solicitacoesHospital", solicitacaoRepository.findAll());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @GetMapping("{id}/dados")
    public String mostraDadosPaciente(Model model, @PathVariable Long id) {

        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            model.addAttribute("formata", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            model.addAttribute("solicitante", solicitacao);

            return "solicitacao/listaHospede/dadosSolicitante";
        }
        return "404";
    }


    @GetMapping("/{id}/editar")
    public String editaDadosHospede(Model model, @PathVariable Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);
        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.getAcompanhantes().sort(Comparator.comparing(Acompanhante::getId));
            model.addAttribute("formata", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            model.addAttribute("solicitacao", solicitacao);
            return "solicitacao/editaPaciente";
        }
        return "404";
    }

    @PostMapping("/{id}/editar")
    public String salvarDadoEditadoHospede(@PathVariable Long id, Solicitacao solicitacao) {
        Solicitacao solicitacaoAtualizada = solicitacaoRepository.getOne(id);

        solicitacaoAtualizada.setNome(solicitacao.getNome());
        solicitacaoAtualizada.setTelefone(solicitacao.getTelefone());
        solicitacaoAtualizada.setSituacao(solicitacao.getSituacao());
        solicitacaoAtualizada.setGenero(solicitacao.getGenero());
        solicitacaoAtualizada.setPeso(solicitacao.getPeso());
        solicitacaoAtualizada.setDataNascimento(solicitacao.getDataNascimento());
        solicitacaoAtualizada.setDataTransplante(solicitacao.getDataTransplante());
        solicitacaoAtualizada.setDataEntrada(solicitacao.getDataEntrada());
        solicitacaoAtualizada.setDataSaida(solicitacao.getDataSaida());
        solicitacaoAtualizada.setEndereco(solicitacao.getEndereco());

        solicitacaoAtualizada.setAcompanhantes(solicitacao.getAcompanhantes());

        for (Acompanhante acompanhante : solicitacaoAtualizada.getAcompanhantes()) {
            acompanhante.setSolicitacao(solicitacaoAtualizada);
        }

        solicitacaoRepository.save(solicitacaoAtualizada);
        return "redirect:/solicitacao/hospital/lista";
    }

    @GetMapping("/{id}/excluir")
    public String excluirSolicitacaoHospital(@PathVariable Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(id);

        if (solicitacaoOptional.isPresent()) {
            solicitacaoRepository.deleteById(id);

            return "redirect:/solicitacao/hospital/lista";
        }
        return "404";
    }
}