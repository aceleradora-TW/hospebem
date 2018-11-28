package com.thoughtworks.aceleradora.quarto.controller;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import com.thoughtworks.aceleradora.quarto.dominio.QuartoRepository;
import com.thoughtworks.aceleradora.quarto.helpers.QuartoHelper;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CheckinCheckoutController {
    private QuartoRepository quartoRepository;
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public CheckinCheckoutController(QuartoRepository repositorio, SolicitacaoRepository solicitacaoRepository) {
        this.quartoRepository = repositorio;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @GetMapping("/listacheckincheckout")
    public String listaCheckinCheckout(Model model){
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
        solicitacoes.sort(Comparator.comparing(Solicitacao::getNome));

        model.addAttribute("quartos", quartoRepository.findAll());
        model.addAttribute("solicitacoes", solicitacoes);
        model.addAttribute("solicitacao", new Solicitacao());
        model.addAttribute("formataData", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return "quarto/listagens/listaCheckinCheckout";
    }

    @PostMapping("/checkin/{id}")
    public String checkin (@PathVariable Long id, Solicitacao solicitacaoCheckin) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);

        solicitacao.setDataCheckin(solicitacaoCheckin.getDataCheckin());
        solicitacao.setStatus(Solicitacao.Status.HOSPEDE.toString());
        solicitacaoRepository.save(solicitacao);

        return "redirect:/listacheckincheckout";
    }

    @PostMapping("/checkout/{id}")
    public String checkout (@PathVariable Long id, Solicitacao solicitacaoCheckout) {
        Solicitacao solicitacao = solicitacaoRepository.getOne(id);

        solicitacao.setDataCheckout(solicitacaoCheckout.getDataCheckout());
        solicitacao.setStatus(Solicitacao.Status.EX_HOSPEDE.toString());
        solicitacao.setQuarto(null);
        solicitacaoRepository.save(solicitacao);
        
        return "redirect:/listacheckincheckout";
    }
}
