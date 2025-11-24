package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.dto.LoteRequestDTO;
import com.industria.cafeeira.model.entities.Lote;
import com.industria.cafeeira.model.repository.LoteRepository;
import com.industria.cafeeira.model.service.LoteService;
import com.industria.cafeeira.view.LoteResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoteResponseDTO> create(@RequestBody LoteRequestDTO lote){
        var resp = service.create(lote);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<LoteResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteResponseDTO> update(@PathVariable Long id, @RequestBody LoteRequestDTO dados){
        return ResponseEntity.ok(service.update(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();// ocorre 204
    }
}