package com.thoughtworks.aceleradora.paginaErro.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PaginaErroControllerTest {

    private PaginaErroController controller;

    @Before
    public void setUp() {
        controller = new PaginaErroController();
    }

    @Test
    public void deveRenderizarPaginaDeErro() {

        String paginaRenderizada = controller.mostraPaginaDeErro();

        assertThat(paginaRenderizada, equalTo("paginaErro/erro"));
    }
}