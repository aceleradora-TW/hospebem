package com.thoughtworks.aceleradora.login.controllers;

import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import com.thoughtworks.aceleradora.login.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/listaUsuarios")
    public String buscarTodosUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);

        return "/usuario/listagens/listaUsuarios";
    }

    @GetMapping("/{id}/editaUsuario")
    public String editaUsuario(Model model, @PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarios = usuarioOptional.get();
            model.addAttribute("usuarios", usuarios);
            return "/usuario/editaUsuario";
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

        return "redirect:/usuario/listaUsuarios";
    }

    @GetMapping("/{id}/editaSenhaUsuario")
    public String editaSenhaUsuario(Model model, @PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarios = usuarioOptional.get();
            usuarios.getSenha();

            model.addAttribute("usuarios", usuarios);

            return "/usuario/editaSenhaUsuario";
        }

        return "404";
    }

    @PostMapping("/{id}/editaSenhaUsuario")
    public String salvarSenhaEditadoUsuario(@PathVariable Long id, Usuario usuario) {
        Usuario senhaAtualizada = usuarioRepository.getOne(id);

        senhaAtualizada.setSenha(usuario.getSenha());

        usuarioService.salvar(senhaAtualizada);

        return "redirect:/usuario/listaUsuarios";
    }

    @GetMapping("/{id}/excluirUsuario")
    public String excluirUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);

            return "redirect:/usuario/listaUsuarios";
        }
        return "404";
    }
}
