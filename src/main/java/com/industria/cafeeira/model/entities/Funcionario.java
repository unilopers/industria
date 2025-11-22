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



}
