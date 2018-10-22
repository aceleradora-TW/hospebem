package com.thoughtworks.aceleradora.login.servicos;

import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public UserDetailsImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByNome(username);

        return new User(usuario.getNome(), usuario.getSenha(), new HashSet<>());
    }
}
