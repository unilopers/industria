package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.DTO.PedidoDto;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.repository.ClienteRepository;
import com.industria.cafeeira.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido cadastrarPedido(PedidoDto pedidoDto) {

        if (pedidoRepository.existsByCodigoPedido(pedidoDto.getCodigoPedido())) {
            throw new RuntimeException("Já existe um Pedido com esse código.");
        }

        Cliente cliente = clienteRepository.findById(pedidoDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(pedidoDto.getCodigoPedido());
        pedido.setCliente(cliente);

        try {
            return pedidoRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Pedido já cadastrado!");
        }
    }

    public void deletarPedido(String codigoPedido){
        Pedido pedido = pedidoRepository.findByCodigoPedido(codigoPedido)
                .orElseThrow(() -> new RuntimeException(
                        "Pedido com código " + codigoPedido + " não encontrado"
                ));

        pedidoRepository.delete(pedido);
    }


    public Pedido buscarPedidoPorCodigo(String codigoPedido){
        return pedidoRepository.findByCodigoPedido(codigoPedido)
                .orElseThrow(() ->
                        new RuntimeException("Pedido com código " + codigoPedido + " não encontrado")
                );
    }

    public List<Pedido> buscarClientePorCodigo(Long idCliente){
        return pedidoRepository.findByClienteId(idCliente);
    }

    public List<Pedido> consultarPedidos() {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();

        return (List<Pedido>) pedidos;
    }
}
