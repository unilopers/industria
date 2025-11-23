package com.industria.cafeeira.controller;


import com.industria.cafeeira.model.dto.FornecedorRequestDTO;
import com.industria.cafeeira.model.entities.Fornecedor;
import com.industria.cafeeira.model.service.FornecedorService;
import com.industria.cafeeira.view.FornecedorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> create (@RequestBody FornecedorRequestDTO fornecedor){
        var resp = service.create(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> update(@PathVariable Long id, @RequestBody FornecedorRequestDTO dados ){
        return ResponseEntity.ok(service.update(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //ocorre 204
    }
}