package com.thoughtworks.aceleradora.solicitacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @GetMapping("/cadastro")
    public String formularioCadastro() {
        return "solicitacao/cadastro";
    }
    

}
