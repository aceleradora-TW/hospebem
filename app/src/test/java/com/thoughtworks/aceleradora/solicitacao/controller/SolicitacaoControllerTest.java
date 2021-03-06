package com.thoughtworks.aceleradora.solicitacao.controller;

import com.thoughtworks.aceleradora.solicitacao.dominio.Acompanhante;
import com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao;
import com.thoughtworks.aceleradora.solicitacao.dominio.SolicitacaoRepository;
import com.thoughtworks.aceleradora.email.component.EmailComponent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SolicitacaoControllerTest {

    private SolicitacaoController controller;

    private Solicitacao solicitacao;

    @Mock
    private Model model;

    @Mock
    private SolicitacaoRepository repositorio;

    @Mock
    private EmailComponent emailComponent;

    @Before
    public void setUp() {
        controller = new SolicitacaoController(repositorio, emailComponent);
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
        Acompanhante umAcompanhante = new Acompanhante("Alfredo", "Masculino", LocalDate.now(), umaSolicitacao);

        umaSolicitacao.setAcompanhantes(singletonList(umAcompanhante));

        String paginaRenderizada = controller.salvaSolicitacao(umaSolicitacao);

        verify(repositorio).save(umaSolicitacao);
        assertThat(umAcompanhante.getSolicitacao(), equalTo(umaSolicitacao));
        assertThat(paginaRenderizada, equalTo("redirect:/solicitacao/hospital/lista"));
    }

    @Test
    public void renderizaSolicitacoesDaCasaComTodasSolicitacoesPendentes() {
        List<Solicitacao> solicitacoesPendentes = asList(new Solicitacao(), new Solicitacao());
        when(repositorio.findAllByStatus(Solicitacao.Status.PENDENTE)).thenReturn(solicitacoesPendentes);

        String paginaRenderizada = controller.listaSolicitacoesDaCasa(model);

        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaSolicitacaoCasa"));
    }

    @Test
    public void deveRenderizarListaDeHospedes() {
        List<Solicitacao> solicitacoesAceitas = asList(new Solicitacao(), new Solicitacao());
        when(repositorio.findAllByStatus(Solicitacao.Status.HOSPEDE)).thenReturn(solicitacoesAceitas);

        String paginaRenderizada = controller.listaGerenciamentoHospede(model);


        verify(model).addAttribute("solicitacoesAceitas", solicitacoesAceitas);
        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaGerenciamentoHospede"));
    }

    @Test
    public void deveRenderizarListaSolicitacaoHospital() {
        Solicitacao a = new Solicitacao();
        Solicitacao b = new Solicitacao();
        a.setNomeSolicitante("Teste1");
        b.setNomeSolicitante("Teste2");
        Authentication auth = new UsernamePasswordAuthenticationToken("user1@example.com", "user1");

        List<Solicitacao> solicitacoesHospital = asList(a, b)
                .stream()
                .filter(solicitacao -> solicitacao.getNomeSolicitante().equals(auth.getName()))
                .collect(Collectors.toList());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        String paginaRenderizada = controller.listaSolicitacoesDoHospital(model);

        verify(model).addAttribute("solicitacoesHospital", solicitacoesHospital);
        assertThat(paginaRenderizada, equalTo("solicitacao/listagens/listaSolicitacaoHospital"));
    }

    @Test
    public void exibeDadosDoPacienteQuandoEsteExistir() {
        Solicitacao solicitacao = new Solicitacao();
        when(repositorio.findById(1L)).thenReturn(Optional.of(solicitacao));

        String paginaRenderizada = controller.mostraDadosPaciente(model, 1L);

        model.addAttribute("formatar", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        model.addAttribute("solicitante", solicitacao);

        assertThat(paginaRenderizada, equalTo("solicitacao/dadosSolicitacao"));
    }
}