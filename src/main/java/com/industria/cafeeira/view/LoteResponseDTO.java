package com.industria.cafeeira.view;

import java.math.BigDecimal;

public record LoteResponseDTO(
      Long id,
      String codigo,
      String tipo,
      BigDecimal quantidade,
      String dataVencimento,
      String descricao
) {}
