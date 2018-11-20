package com.thoughtworks.aceleradora.login.servicos.controllers;

import com.thoughtworks.aceleradora.login.controllers.LoginController;
import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import com.thoughtworks.aceleradora.login.servicos.UsuarioService;
import com.thoughtworks.aceleradora.login.validador.UsuarioValidador;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginControllerTest {
    @Mock
    UsuarioValidador usuarioValidador;
    @Mock
    UsuarioService usuarioService;
    @Mock
    Model model;

    LoginController loginController;

    @Before
    public void setup() {
        initMocks(this);
        loginController = new LoginController(usuarioValidador, usuarioService);
    }

    @Test
    public void testeGetRegistrar() {
        assertThat(loginController.registrar(model)).isEqualTo("registrarUsuario/registrar");
        verify(model, times(1)).addAttribute(eq("formUsuario"), any(Usuario.class));
        verify(model, times(1)).addAttribute(eq("cargos"), eq(Cargo.values()));
    }

    @Test
    public void testePostRegistrar() {
        Usuario usuario = mock(Usuario.class);
        BindingResult bindingResult = mock(BindingResult.class);
        doNothing().when(usuarioValidador).validate(usuario, bindingResult);
        when(bindingResult.hasErrors()).thenReturn(false);
        assertThat(loginController.registrar(usuario, bindingResult)).isEqualTo("redirect:/login");
        verify(usuarioService, times(1)).salvar(usuario);
    }

    @Test
    public void testeLogin() {
        assertThat(loginController.login()).isEqualTo("login/login");
    }

    @Test
    public void testePaginaAdmin() {
        assertThat(loginController.paginaAdmin()).isEqualTo("admin");
    }

    @Test
    public void testePaginaAssistente() {
        assertThat(loginController.paginaAssistente()).isEqualTo("/solicitacao/listagens/listaSolicitacaoHospital");
    }
}