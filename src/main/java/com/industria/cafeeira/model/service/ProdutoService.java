package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Produto;
import com.industria.cafeeira.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    public List<Produto> listarTodos() {
        List<Produto> produtos = repository.findAll();
        return produtos;
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> produtoOptional = repository.findById(id);
        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        } else {
            throw new RuntimeException("Produto com ID " + id + " não foi encontrado");
        }
    }

    public Produto criar(Produto produto) {
        if (produto.getCodigo() == null || produto.getCodigo().isEmpty()) {
            throw new RuntimeException("O código do produto não pode ser vazio");
        }
        if (produto.getName() == null || produto.getName().isEmpty()) {
            throw new RuntimeException("O nome do produto não pode ser vazio");
        }
        Produto novoProduto = repository.save(produto);
        return novoProduto;
    }

    public Produto atualizar(Long id, Produto dadosNovos) {
        Produto produtoExistente = buscarPorId(id);
        produtoExistente.setCodigo(dadosNovos.getCodigo());
        produtoExistente.setName(dadosNovos.getName());
        if (produtoExistente.getCodigo() == null || produtoExistente.getCodigo().isEmpty()) {
            throw new RuntimeException("O código do produto não pode ser vazio");
        }
        if (produtoExistente.getName() == null || produtoExistente.getName().isEmpty()) {
            throw new RuntimeException("O nome do produto não pode ser vazio");
        }
        Produto produtoAtualizado = repository.save(produtoExistente);
        return produtoAtualizado;
    }

    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }
}