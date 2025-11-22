package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "fk_funcionario", nullable = false, unique = true)
    private Funcionario funcionario;
    private String login;
    private String senha;

}
