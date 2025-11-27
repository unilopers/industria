package com.industria.cafeeira.model.repository;

import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByCodigoPedido(String codigoPedido);
    boolean existsByCodigoPedido(String codigoPedido);

    List<Pedido> findByClienteId(Long idCliente);
    Optional<Pedido> findByUsuarioId(Long idUsuario);

}
