package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static com.thoughtworks.aceleradora.solicitacao.dominio.Solicitacao.Status.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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
    public void filtraSolicitacoesAceitasEPendentesETrataDatasNulas() {
        Solicitacao aceita = new Solicitacao() {{
            setNome("Aceita");
            setStatus(ACEITO);

        }};

        Solicitacao negada = new Solicitacao() {{
            setNome("Negada");
            setStatus(NEGADO);
            setGenero("?");
            setDataNascimento(LocalDate.parse("1998-12-20"));
            setSituacao("PRE_TRANSPLANTE");
            setOrgao("Apendice");
            setCadeirante("Sim");
            setTelefone("7777");
            setDataEntrada(LocalDate.parse("2018-12-20"));
            setDataSaida(LocalDate.parse("2019-01-20"));

            setEndereco(new Endereco() {{
                setRua("Rua dos Sabias");
                setCidade("Alvorada");
                setBairro("Jardim Algarve");
                setNumero("1000");
                setUf("RS");
            }});

            setAcompanhantes(singletonList(
                    new Acompanhante() {{
                        setNome("Acompanhante Negado");
                        setGenero("?");
                        setDataNascimento(LocalDate.parse("1999-12-20"));
                    }}
            ));
        }};

        Solicitacao hospede = new Solicitacao() {{
            setNome("Hospede");
            setStatus(HOSPEDE);
            setGenero("?");
            setDataNascimento(LocalDate.parse("1998-12-21"));
            setSituacao("PRE_TRANSPLANTE");
            setOrgao("Nariz");
            setCadeirante("Nao");
            setTelefone("8888");
            setDataEntrada(LocalDate.parse("2018-12-21"));
            setDataSaida(LocalDate.parse("2019-01-20"));

            setEndereco(new Endereco() {{
                setRua("Rua das Caturritas");
                setCidade("Alvorada");
                setBairro("Jardim Algarve");
                setNumero("1000");
                setUf("RS");
            }});

            setAcompanhantes(singletonList(
                    new Acompanhante() {{
                        setNome("Acompanhante Hospede");
                        setGenero("?");
                        setDataNascimento(LocalDate.parse("1999-12-20"));
                    }}
            ));
        }};

        Solicitacao exHospede = new Solicitacao() {{
            setNome("ExHospede");
            setStatus(EX_HOSPEDE);
            setGenero("?");
            setDataNascimento(LocalDate.parse("1998-12-22"));
            setSituacao("PRE_TRANSPLANTE");
            setOrgao("Pele");
            setCadeirante("Sim");
            setTelefone("9999");
            setDataEntrada(LocalDate.parse("2018-12-22"));
            setDataSaida(LocalDate.parse("2019-01-20"));

            setEndereco(new Endereco() {{
                setRua("Rua dos biguas");
                setCidade("Alvorada");
                setBairro("Jardim Algarve");
                setNumero("1000");
                setUf("RS");
            }});

            setAcompanhantes(singletonList(
                    new Acompanhante() {{
                        setNome("Acompanhante Ex Hospede");
                        setGenero("?");
                        setDataNascimento(LocalDate.parse("1999-12-20"));
                    }}
            ));
        }};

        Solicitacao pendente = new Solicitacao() {{
            setNome("Pendente");
            setStatus(PENDENTE);
        }};

        when(repository.findAll()).thenReturn(asList(aceita, negada, hospede, exHospede, pendente));

        String resultado = service.solicitacoesRelatorio();

        assertThat(resultado, equalTo(
                "Nome,Status,Gênero,Data de Nascimento,Situacao,Órgão,Rua,Número," +
                        "Cidade,Bairro,UF,Cadeirante,Telefone,Data de Entrada,Data de Saída," +
                        "Nome Acompanhante,Gênero Acompanhante,Data Nascimento Acompanhante," +
                        "Nome Acompanhante2,Gênero Acompanhante2,Data Nascimento Acompanhante2" +
                        "\n" +
                        "Negada,NEGADO,?,1998-12-20,PRE_TRANSPLANTE,Apendice,Sim,7777," +
                        "2018-12-20,2019-01-20,Rua dos Sabias,1000,Alvorada,Jardim Algarve,RS," +
                        "Acompanhante Negado,?,1999-12-20" +
                        "\n" +
                        "Hospede,HOSPEDE,?,1998-12-21,PRE_TRANSPLANTE,Nariz,Nao,8888,2018-12-21," +
                        "2019-01-20,Rua das Caturritas,1000,Alvorada,Jardim Algarve,RS," +
                        "Acompanhante Hospede,?,1999-12-20" +
                        "\n" +
                        "ExHospede,EX_HOSPEDE,?,1998-12-22,PRE_TRANSPLANTE,Pele,Sim,9999,2018-12-22," +
                        "2019-01-20,Rua dos biguas,1000,Alvorada,Jardim Algarve,RS," +
                        "Acompanhante Ex Hospede,?,1999-12-20"
        ));
    }
}