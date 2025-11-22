package com.industria.cafeeira.controller;

import com.industria.cafeeira.util.DefaultResponse;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> setCadastrarCliente(@Valid @RequestBody Cliente cliente){
        try{
            Cliente clientes = clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clientes);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<?> deletarCliente(@PathVariable String codigo){
        try{
            clienteService.deletarCliente(codigo);
            return ResponseEntity.ok(
                    DefaultResponse.construir(
                            HttpStatus.OK.value(),
                            "Usuário deletado com sucesso",
                            null));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(
                            HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping("buscar/codigo/{codigo}")
    public ResponseEntity<?> getCodigo(@PathVariable String codigo){
        try{
            Cliente cliente = clienteService.buscarPorCodigo(codigo);
            return ResponseEntity.ok().body(cliente);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }
}
