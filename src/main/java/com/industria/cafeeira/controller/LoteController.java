package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.entities.Lote;
import com.industria.cafeeira.model.repository.LoteRepository;
import com.industria.cafeeira.model.service.LoteService;
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
    public Lote create(@RequestBody Lote lote){
        return service.save(lote);
    }

    @GetMapping
    public List<Lote> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Lote findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Lote update(@PathVariable Long id, @RequestBody Lote dados){
        return service.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}