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


}
