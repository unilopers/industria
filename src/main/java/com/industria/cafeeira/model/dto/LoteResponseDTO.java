package com.industria.cafeeira.model.dto;

import java.math.BigDecimal;

public record LoteResponseDTO(
      Long id,
      String codigo,
      String tipo,
      BigDecimal quantidade,
      String dataVencimento,
      String descricao
) {}
