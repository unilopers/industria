package com.industria.cafeeira.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


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

    @NotNull(message = "O código é obrigatória")
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

    @NotNull(message = "O tipo de Operação é obrigatória")
    @Column(name = "tipoOperacao", nullable = false)
    private String tipoOperacao;

}
