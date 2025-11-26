package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.entities.BoletimTorrefacao;
import com.industria.cafeeira.model.service.BoletimTorrefacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boletim-torrefacao")
public class BoletimTorrefacaoController {

    @Autowired
    private BoletimTorrefacaoService service;

    @GetMapping
    public List<BoletimTorrefacao> listarTodos() {
        List<BoletimTorrefacao> boletins = service.listar();
        return boletins;
    }
    @GetMapping("/{id}")
    public BoletimTorrefacao buscaId(@PathVariable Long id) {
        BoletimTorrefacao boletim = service.buscarId(id);
        return boletim;
    }
    @PostMapping
    public BoletimTorrefacao cria(@RequestBody BoletimTorrefacao boletim) {
        BoletimTorrefacao novoBoletim = service.criar(boletim);
        return novoBoletim;
    }
    @PutMapping("/atualizar/{id}")
    public BoletimTorrefacao atualiza(@PathVariable Long id, @RequestBody BoletimTorrefacao dados) {
        BoletimTorrefacao boletimAtualizado = service.atualizar(id, dados);
        return boletimAtualizado;
    }
    @DeleteMapping("/deletar/{id}")
    public void deleta(@PathVariable Long id) {
        service.deleta(id);
    }
}
