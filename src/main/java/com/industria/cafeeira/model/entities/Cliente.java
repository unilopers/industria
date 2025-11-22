package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "razaoSocial", unique = true)
    private String razaoSocial;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "tipoOperacao")
    private String tipoOperacao;
}
