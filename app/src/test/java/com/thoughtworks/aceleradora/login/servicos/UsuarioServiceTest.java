package com.thoughtworks.aceleradora.login.servicos;

import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.dominio.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repositorio;

    @Mock
    private BCryptPasswordEncoder encoder;

    private UsuarioService servico;

    @Before
    public void setUp() {
        servico = new UsuarioService(repositorio, encoder);
    }

    @Test
    public void salvaUsuarioComSenhaCriptografada() {
        Usuario usuario = new Usuario("Abc", "Segredo", "administrador", "Shirley","shirley@blabla","23232","sao lucas");
        when(encoder.encode("Segredo")).thenReturn("Shhhh");

        servico.salvar(usuario);

        assertThat(usuario.getSenha(), equalTo("Shhhh"));
        verify(repositorio).save(usuario);
    }
}