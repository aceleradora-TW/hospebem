package com.thoughtworks.aceleradora.login.controllers;


import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.servicos.UsuarioService;
import com.thoughtworks.aceleradora.login.validador.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private UsuarioValidador usuarioValidador;
    private UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioValidador usuarioValidador, UsuarioService usuarioService) {
        this.usuarioValidador = usuarioValidador;
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/registrar")
    public String registrar(Model model) {
        model.addAttribute("formUsuario", new Usuario());
        model.addAttribute("cargos", Cargo.values());

        return "registrarUsuario/registrar";
    }

    @PostMapping(value = "/registrar")
    public String registrar(@ModelAttribute("formUsuario") Usuario usuario, BindingResult bindingResult) {
        usuarioValidador.validate(usuario, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registrarUsuario/registrar";
        }

        usuarioService.salvar(usuario);

        return "redirect:/registrar";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login/login";
    }


    @GetMapping(value = "/bemvindo")
    public String bemVindo(@ModelAttribute Usuario usuario) {
        return "bemvindo/bemvindo";
    }

    @GetMapping(value = "/admin")
    public String paginaAdmin() {
        return "admin";
    }

    @GetMapping(value = "/listahospital")
    public String paginaAssistente() {
        return "redirect:/solicitacao/hospital/lista";
    }
}
