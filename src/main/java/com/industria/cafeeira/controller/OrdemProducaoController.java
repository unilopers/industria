package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.entities.OrdemProducao;
import com.industria.cafeeira.model.service.EtiquetaService;
import com.industria.cafeeira.model.service.OrdemProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ordem-producao")
public class OrdemProducaoController {

    @Autowired
    private OrdemProducaoService ordemProducaoService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody OrdemProducao ordem){
        try {
            var resp = ordemProducaoService.create(ordem);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<?>> list(){
        return ResponseEntity.ok(ordemProducaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id){
        try {
            return ResponseEntity.ok(ordemProducaoService.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody  OrdemProducao ordem ){

        try {
            return ResponseEntity.ok(ordemProducaoService.update(id, ordem));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {

        try {
            ordemProducaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}
