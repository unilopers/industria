package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Fornecedor;
import com.industria.cafeeira.model.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    public List<Fornecedor>findAll(){
        return repository.findAll();
    }

    public Fornecedor findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));
    }

    public Fornecedor save(Fornecedor fornecedor){
        return repository.save(fornecedor);
    }

    public Fornecedor update(Long id, Fornecedor dados){
        Fornecedor fornecedor = findById(id);

        //CRUD simples, service recebe a entity diretamente
        fornecedor.setNome_fantasia(dados.getNome_fantasia());
        fornecedor.setRazao_social(dados.getRazao_social());
        fornecedor.setCpf_cnpj(dados.getCpf_cnpj());
        fornecedor.setInscricao_estadual(dados.getInscricao_estadual());
        fornecedor.setInscricao_municipal(dados.getInscricao_municipal());
        fornecedor.setLogradouro(dados.getLogradouro());
        fornecedor.setSimples_nacional(dados.isSimples_nacional());

        return repository.save(fornecedor);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
