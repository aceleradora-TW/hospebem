package com.thoughtworks.aceleradora.listaSolicitacaoController.controller;

import com.thoughtworks.aceleradora.listaSolicitacoesHospital.controller.ListaSolicitacaoHospitalController;
import com.thoughtworks.aceleradora.listaSolicitacoesHospital.dominio.ListaSolicitacaoHospitalRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class listaSolicitacoesHospitalControllerTest {

    @Mock
    private ListaSolicitacaoHospitalRepository repository;
    private ListaSolicitacaoHospitalController controller;

    @Before
    public void setUp() {
        controller = new ListaSolicitacaoHospitalController(repository);
    }

    @Test
    public void renderizaListaDeSolicitacoesDoHospitalCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCasaCadastradas = asList(
                new Solicitacao("Olimar"),
                new Solicitacao("Yasser"));
        Model model = mock(Model.class);
        when(repository.findAll()).thenReturn(solicitacoesCasaCadastradas);

        String paginaRenderizada = controller.listaSolicitacao(model);

        verify(model).addAttribute("solicitacoesHospital", solicitacoesCasaCadastradas);
        assertThat(paginaRenderizada, is("listaSolicitacaoHospital/listaSolicitacaoHospital"));

    }
}