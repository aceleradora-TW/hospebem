package com.thoughtworks.aceleradora.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String renderizaPaginaInicial() {
        return "inicio";
    }

    @GetMapping(value = "/bemvindo")
    public String bemVindo(){
        return "bemvindo";
    }
}
