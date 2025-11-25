package com.industria.cafeeira.controller;

import com.industria.cafeeira.model.entities.Funcionario;
import org.springframework.web.bind.annotation.*;
import com.industria.cafeeira.model.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<?>> list(){
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(Long id){
        try {
            return ResponseEntity.ok(funcionarioService.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<?> atualizarFuncionario(Funcionario funcionario) {
        try {
            return ResponseEntity.ok(funcionarioService.atualizarFuncionario(funcionario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id)  {

        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PostMapping("/{matricula}")
    public ResponseEntity<?> novoFuncionario(Funcionario funcionario)  {

        try {
            funcionarioService.novoFuncionario(funcionario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}
