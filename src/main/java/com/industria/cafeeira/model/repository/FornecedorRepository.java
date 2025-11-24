package com.industria.cafeeira.model.repository;

import com.industria.cafeeira.model.entities.Fornecedor;
import com.industria.cafeeira.view.FornecedorResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

}
