package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Lote;
import com.industria.cafeeira.model.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService {

    @Autowired
    private LoteRepository repository;

    public List<Lote> findAll(){
        return repository.findAll();
    }

    public Lote findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado"));
    }

    public Lote save (Lote lote){
        return repository.save(lote);
    }

    public Lote update (Long id, Lote dados){
        Lote lote = findById(id);

        //CRUD simples, service recebe a entity diretamente
        lote.setCodigo(dados.getCodigo());
        lote.setFk_fornecedor(dados.getFk_fornecedor());
        lote.setFk_produto(dados.getFk_produto());
        lote.setTipo(dados.getTipo());
        lote.setQuantidade(dados.getQuantidade());
        lote.setData_vencimento(dados.getData_vencimento());
        lote.setDescricao(dados.getDescricao());

        return repository.save(lote);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
