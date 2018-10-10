package com.thoughtworks.aceleradora.solicitacao.dominio;

import com.thoughtworks.aceleradora.acompanhante.dominio.Acompanhante;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "solicitacao")
    private List<Acompanhante> acompanhantes;

    public Solicitacao() {
    }

    public Solicitacao(String nome, List<Acompanhante> acompanhantes) {
        this.nome = nome;
        this.acompanhantes = acompanhantes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }
}
