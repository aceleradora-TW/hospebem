package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class SolicitacaoTest {

    @Test
    public void formataDataHoraNoPadraoBrasileiro() {

        LocalDate data = LocalDate.parse("2018-12-21");
        LocalTime hora = LocalTime.of(10, 30);

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setDataAtualizacao(LocalDateTime.of(data, hora));

        assertThat(solicitacao.formataData(),  equalTo("21/12/2018 10:30"));
    }

    @Test
    public void retornaApenasUmTracoQuandoDataDeAtualizacaoEhNula() {
        Solicitacao solicitacao = new Solicitacao();

        assertThat(solicitacao.formataData(), equalTo("-"));
    }
}