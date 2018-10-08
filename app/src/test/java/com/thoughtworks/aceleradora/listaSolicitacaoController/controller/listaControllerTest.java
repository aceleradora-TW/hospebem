package com.thoughtworks.aceleradora.listaSolicitacaoController.controller;

import com.thoughtworks.aceleradora.ListaSolicitacoes.controllers.ListaSolicitacaoController;
import com.thoughtworks.aceleradora.ListaSolicitacoes.dominio.ListaSolicitacaoRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.springframework.ui.Model;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class listaControllerTest {

    @Mock
    private ListaSolicitacaoRepository repository;
    private ListaSolicitacaoController controller;

    @Before
    public void setUp() {
        controller = new ListaSolicitacaoController(repository);
    }

    @Test
    public void renderizaListaDeSolicitacoesCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCadastradas = asList(
                new Solicitacao("Amanda"),
                new Solicitacao("Aline"));
        Model model = mock(Model.class);
        when(repository.findAll()).thenReturn(solicitacoesCadastradas);

        String paginaRenderizada = controller.listaSolicitacao(model);

        verify(model).addAttribute("solicitacoes", solicitacoesCadastradas);
        assertThat(paginaRenderizada, is("/lista"));

    }
}
