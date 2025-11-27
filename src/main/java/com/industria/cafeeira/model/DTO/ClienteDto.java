package com.industria.cafeeira.model.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClienteDto {

    @NotNull(message = "O código é obrigatória")
    private String codigo;

    private String razaoSocial;

    @Column(name = "logradouro")
    private String logradouro;

    @NotNull(message = "O tipo de Operação é obrigatória")
    private String tipoOperacao;
}
