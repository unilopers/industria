package com.industria.cafeeira.controller;


import com.industria.cafeeira.model.entities.Fornecedor;
import com.industria.cafeeira.model.service.FornecedorService;
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
    public Fornecedor create (@RequestBody Fornecedor fornecedor){
        return service.create(fornecedor);
    }

    @GetMapping
    public List<Fornecedor> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Fornecedor getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Fornecedor update(@PathVariable Long id, @RequestBody Fornecedor dados ){
        return service.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
