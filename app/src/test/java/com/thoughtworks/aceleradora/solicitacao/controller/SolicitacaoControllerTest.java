package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.ListaSolicitacaoCasaRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.ListaSolicitacaoHospitalRepository;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    @Mock
    private SolicitacaoRepository repositorioSolicitacao;
    private ListaSolicitacaoCasaRepository repositorioCasa;
    private ListaSolicitacaoHospitalRepository repositorioHospital;

    @Before
    public void setUp() {
        controller = new SolicitacaoController(repositorioSolicitacao, repositorioCasa, repositorioHospital);
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

        verify(repositorioSolicitacao).save(solicitacao);
        assertThat(mensagem, equalTo("salvou:Abc"));
    }

    @Test
    public void renderizaListaDeSolicitacoesDaCasaCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCasaCadastradas = asList(
                new Solicitacao("Amanda"),
                new Solicitacao("Aline"));
        Model model = mock(Model.class);
        when(repositorioCasa.findAll()).thenReturn(solicitacoesCasaCadastradas);

        String paginaRenderizada = controller.listaSolicitacoesDaCasa(model);

        verify(model).addAttribute("solicitacoesCasa", solicitacoesCasaCadastradas);
        Assert.assertThat(paginaRenderizada, is("listaSolicitacao/listaSolicitacaoCasa"));
    }

    @Test
    public void renderizaListaDeSolicitacoesDoHospitalCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCasaCadastradas = asList(
                new Solicitacao("Olimar"),
                new Solicitacao("Yasser"));
        Model model = mock(Model.class);
        when(repositorioHospital.findAll()).thenReturn(solicitacoesCasaCadastradas);

        String paginaRenderizada = controller.listaSolicitacoesDoHospital(model);

        verify(model).addAttribute("solicitacoesHospital", solicitacoesCasaCadastradas);
        Assert.assertThat(paginaRenderizada, is("listaSolicitacao/listaSolicitacaoHospital"));

    }
}