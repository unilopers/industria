package com.industria.cafeeira.model.repository;

import com.industria.cafeeira.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByTipoOperacao(String tipoOperacao);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByCnpj(String cnpj);
    Optional<Cliente> findByRazaoSocial(String razaoSocial);
    Optional<Cliente> findByCodigo(String codigo);
}
