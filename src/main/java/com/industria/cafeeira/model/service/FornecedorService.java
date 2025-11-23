package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.dto.FornecedorRequestDTO;
import com.industria.cafeeira.model.entities.Fornecedor;
import com.industria.cafeeira.model.entities.Lote;
import com.industria.cafeeira.model.repository.FornecedorRepository;
import com.industria.cafeeira.view.FornecedorMapper;
import com.industria.cafeeira.view.FornecedorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;
    @Autowired
    private FornecedorMapper mapper;

    public List<FornecedorResponseDTO>findAll(){
       return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public FornecedorResponseDTO findById(Long id){
       Fornecedor fornecedor = repository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));
       return mapper.toResponse(fornecedor);
    }

    public FornecedorResponseDTO create(FornecedorRequestDTO req){
       Fornecedor fornecedor = mapper.toEntity(req);

        fornecedor.setCodigo("COD-" + System.currentTimeMillis());

        Fornecedor salvo = repository.save(fornecedor);

        return mapper.toResponse(salvo);
    }

    public FornecedorResponseDTO update(Long id, FornecedorRequestDTO dados){
      Fornecedor existente = repository.findById(id)
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!"));

      existente.setNome_fantasia(dados.nome_fantasia());
      existente.setRazao_social(dados.razao_social());
      existente.setCpf_cnpj(dados.cpf_cnpj());
      existente.setInscricao_estadual(dados.inscricao_estadual());
      existente.setInscricao_municipal(dados.inscricao_municipal());
      existente.setLogradouro(dados.logradouro());
      existente.setSimples_nacional(dados.simples_nacional());

      Fornecedor salvo = repository.save(existente);

      return mapper.toResponse(salvo);
    }

    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!");
        }
        repository.deleteById(id);
    }
}
