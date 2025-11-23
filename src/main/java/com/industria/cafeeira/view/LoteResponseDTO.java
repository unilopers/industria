package com.industria.cafeeira.view;

import java.math.BigDecimal;
import java.util.Date;

public record LoteResponseDTO(
      Long id,
      String codigo,
      String tipo,
      BigDecimal quantidade,
      Date dataVencimento,
      String descricao
) {}
