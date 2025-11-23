package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.DTO.PedidoDto;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.service.PedidoService;
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
}
