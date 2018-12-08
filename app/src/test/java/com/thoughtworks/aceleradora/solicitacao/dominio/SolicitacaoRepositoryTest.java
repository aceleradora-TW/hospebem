package com.thoughtworks.aceleradora.solicitacao.dominio;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SolicitacaoRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private SolicitacaoRepository repository;

    @Before
    public void setUp() {

        Endereco endereco = new Endereco();
//        endereco.setId(1L);

        Quarto quarto = new Quarto();
//        quarto.setId(1L);

        Acompanhante acompanhante = new Acompanhante();
//        acompanhante.setId(1L);

        Solicitacao s = new Solicitacao();
        s.setId(1L);
        s.setAcompanhantes(singletonList(acompanhante));
        s.setEndereco(endereco);
        s.setQuarto(quarto);

        manager.persist(s);
    }

    @Test
    public void bla() {
        List<Solicitacao> solicitacoes = repository.findAll();

        assertThat(solicitacoes.isEmpty(), equalTo(true));
    }
}