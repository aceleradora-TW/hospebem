package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.email.component.EmailComponent;
import com.thoughtworks.aceleradora.solicitacao.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    private SolicitacaoRepository solicitacaoRepository;
    private EmailComponent emailComponent;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository repositorio, EmailComponent emailComponent) {
        this.solicitacaoRepository = repositorio;
        this.emailComponent = emailComponent;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model, Solicitacao novaSolicitacao) {
        novaSolicitacao.setAcompanhantes(Arrays.asList(new Acompanhante(), new Acompanhante()));
        model.addAttribute("solicitacao", novaSolicitacao);
        return "solicitacao/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvaSolicitacao(Solicitacao solicitacao) {
        solicitacao
            .setAcompanhantes(solicitacao
                .getAcompanhantes()
                .stream()
                .filter(acompanhante -> (!acompanhante.getNome().isEmpty() || acompanhante.getDataNascimento() != null))
                .peek(acompanhante -> acompanhante.setSolicitacao(solicitacao))
                .collect(Collectors.toList()));

        solicitacaoRepository.save(solicitacao);
        emailComponent.notificaCasa(solicitacao);

        return "redirect:/solicitacao/hospital/lista";
    }

    @GetMapping("/casa/lista")
    public String listaSolicitacoesDaCasa(Model model) {
        Function<LocalDate, Integer> calculadoraIdade = (dataNascimento) -> Period
                .between(dataNascimento, LocalDate.now())
                .getYears();

        model.addAttribute("calculadoraIdade", calculadoraIdade);
        model.addAttribute("solicitacoesCasa",
                solicitacaoRepository.findAllByStatusOrderByNome(
                        Solicitacao.Status.PENDENTE));

        return "solicitacao/listagens/listaSolicitacaoCasa";
    }

    @GetMapping("/hospital/lista")
    public String listaSolicitacoesDoHospital(Model model) {
        model.addAttribute("solicitacoesHospital",
                solicitacaoRepository.findAllByOrderByIdDesc());

        return "solicitacao/listagens/listaSolicitacaoHospital";
    }

    @GetMapping("/listagemHospede")
    public String listaGerenciamentoHospede(Model model) {
        model.addAttribute("solicitacoesAceitas", solicitacaoRepository.findAllByStatus(Solicitacao.Status.ACEITO));

        return "solicitacao/listagens/listaGerenciamentoHospede";
    }

    @GetMapping("{id}/dados")
    public String mostraDadosPaciente(Model model, @PathVariable Long id) {
        model.addAttribute("formatar", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        model.addAttribute("solicitacao", solicitacaoRepository.getOne(id));

        return "solicitacao/dadosSolicitacao";
    }

    @GetMapping("/{id}/editar")
    public String editaDadosHospede(Model model, @PathVariable Long id) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);
        solicitacao.getAcompanhantes().sort(Comparator.comparing(Acompanhante::getId));

        model.addAttribute("formata", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        model.addAttribute("solicitacao", solicitacao);

        return "solicitacao/editaPaciente";
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

        solicitacaoAtualizada
                .setAcompanhantes(solicitacao
                        .getAcompanhantes()
                        .stream()
                        .peek(acompanhante -> acompanhante.setSolicitacao(solicitacaoAtualizada))
                        .collect(Collectors.toList()));

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