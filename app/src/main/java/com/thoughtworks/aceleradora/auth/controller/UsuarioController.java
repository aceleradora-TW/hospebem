package com.thoughtworks.aceleradora.auth.controller;

import com.thoughtworks.aceleradora.auth.dominio.Usuario;
import com.thoughtworks.aceleradora.auth.servicos.UsuarioService;
import com.thoughtworks.aceleradora.auth.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioValidator usuarioValidator;

    @GetMapping(value = "/registrar")
    public String registrar(Model model) {
        model.addAttribute("formUsuario", new Usuario());

        return "registrar";
    }

    @PostMapping(value = "/registrar")
    public String registrar(@ModelAttribute("formUsuario") Usuario usuario, BindingResult bindingResult) {
        usuarioValidator.validate(usuario, bindingResult);

        if(bindingResult.hasErrors()) {
            return "registrar";
        }

        usuarioService.salvar(usuario);

        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
