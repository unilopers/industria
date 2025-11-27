package com.industria.cafeeira.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public record LoteRequestDTO(
        Long fkFornecedor,
        Long fkProduto,
        String tipo,
        BigDecimal quantidade,
        Date dataVencimento,
        String descricao) {}