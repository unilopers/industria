package com.industria.cafeeira.controller;

import com.industria.cafeeira.util.DefaultResponse;
import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.Cleaner;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        List<Cliente> clientes = clienteService.consultarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try{
            String cpfNormalizado = clienteService.normalizar(cliente.getCpf());
            String cnpjNormalizado = clienteService.normalizar(cliente.getCnpj());
            String razaoSocialNormalizado = clienteService.normalizar(cliente.getRazaoSocial());

            cliente.setCpf(cpfNormalizado);
            cliente.setCnpj(cnpjNormalizado);
            cliente.setRazaoSocial(razaoSocialNormalizado);

            Cliente salvo = clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
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

    @GetMapping("buscar/cpf/{cpf}")
    public ResponseEntity<?> getCpf(@PathVariable String cpf){
        try{
            Cliente cliente = clienteService.buscarPorCpf(cpf);
            return ResponseEntity.ok().body(cliente);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping("buscar/cnpj/{cnpj}")
    public ResponseEntity<?> getCnpj(@PathVariable String cnpj){
        try{
            Cliente cliente = clienteService.buscarPorCnpj(cnpj);
            return ResponseEntity.ok().body(cliente);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping("buscar/razaoSocial/{razaoSocial}")
    public ResponseEntity<?> getRazaoSocial(@PathVariable String razaoSocial){
        try{
            Cliente cliente = clienteService.buscarRazaoSocial(razaoSocial);
            return ResponseEntity.ok().body(cliente);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    DefaultResponse.construir(HttpStatus.NOT_FOUND.value(),
                            e.getMessage(),
                            null));
        }
    }

    @GetMapping("/buscar/tipoOperacao/{tipoOperacao}")
    public Map<String, List<Map<String, String>>> getTipoOperacao(@PathVariable String tipoOperacao) {

        List<Cliente> clientes = clienteService.buscarPorOperacao(tipoOperacao);

        List<Map<String, String>> listaFormatada = clientes.stream()
                .map(cliente -> Map.ofEntries(
                        Map.entry("codigo", String.valueOf(cliente.getCodigo())),
                        Map.entry("cpf", cliente.getCpf() != null ? cliente.getCpf() : ""),
                        Map.entry("cnpj", cliente.getCnpj() != null ? cliente.getCnpj() : ""),
                        Map.entry("razaoSocial", cliente.getRazaoSocial() != null ? cliente.getRazaoSocial() : ""),
                        Map.entry("logradouro", cliente.getLogradouro() != null ? cliente.getLogradouro() : ""),
                        Map.entry("tipoOperacao", cliente.getTipoOperacao() != null ? cliente.getTipoOperacao() : "")
                ))
                .toList();

        // retorna {"estabelecimento": [ ... ]}
        return Map.of("tipoOperacao", listaFormatada);
    }
}
