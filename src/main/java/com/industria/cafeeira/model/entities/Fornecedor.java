package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String nome_fantasia;

    private String razao_social;

    private String cpf_cnpj;

    private String inscricao_estadual;
    private String inscricao_municipal;
    private String logradouro;
    private boolean simples_nacional;

    public Fornecedor(){

    }

    public Fornecedor(
            Long id,
            String codigo,
            String nome_fantasia,
            String razao_social,
            String cpf_cnpj,
            String inscricao_estadual,
            String inscricao_municipal,
            String logradouro,
            boolean simples_nacional
    ){
        this. id = id;
        this.codigo = codigo;
        this.nome_fantasia = nome_fantasia;
        this. razao_social = razao_social;
        this.cpf_cnpj = cpf_cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.inscricao_municipal = inscricao_municipal;
        this.logradouro = logradouro;
        this.simples_nacional = simples_nacional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getInscricao_municipal() {
        return inscricao_municipal;
    }

    public void setInscricao_municipal(String inscricao_municipal) {
        this.inscricao_municipal = inscricao_municipal;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public boolean isSimples_nacional() {
        return simples_nacional;
    }

    public void setSimples_nacional(boolean simples_nacional) {
        this.simples_nacional = simples_nacional;
    }
}