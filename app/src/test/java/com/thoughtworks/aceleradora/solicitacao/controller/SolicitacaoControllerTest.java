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
        LocalDate dataEntrada= LocalDate.of(2010, 3, 7);
        LocalDate dataSaida= LocalDate.of(2010, 3, 7);
        LocalDate dataTransplante= LocalDate.of(2010, 3, 7);

        Endereco end = new Endereco("A", "B", "C", "D", "E");

        List<Acompanhante> acompanhantes = asList(
                new Acompanhante("Amanda", "F", dataNascimento, soli),
                new Acompanhante("Aline","F", dataNascimento, soli));

        Solicitacao solicitacao = new Solicitacao("joao", "masculino", "pos", "545214", "sim", 75, dataNascimento, dataEntrada, dataSaida, dataTransplante, end, "pendente", acompanhantes, "coracao");

        String paginaRenderizada = controller.salvaSolicitacao(model, solicitacao);
        verify(repositorio).save(solicitacao);
        assertThat(paginaRenderizada, equalTo("redirect:/"));
    }
}