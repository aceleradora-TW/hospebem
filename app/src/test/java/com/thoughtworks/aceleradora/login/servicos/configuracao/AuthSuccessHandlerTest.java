package com.thoughtworks.aceleradora.login.servicos.configuracao;

import com.thoughtworks.aceleradora.login.configuracao.AuthSuccessHandler;
import com.thoughtworks.aceleradora.login.dominio.Cargo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthSuccessHandlerTest {
    @Mock
    RedirectStrategy redirectStrategy;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Authentication authentication;

    AuthSuccessHandler authSuccessHandler;

    @Before
    public void setup() {
        initMocks(this);
        authSuccessHandler = new AuthSuccessHandler(redirectStrategy);
    }

    @Test
    public void testaOnAuthenticationSuccessParaAdmin() throws IOException, ServletException {
        Collection grantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(Cargo.ADMINISTRADOR.getNome()));

        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);

        authSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(redirectStrategy, times(1)).sendRedirect(request, response, "/bemvindo");
    }

    @Test
    public void testaOnAuthenticationSuccessParaAssistente() throws IOException, ServletException {
        Collection grantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(Cargo.ASSISTENTE_SOCIAL.getNome()));

        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);

        authSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(redirectStrategy, times(1)).sendRedirect(request, response, "/solicitacao/hospital/lista");
    }
}