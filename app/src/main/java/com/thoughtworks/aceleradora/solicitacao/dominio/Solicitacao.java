package com.thoughtworks.aceleradora.solicitacao.dominio;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String situacao;

    private String telefone;

    private String cadeirante;

    private String status="Pendente";


    private Float peso;

    private String orgao;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name="data_entrada")
    private LocalDate dataEntrada;

    @Column(name="data_saida")
    private LocalDate dataSaida;

    @Column(name="data_transplante")
    private LocalDate dataTransplante;

    @Column(name="data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToOne(cascade=ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "solicitacao")
    private List<Acompanhante> acompanhantes;

    public Solicitacao() {
    }


    public Solicitacao(String nome,
                       String genero,
                       String situacao,
                       String telefone,
                       String cadeirante,
                       String status,
                       float peso,
                       LocalDate dataNascimento,
                       LocalDate dataEntrada,
                       LocalDate dataSaida,
                       LocalDate dataTransplante,
                       LocalDateTime dataAtualizacao,
                       Endereco endereco,
                       List<Acompanhante> acompanhantes,
                       String orgao)
    {
        this.nome = nome;
        this.genero = genero;
        this.situacao = situacao;
        this.telefone = telefone;
        this.cadeirante = cadeirante;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.status = status;
        this.acompanhantes = acompanhantes;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.dataTransplante = dataTransplante;
        this.dataAtualizacao = dataAtualizacao;
        this.orgao = orgao;
    }
    public Long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCadeirante() {
        return cadeirante;
    }

    public void setCadeirante(String cadeirante) {
        this.cadeirante = cadeirante;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataSaida() { return dataSaida; }

    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }

    public LocalDate getDataTransplante() { return dataTransplante; }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataTransplante(LocalDate dataTransplante) { this.dataTransplante = dataTransplante; }

    public List<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setAcompanhantes(List<Acompanhante> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgao() { return orgao; }

    public void setOrgao(String orgao) { this.orgao = orgao; }

    public String formataData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = getDataAtualizacao();
        String formattedDateTime= dateTime.format(formatter);
        return formattedDateTime;
    }

    @PrePersist
    protected void onCreate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Solicitacao{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", situacao='" + situacao + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cadeirante='" + cadeirante + '\'' +
                ", peso=" + peso +
                ", orgao='" + orgao + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", dataTransplante=" + dataTransplante +
                ", endereco=" + endereco +
                ", status=" + status +
                ", acompanhantes=" + acompanhantes +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}