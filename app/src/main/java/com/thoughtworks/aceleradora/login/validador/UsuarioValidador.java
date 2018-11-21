package com.thoughtworks.aceleradora.login.validador;

import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {

    private UsuarioRepository usuarioRepository;

    public UsuarioValidador(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.equals(clazz);
    }

    @Override
    public void validate(Object objeto, Errors erros) {
        Usuario usuario = (Usuario) objeto;

        if (usuarioRepository.findByNome(usuario.getNome()) != null) {
            erros.rejectValue("nome", "Usuario.usuarioForm.usuarioDuplicado");
        }

        if (usuario.getNome() == null) {
            erros.rejectValue("nome", "Usuario.usuarioForm.usuarioVazio");
        }

    }
}
