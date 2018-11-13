package com.thoughtworks.aceleradora.login.configuracao;

import com.thoughtworks.aceleradora.login.dominio.Cargo;
import com.thoughtworks.aceleradora.login.dominio.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy;

    public AuthSuccessHandler() {
        this.redirectStrategy = new DefaultRedirectStrategy();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SimpleGrantedAuthority cargo = (SimpleGrantedAuthority) authentication.getAuthorities().stream().findFirst().get();

        if(cargo.getAuthority().equals(Cargo.ADMINISTRADOR.getNome())) {
            this.redirectStrategy.sendRedirect(request, response, "/admin");
        } else if(cargo.getAuthority().equals(Cargo.ASSISTENTE_SOCIAL.getNome())) {
            this.redirectStrategy.sendRedirect(request, response, "/assistente");
        } else {
            this.redirectStrategy.sendRedirect(request, response, "/bemvindo");
        }
    }
}
