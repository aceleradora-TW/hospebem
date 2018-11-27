package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    @Mock
    private Model model;

    @Mock
    private SolicitacaoRepository repositorio;

    @Before
    public void setUp() {
        controller = new SolicitacaoController(repositorio);
    }

    @Test
    public void renderizaFormularioDeCadastroComSolicitacaoVazia() {
        Solicitacao solicitacaoVazia = new Solicitacao();
        String paginaRenderizada = controller.formularioCadastro(model, solicitacaoVazia);

        verify(model).addAttribute("solicitacao", solicitacaoVazia);
        assertThat(solicitacaoVazia.getAcompanhantes(), equalTo(asList(new Acompanhante(), new Acompanhante())));
        assertThat(paginaRenderizada, equalTo("solicitacao/cadastro"));
    }

    @Test
    public void salvaSolicitacaoNoBancoAtualizandoAsReferenciasDeCadaAcompanhante() {

        Solicitacao umaSolicitacao = new Solicitacao();

        Acompanhante umAcompanhante = new Acompanhante();

        umAcompanhante.setSolicitacao(umaSolicitacao);
        umaSolicitacao.setAcompanhantes(singletonList(umAcompanhante));

        String paginaRenderizada = controller.salvaSolicitacao(umaSolicitacao);

        verify(repositorio).save(umaSolicitacao);
        assertThat(umAcompanhante.getSolicitacao(), equalTo(umaSolicitacao));
        assertThat(paginaRenderizada, equalTo("redirect:/solicitacao/hospital/lista"));
    }

    @Test
    public void renderizaSolicitacoesDaCasaComTodasSolicitacoesPendentes() {
        List<Solicitacao> solicitacoesPendentes = asList(new Solicitacao(), new Solicitacao());
        when(repositorio.findAllByStatus("Pendente")).thenReturn(solicitacoesPendentes);

        String paginaRenderizada = controller.listaSolicitacoesDaCasa(model);

        verify(model).addAttribute("solicitacoesCasa", solicitacoesPendentes);
        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaSolicitacaoCasa"));
    }

    @Test
    public void deveRenderizarListaDeHospedes() {
        List<Solicitacao> solicitacoesAceitas = asList(new Solicitacao(), new Solicitacao());
        when(repositorio.findAllByStatus("Aceito")).thenReturn(solicitacoesAceitas);

        String paginaRenderizada = controller.listaGerenciamentoHospede(model);


        verify(model).addAttribute("solicitacoesAceitas", solicitacoesAceitas);
        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaGerenciamentoHospede"));
    }

    @Test
    public void deveRenderizarListaSolicitacaoHospital() {
        List<Solicitacao> solicitacoesHospital = asList(new Solicitacao(), new Solicitacao());
        when(repositorio.findAll()).thenReturn(solicitacoesHospital);

        String paginaRenderizada = controller.listaSolicitacoesDoHospital(model);

        verify(model).addAttribute("solicitacoesHospital", solicitacoesHospital);
        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaSolicitacaoHospital"));
    }

    @Test
    public void exibeDadosDoPacienteQuandoEsteExistir() {
        Solicitacao solicitacao = new Solicitacao();
        when(repositorio.findById(1L)).thenReturn(Optional.of(solicitacao));

        String paginaRenderizada = controller.mostraDadosPaciente(model, 1L);

        verify(model).addAttribute("solicitante", solicitacao);
        assertThat(paginaRenderizada, equalTo("solicitacao/listaHospede/dadosSolicitante"));
    }

    @Test
    public void exibeTelaDeNaoEncontrarQuandoPacienteNaoExistir() {
        when(repositorio.findById(1L)).thenReturn(Optional.empty());

        String paginaRenderizada = controller.mostraDadosPaciente(model, 1L);

        assertThat(paginaRenderizada, equalTo("404"));
    }
}