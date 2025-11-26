package com.industria.cafeeira.view;

import com.industria.cafeeira.model.dto.LoteRequestDTO;
import com.industria.cafeeira.model.entities.Lote;
import org.springframework.stereotype.Component;

@Component
public class LoteMapper {
    public Lote toEntity(LoteRequestDTO req){
        Lote lote = new Lote();
        lote.setFk_fornecedor(req.fkFornecedor());
        lote.setFk_produto(req.fkProduto());
        lote.setTipo(req.tipo());
        lote.setQuantidade(req.quantidade());
        lote.setData_vencimento(req.dataVencimento());
        lote.setDescricao(req.descricao());
     return lote;
    }

    public LoteResponseDTO toResponse (Lote lote){
        return new LoteResponseDTO(
                lote.getId(),
                lote.getCodigo(),
                lote.getTipo(),
                lote.getQuantidade(),
                lote.getData_vencimento(),
                lote.getDescricao()
        );
    }
}
