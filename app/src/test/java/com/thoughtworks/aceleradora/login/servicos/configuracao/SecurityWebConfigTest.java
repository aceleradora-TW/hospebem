package com.thoughtworks.aceleradora.login.servicos.configuracao;

import com.thoughtworks.aceleradora.login.configuracao.SecurityWebConfig;
import com.thoughtworks.aceleradora.login.servicos.UserDetailsImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SecurityWebConfigTest {

    @Mock
    UserDetailsImpl userDetails;

    @Mock
    AuthenticationManagerBuilder authentication;

    SecurityWebConfig securityWebConfig;

    @Before
    public void setup() {
        initMocks(this);
        securityWebConfig = spy(new SecurityWebConfig(userDetails));
    }

    @Test
    public void testeConfigureGlobal() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        DaoAuthenticationConfigurer daoAuthenticationConfigurer = mock(DaoAuthenticationConfigurer.class);

        when(securityWebConfig.bCryptPasswordEncoder()).thenReturn(bCryptPasswordEncoder);
        when(authentication.userDetailsService(userDetails)).thenReturn(daoAuthenticationConfigurer);

        securityWebConfig.configureGlobal(authentication);

        verify(authentication, times(1)).userDetailsService(userDetails);
        verify(daoAuthenticationConfigurer, times(1)).passwordEncoder(bCryptPasswordEncoder);
    }

}