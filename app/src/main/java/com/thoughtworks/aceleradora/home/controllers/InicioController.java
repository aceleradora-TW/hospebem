package com.thoughtworks.aceleradora.home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class InicioController {

    @GetMapping("/")
    public String renderizaPaginaInicial() {
        return "inicio";
    }
}
