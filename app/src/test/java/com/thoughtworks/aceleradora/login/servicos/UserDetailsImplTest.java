package com.thoughtworks.aceleradora.login.servicos;

import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDetailsImplTest {
    @Mock
    UsuarioRepository usuarioRepository;

    private UserDetailsImpl userDetails;

    private Usuario usuario;

    @Before
    public void setup() {
        initMocks(this);
        userDetails = new UserDetailsImpl(usuarioRepository);
        usuario = geraUsuario();
    }

    @Test
    public void testLoadByUserName(){
        when(usuarioRepository.findByNome(usuario.getNome())).thenReturn(usuario);

        UserDetails usuarioRetornado = userDetails.loadUserByUsername(usuario.getNome());

        assertThat(usuarioRetornado).extracting(UserDetails::getUsername, UserDetails::getPassword)
            .isEqualTo(Arrays.asList(usuario.getNome(), usuario.getSenha()));
        assertThat((SimpleGrantedAuthority) usuarioRetornado.getAuthorities().stream().findFirst().get())
                .extracting(SimpleGrantedAuthority::getAuthority)
                .first()
                .isEqualTo(Cargo.ADMINISTRADOR.getNome());
    }

    private Usuario geraUsuario() {
        return new Usuario("FooBar", "123", Cargo.ADMINISTRADOR);
    }
}