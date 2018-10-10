package com.thoughtworks.aceleradora.login.validador;

import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz){
        return Usuario.class.equals(clazz);
    }

    @Override
    public void validate(Object objeto, Errors erros){
        Usuario usuario = (Usuario) objeto;

        if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null){
            erros.rejectValue("usuario", "Usuario.usuarioForm.usuarioDuplicado");
        }
        if(usuario.getUsuario() == null){
            erros.rejectValue("usuario", "Usuario.usuarioForm.usuarioVazio");
        }
    }
}
