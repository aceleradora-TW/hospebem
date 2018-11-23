package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SolicitacaoCsvServiceTest {

    @Mock
    private SolicitacaoRepository repository;

    private SolicitacaoCsvService service;

    @Before
    public void setUp() {
        service = new SolicitacaoCsvService(repository);
    }

    @Test
    public void constroiConteudoDoCsvComSolicitacoesNegadas() {
        Solicitacao umaSolicitacao = new Solicitacao();
        Solicitacao outraSolicitacao = new Solicitacao();

        umaSolicitacao.setNome("UmaSolicitacao");
        umaSolicitacao.setSituacao("UmaSituacao");
        umaSolicitacao.setOrgao("Orelha");

        outraSolicitacao.setNome("OutraSolicitacao");
        outraSolicitacao.setSituacao("OutraSituacao");
        outraSolicitacao.setOrgao("Nariz");

        when(repository.findAllByStatus("negada")).thenReturn(asList(umaSolicitacao, outraSolicitacao));

        String resultado = service.solicitacoesNegadas();

        assertThat(resultado, equalTo("UmaSolicitacao,UmaSituacao,Orelha\nOutraSolicitacao,OutraSituacao,Nariz"));
    }
}