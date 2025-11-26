package com.industria.cafeeira.model.repository;

import com.industria.cafeeira.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
