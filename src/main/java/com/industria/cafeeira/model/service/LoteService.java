package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.dto.LoteRequestDTO;
import com.industria.cafeeira.model.entities.Lote;
import com.industria.cafeeira.model.repository.LoteRepository;
import com.industria.cafeeira.view.LoteMapper;
import com.industria.cafeeira.view.LoteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoteService {

    @Autowired
    private LoteRepository repository;
    @Autowired
    private LoteMapper mapper;

    public List<LoteResponseDTO> findAll(){
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public LoteResponseDTO findById(Long id){
        Lote lote = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado!"));
        return mapper.toResponse(lote);
    }

    public LoteResponseDTO create (LoteRequestDTO req){
        Lote lote = mapper.toEntity(req);

        lote.setCodigo("COD-" + System.currentTimeMillis());

        Lote salvo = repository.save(lote);

        return mapper.toResponse(salvo);
    }

    public LoteResponseDTO update (Long id, LoteRequestDTO dados){
        Lote existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado!"));

        //CRUD simples, service recebe a entity diretamente
        existente.setFk_fornecedor(dados.fkFornecedor());
        existente.setFk_produto(dados.fkProduto());
        existente.setTipo(dados.tipo());
        existente.setQuantidade(dados.quantidade());
        existente.setData_vencimento(dados.dataVencimento());
        existente.setDescricao(dados.descricao());

        Lote salvo = repository.save(existente);

        return mapper.toResponse(salvo);
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote não encontrado!");
        }
        repository.deleteById(id);
    }
}
