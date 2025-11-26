package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.dto.FornecedorRequestDTO;
import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.service.ClienteService;
import com.industria.cafeeira.model.service.EtiquetaService;
import com.industria.cafeeira.view.FornecedorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Etiqueta etiqueta){
        try {
            var resp = etiquetaService.create(etiqueta);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<?>> list(){
        return ResponseEntity.ok(etiquetaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        try {
            return ResponseEntity.ok(etiquetaService.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody  Etiqueta etiqueta ){

        try {
            return ResponseEntity.ok(etiquetaService.update(id, etiqueta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {

        try {
            etiquetaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}
