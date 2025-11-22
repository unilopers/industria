package com.industria.cafeeira.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoteRequestDTO(
        Long fkFornecedor,
        Long fkProduto,
        String tipo,
        BigDecimal quantidade,
        LocalDate dataVencimento,
        String descricao) {}