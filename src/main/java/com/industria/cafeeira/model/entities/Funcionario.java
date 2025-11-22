package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Funcionario")
public class Funcionario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome_completo;
    private String cpf;
    private String rg;
    private String departamento;
    private String cargo;
    private int matricula;
    private String sindicato;
    private int centro_custo;
    private String filial;
    private LocalDate data_nascimento;
    private LocalDate data_admissao;
    private String logradouro;
    private LocalDate data_demissao;

    public Funcionario(String nome_completo, String cpf, String rg, String departamento, String cargo, int matricula, String sindicato, int centro_custo, String filial, LocalDate data_nascimento, LocalDate data_admissao, String logradouro, LocalDate data_demissao) {
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.rg = rg;
        this.departamento = departamento;
        this.cargo = cargo;
        this.matricula = matricula;
        this.sindicato = sindicato;
        this.centro_custo = centro_custo;
        this.filial = filial;
        this.data_nascimento = data_nascimento;
        this.data_admissao = data_admissao;
        this.logradouro = logradouro;
        this.data_demissao = data_demissao;
    }

    public Funcionario(){}

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public int getCentro_custo() {
        return centro_custo;
    }

    public void setCentro_custo(int centro_custo) {
        this.centro_custo = centro_custo;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public LocalDate getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(LocalDate data_admissao) {
        this.data_admissao = data_admissao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public LocalDate getData_demissao() {
        return data_demissao;
    }

    public void setData_demissao(LocalDate data_demissao) {
        this.data_demissao = data_demissao;
    }
}
