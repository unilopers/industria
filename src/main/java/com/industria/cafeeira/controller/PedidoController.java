package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.DTO.PedidoDto;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.service.PedidoService;
import com.industria.cafeeira.util.DefaultResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarPedido(@Valid @RequestBody PedidoDto pedidoDto) {
        try {
            Pedido pedido = pedidoService.cadastrarPedido(pedidoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        List<Pedido> pedidos = pedidoService.consultarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/buscar/idCliente/{idCliente}")
    public List<PedidoDto> consultarPorIdCliente(@PathVariable Long idCliente) {

        List<Pedido> pedidos = pedidoService.buscarClientePorCodigo(idCliente);

        return pedidos.stream()
                .map(pedido -> new PedidoDto(
                        pedido.getCodigoPedido(),
                        pedido.getCliente().getId()
                ))
                .toList();
    }

    @DeleteMapping("/deletar/{codigoPedido}")
    public ResponseEntity<?> deletarPedido(@PathVariable String codigoPedido){
        try {
            pedidoService.deletarPedido(codigoPedido);
            return ResponseEntity.ok(
                    DefaultResponse.construir(
                            HttpStatus.OK.value(),
                            "Pedido deletado com sucesso",
                            null));
        } catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(
                            HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping("/buscar/codigoPedido/{codigoPedido}")
    public ResponseEntity<?> getCodigoPedido(@PathVariable String codigoPedido){
        try{
            Pedido pedido = pedidoService.buscarPedidoPorCodigo(codigoPedido);
            return ResponseEntity.ok().body(pedido);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @PutMapping("/atualizar-codigo/{id}")
    public ResponseEntity<?> atualizarCodigoPedido(
            @PathVariable Long id,
            @RequestBody PedidoDto dto
    ) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarCodigoPedido(id, dto.getCodigoPedido());
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar-cliente/{id}")
    public ResponseEntity<?> atualizarClienteDoPedido(
            @PathVariable Long id,
            @RequestBody PedidoDto dto
    ) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarClienteDoPedido(id, dto);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
