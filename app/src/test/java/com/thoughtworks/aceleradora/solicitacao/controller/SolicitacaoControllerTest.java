package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Endereco;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    @Mock
    private Solicitacao solicitacao;

    @Mock
    private Model model;


    @Mock
    private SolicitacaoRepository repositorio;

    @Before
    public void setUp() {
        controller = new SolicitacaoController(repositorio);
    }

    @Test
    public void deveRenderizarFormularioDeCadastro() {

        String paginaRenderizada = controller.formularioCadastro(solicitacao, model);

        assertThat(paginaRenderizada, equalTo("solicitacao/cadastro"));
    }

    @Test
    public void deveSalvarSolicitacaoNoBanco() {

        Solicitacao soli = mock(Solicitacao.class);
        LocalDate dataNascimento = LocalDate.of(2010, 3, 7);

        Endereco end = new Endereco("A", "B", "C", "D", "E");

        List<Acompanhante> acompanhantes = asList(
                new Acompanhante("Amanda", "F", dataNascimento, soli),
                new Acompanhante("Aline","F", dataNascimento, soli));
        Solicitacao solicitacao = new Solicitacao("Abc", "F", "Pendente", "1234", "sim", 1, dataNascimento, end, acompanhantes);



        String mensagem = controller.salvaSolicitacao(model, solicitacao);

        verify(repositorio).save(solicitacao);
        assertThat(mensagem, equalTo("redirect:/"));
    }

    @Test
    public void renderizaListaDeSolicitacoesDaCasaCadastradasNoBanco() {
        List<Solicitacao> solicitacoesCasaCadastradas = asList(
                new Solicitacao("Amanda"),
                new Solicitacao("Aline"));
        Model model = mock(Model.class);
        when(repositorio.findAll()).thenReturn(solicitacoesCasaCadastradas);

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
        when(repositorio.findAll()).thenReturn(solicitacoesCasaCadastradas);

        String paginaRenderizada = controller.listaSolicitacoesDoHospital(model);

        verify(model).addAttribute("solicitacoesHospital", solicitacoesCasaCadastradas);
        Assert.assertThat(paginaRenderizada, is("listaSolicitacao/listaSolicitacaoHospital"));

    }
}