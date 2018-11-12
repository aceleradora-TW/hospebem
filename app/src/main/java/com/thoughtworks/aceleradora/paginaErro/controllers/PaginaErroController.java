package com.thoughtworks.aceleradora.paginaErro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PaginaErroController {

    @GetMapping("/erro")
    public String mostraPaginaDeErro(){
        return "paginaErro/erro";
    }
}