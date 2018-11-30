package com.thoughtworks.aceleradora.login.controllers;

import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import com.thoughtworks.aceleradora.login.servicos.UsuarioService;
import com.thoughtworks.aceleradora.login.validador.UsuarioValidador;
import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/listaUsuarios")
    public String buscarTodosUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);
        return "solicitacao/listagens/listaUsuarios";
    }

    @GetMapping("/{id}/editaUsuario")
    public String editaUsuario(Model model, @PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarios = usuarioOptional.get();
            model.addAttribute("usuarios", usuarios);
            return "/solicitacao/editaUsuario";
        }

        return "404";
    }

    @PostMapping("/{id}/editaUsuario")
    public String salvarDadoEditadoUsuario(@PathVariable Long id, Usuario usuario) {
        Usuario usuarioAtualizado = usuarioRepository.getOne(id);

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setNomeAssistente(usuario.getNomeAssistente());
        usuarioAtualizado.setEmail(usuario.getEmail());
        usuarioAtualizado.setHospitalReferencia(usuario.getHospitalReferencia());
        usuarioAtualizado.setTelefone(usuario.getTelefone());
        usuarioRepository.save(usuarioAtualizado);

        return "redirect:/listaUsuarios";
    }

    @GetMapping("/{id}/editaSenhaUsuario")
    public String editaSenhaUsuario(Model model, @PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarios = usuarioOptional.get();
            usuarios.getSenha();

            model.addAttribute("usuarios", usuarios);
            return "/solicitacao/editaSenhaUsuario";
        }

        return "404";
    }

    @PostMapping("/{id}/editaSenhaUsuario")
    public String salvarSenhaEditadoUsuario(@PathVariable Long id, Usuario usuario) {
        Usuario senhaAtualizada = usuarioRepository.getOne(id);

        senhaAtualizada.setSenha(usuario.getSenha());
        usuarioService.salvar(senhaAtualizada);

        return "redirect:/listaUsuarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluirUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                usuarioRepository.deleteById(id);
            }

            return "redirect:/listaUsuarios";
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
