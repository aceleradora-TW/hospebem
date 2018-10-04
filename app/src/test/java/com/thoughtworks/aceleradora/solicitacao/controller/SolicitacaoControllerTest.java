package com.thoughtworks.aceleradora.solicitacao.controller;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class SolicitacaoControllerTest {

    @Test
    public void deveRenderizarFormularioDeCadastro() {

        SolicitacaoController controller = new SolicitacaoController();

        String paginaRenderizada = controller.formularioCadastro();

        assertThat(paginaRenderizada, equalTo("olicitacao/cadastro"));

    }
}