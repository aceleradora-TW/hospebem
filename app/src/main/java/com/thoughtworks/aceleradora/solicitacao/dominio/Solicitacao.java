package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static com.thoughtworks.aceleradora.configuracao.Formatos.FORMATO_BRASILEIRO_DE_DATA;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private Solicitacao() {
    }

    public Solicitacao(
            String nome,
            @DateTimeFormat(pattern = FORMATO_BRASILEIRO_DE_DATA) LocalDate dataNascimento
    ) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
