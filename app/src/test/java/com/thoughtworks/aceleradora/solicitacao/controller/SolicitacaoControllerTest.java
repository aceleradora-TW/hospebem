package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    @Mock
    private SolicitacaoRepository repositorio;

    @Before
    public void setUp() {
        controller = new SolicitacaoController(repositorio);
    }

    @Test
    public void deveRenderizarFormularioDeCadastro() {

        String paginaRenderizada = controller.formularioCadastro();

        assertThat(paginaRenderizada, equalTo("solicitacao/cadastro"));
    }

    @Test
    public void deveSalvarSolicitacaoNoBanco() {
        Solicitacao solicitacao = new Solicitacao("Abc");

        String mensagem = controller.salvaSolicitacao(solicitacao);

        verify(repositorio).save(solicitacao);
        assertThat(mensagem, equalTo("salvou:Abc"));
    }
}