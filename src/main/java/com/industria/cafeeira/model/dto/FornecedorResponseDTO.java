package com.industria.cafeeira.model.dto;

public record FornecedorResponseDTO(
        Long id,
        String codigo,
        String nome_fantasia,
        String razao_social,
        String cpf_cnpj,
        String inscricao_estadual,
        String inscricao_municipal,
        String logradouro,
        boolean simples_nacioanl
) {}
