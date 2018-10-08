package com.thoughtworks.aceleradora.listaSolicitacaoController.controller;

import com.thoughtworks.aceleradora.listaSolicitacoesCasa.controllers.ListaSolicitacaoCasaController;
import com.thoughtworks.aceleradora.listaSolicitacoesCasa.dominio.ListaSolicitacaoCasaRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class listaSolicitacoesCasaControllerTest {

    @Mock
    private ListaSolicitacaoCasaRepository repository;
    private ListaSolicitacaoCasaController controller;

    @Before
    public void setUp() {
        controller = new ListaSolicitacaoCasaController(repository);
    }

    @Test
    public void renderizaListaDeSolicitacoesDaCasaCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCasaCadastradas = asList(
                new Solicitacao("Amanda"),
                new Solicitacao("Aline"));
        Model model = mock(Model.class);
        when(repository.findAll()).thenReturn(solicitacoesCasaCadastradas);

        String paginaRenderizada = controller.listaSolicitacao(model);

        verify(model).addAttribute("solicitacoesCasa", solicitacoesCasaCadastradas);
        assertThat(paginaRenderizada, is("listaSolicitacaoCasa/listaSolicitacaoCasa"));

    }
}
