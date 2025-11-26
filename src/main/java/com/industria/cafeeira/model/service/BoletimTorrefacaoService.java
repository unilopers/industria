package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.BoletimTorrefacao;
import com.industria.cafeeira.model.repository.BoletimTorrefacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BoletimTorrefacaoService {

    @Autowired
    private BoletimTorrefacaoRepository repository;

    public List<BoletimTorrefacao> listar() {
        List<BoletimTorrefacao> boletins = repository.findAll();
        return boletins;
    }

    public BoletimTorrefacao buscarId(Long id) {
        Optional<BoletimTorrefacao> boletimOptional = repository.findById(id);
        if (boletimOptional.isPresent()) {
            return boletimOptional.get();
        } else {
            throw new RuntimeException("Não foi encontrado");
        }
    }

    public BoletimTorrefacao criar(BoletimTorrefacao boletim) {
        if (boletim.getCodigo() == null || boletim.getCodigo().isEmpty()) {
            throw new RuntimeException("Obrigatorio");
        }
        if (boletim.getQuantidade() != null && boletim.getQuantidade() <= 0) {
            throw new RuntimeException("Tem que ser maior que 0");
        }
        BoletimTorrefacao novoBoletim = repository.save(boletim);
        return novoBoletim;
    }

    public BoletimTorrefacao atualizar(Long id, BoletimTorrefacao dadosNovos) {
        BoletimTorrefacao boletimExistente = buscarId(id);
        boletimExistente.setCodigo(dadosNovos.getCodigo());
        boletimExistente.setOrdemServicos(dadosNovos.getOrdemServicos());
        boletimExistente.setTipoCafe(dadosNovos.getTipoCafe());
        boletimExistente.setQuantidade(dadosNovos.getQuantidade());
        if (boletimExistente.getCodigo() == null || boletimExistente.getCodigo().isEmpty()) {
            throw new RuntimeException("Obrigatorio");
        }
        BoletimTorrefacao boletimAtualizado = repository.save(boletimExistente);
        return boletimAtualizado;
    }

    public void deleta(Long id) {
        BoletimTorrefacao boletim = buscarId(id);
        repository.delete(boletim);
    }
}