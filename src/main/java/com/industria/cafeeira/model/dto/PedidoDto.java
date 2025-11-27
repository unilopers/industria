package com.industria.cafeeira.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PedidoDto {
    @NotNull(message = "O código do pedido é obrigatório")
    private String codigoPedido;
    @NotNull(message = "O cliente é obrigatório")
    private Long idCliente;
}
