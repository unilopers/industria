package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.dto.PedidoDto;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.entities.Usuario;
import com.industria.cafeeira.model.repository.ClienteRepository;
import com.industria.cafeeira.model.repository.PedidoRepository;
import com.industria.cafeeira.model.repository.UsuarioRepository;
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
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pedido cadastrarPedido(PedidoDto pedidoDto) {

        if (pedidoRepository.existsByCodigoPedido(pedidoDto.getCodigoPedido())) {
            throw new RuntimeException("Já existe um Pedido com esse código.");
        }

        Cliente cliente = clienteRepository.findById(pedidoDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = usuarioRepository.findById(pedidoDto.getFkUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));



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

    public Pedido buscarPedidoPorUsuario(Long idUsuario) {
        return pedidoRepository.findByUsuarioId(idUsuario)
                .orElseThrow(() ->
                        new RuntimeException("Nenhum pedido encontrado para o usuário com ID " + idUsuario)
                );
    }

    public List<Pedido> buscarClientePorCodigo(Long idCliente){
        return pedidoRepository.findByClienteId(idCliente);
    }

    public List<Pedido> consultarPedidos() {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();

        return (List<Pedido>) pedidos;
    }

    public Pedido atualizarCodigoPedido(Long id, String novoCodigo) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedidoRepository.existsByCodigoPedido(novoCodigo)) {
            throw new RuntimeException("Já existe um pedido com esse código");
        }

        pedido.setCodigoPedido(novoCodigo);

        try {
            return pedidoRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar código do pedido");
        }
    }

    public Pedido atualizarUsuarioDoPedido(Long id, PedidoDto pedidoDto) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Usuario usuario = usuarioRepository.findById(pedidoDto.getFkUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (pedido.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Esse pedido já está vinculado a esse usuário");
        }

        pedido.setUsuario(usuario);

        try {
            return pedidoRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar o cliente do pedido");
        }
    }

    public Pedido atualizarClienteDoPedido(Long idPedido, PedidoDto pedidoDto) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(pedidoDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (pedido.getCliente().getId().equals(cliente.getId())) {
            throw new RuntimeException("Esse pedido já está vinculado a esse cliente");
        }

        pedido.setCliente(cliente);

        try {
            return pedidoRepository.save(pedido);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar o cliente do pedido");
        }
    }
}
