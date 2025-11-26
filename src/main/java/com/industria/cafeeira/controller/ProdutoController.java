package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.entities.Produto;
import com.industria.cafeeira.model.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @GetMapping
    public List<Produto> listarTodos() {
        List<Produto> produtos = service.listarTodos();
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto buscarId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return produto;
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        Produto novoProduto = service.criar(produto);
        return novoProduto;
    }

    @PutMapping("/atualizar/{id}")
    public Produto atualiza(@PathVariable Long id, @RequestBody Produto dados) {
        Produto produtoAtualizado = service.atualizar(id, dados);
        return produtoAtualizado;
    }

    @DeleteMapping("/deletar/{id}")
    public void deleta(@PathVariable Long id) {
        service.deletar(id);
    }
}
