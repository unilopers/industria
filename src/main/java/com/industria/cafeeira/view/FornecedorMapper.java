package com.industria.cafeeira.view;

import com.industria.cafeeira.model.dto.FornecedorRequestDTO;
import com.industria.cafeeira.model.entities.Fornecedor;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper {
    public Fornecedor toEntity(FornecedorRequestDTO req){
        Fornecedor f = new Fornecedor();
        f.setNome_fantasia(req.nome_fantasia());
        f.setRazao_social(req.razao_social());
        f.setCpf_cnpj(req.cpf_cnpj());
        f.setInscricao_estadual(req.inscricao_estadual());
        f.setInscricao_municipal(req.inscricao_municipal());
        f.setLogradouro(req.logradouro());
        f.setSimples_nacional(req.simples_nacional());
        return f;
    }

    public FornecedorResponseDTO toResponse(Fornecedor f){
        return new FornecedorResponseDTO(
                f.getId(),
                f.getCodigo(),
                f.getNome_fantasia(),
                f.getRazao_social(),
                f.getCpf_cnpj(),
                f.getInscricao_estadual(),
                f.getInscricao_municipal(),
                f.getLogradouro(),
                f.isSimples_nacional()
        );
    }
}
