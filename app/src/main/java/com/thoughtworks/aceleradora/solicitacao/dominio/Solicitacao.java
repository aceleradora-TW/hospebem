package com.thoughtworks.aceleradora.solicitacao.dominio;

import com.thoughtworks.aceleradora.quarto.dominio.Quarto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofPattern;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "solicitacoes")
public class Solicitacao {

    public enum Status{
        PENDENTE, ACEITO, NEGADO, HOSPEDE, EX_HOSPEDE;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String situacao;

    private String telefone;

    private String cadeirante;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    private Float peso;

    private String orgao;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "data_transplante")
    private LocalDate dataTransplante;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name="data_checkin")
    private LocalDate dataCheckin;

    @Column(name="data_checkout")
    private LocalDate dataCheckout;

    @OneToOne(cascade=ALL)
    private Endereco endereco;

    @OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "solicitacao")
    private List<Acompanhante> acompanhantes;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(name="nome_solicitante")
    private String nomeSolicitante;

    @Column(name="hospital_referencia")
    private String hospitalReferencia;

    private String email;

    @Column(name="telefone_solicitante")
    private String telefoneSolicitante;

    public Solicitacao() {
    }

    public Solicitacao(String nome,
                       String genero,
                       String situacao,
                       String telefone,
                       String cadeirante,
                       Status status,
                       float peso,
                       LocalDate dataNascimento,
                       LocalDate dataEntrada,
                       LocalDate dataSaida,
                       LocalDate dataTransplante,
                       LocalDateTime dataAtualizacao,
                       Endereco endereco,
                       List<Acompanhante> acompanhantes,
                       String orgao,
                       LocalDate dataCheckin,
                       LocalDate dataCheckout,
                       Quarto quarto,
                       String nomeSolicitante,
                       String hospitalReferencia,
                       String email,
                       String telefoneSolicitante)
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
        this.dataAtualizacao = dataAtualizacao;
        this.dataTransplante = dataTransplante;
        this.orgao = orgao;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.nomeSolicitante = nomeSolicitante;
        this.hospitalReferencia = hospitalReferencia;
        this.email = email;
        this.telefoneSolicitante = telefoneSolicitante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataTransplante() {
        return dataTransplante;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setDataTransplante(LocalDate dataTransplante) {
        this.dataTransplante = dataTransplante;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(LocalDate dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(LocalDate dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getHospitalReferencia() {
        return hospitalReferencia;
    }

    public void setHospitalReferencia(String hospitalReferencia) {
        this.hospitalReferencia = hospitalReferencia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneSolicitante() {
        return telefoneSolicitante;
    }

    public void setTelefoneSolicitante(String telefoneSolicitante) {
        this.telefoneSolicitante = telefoneSolicitante;
    }

    public String formataData() {
        return Optional
                .ofNullable(dataAtualizacao)
                .map(data -> data.format(ofPattern("dd/MM/yyyy HH:mm")))
                .orElse("-");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacao that = (Solicitacao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(genero, that.genero) &&
                Objects.equals(situacao, that.situacao) &&
                Objects.equals(telefone, that.telefone) &&
                Objects.equals(cadeirante, that.cadeirante) &&
                Objects.equals(status, that.status) &&
                Objects.equals(peso, that.peso) &&
                Objects.equals(orgao, that.orgao) &&
                Objects.equals(dataNascimento, that.dataNascimento) &&
                Objects.equals(dataEntrada, that.dataEntrada) &&
                Objects.equals(dataSaida, that.dataSaida) &&
                Objects.equals(dataTransplante, that.dataTransplante) &&
                Objects.equals(dataAtualizacao, that.dataAtualizacao) &&
                Objects.equals(dataCheckin, that.dataCheckin) &&
                Objects.equals(dataCheckout, that.dataCheckout) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(acompanhantes, that.acompanhantes) &&
                Objects.equals(quarto, that.quarto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, genero, situacao, telefone, cadeirante, status, peso, orgao, dataNascimento, dataEntrada, dataSaida, dataTransplante, dataAtualizacao, dataCheckin, dataCheckout, endereco, acompanhantes, quarto);
    }
}