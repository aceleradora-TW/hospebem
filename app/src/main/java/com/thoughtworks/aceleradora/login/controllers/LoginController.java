package com.thoughtworks.aceleradora.login.controllers;

import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import com.thoughtworks.aceleradora.login.servicos.UsuarioService;
import com.thoughtworks.aceleradora.login.validador.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    private UsuarioValidador usuarioValidador;
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;


    @Autowired
    public LoginController(UsuarioValidador usuarioValidador, UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioValidador = usuarioValidador;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;

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
            return "registrarUsuario/usuarioError";
        }

        usuarioService.salvar(usuario);

        return "registrarUsuario/usuarioSalvo";
    }

    @RequestMapping(value = "/listaUsuarios", method = RequestMethod.GET)
    public String buscarTodosUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);
        return "solicitacao/listagens/listaUsuarios";
    }

    @RequestMapping(value = "{id}/editaUsuario", method = RequestMethod.GET)
    public String editaUsuario(Model model, @PathVariable Long id) {
        Usuario usuario = usuarioRepository.findOneById(id);
        model.addAttribute("usuarios", usuario);
        return "/solicitacao/editaUsuario";
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
}
