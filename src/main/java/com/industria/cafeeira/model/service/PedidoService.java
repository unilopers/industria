package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.repository.ClienteRepository;
import com.industria.cafeeira.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido cadastrarPedido(Pedido pedido){
        if(pedidoRepository.existsByCodigoPedido(pedido.getCodigoPedido())){
            throw new RuntimeException("Já existe um Pedido com essa código.");
        }
        Long idCliente = pedido.getCliente().getId();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedido.setCliente(cliente);
        try {
            return pedidoRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Pedido já cadastrado.");
        }
    }
}
