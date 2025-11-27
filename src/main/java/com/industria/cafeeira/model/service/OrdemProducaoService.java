package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.entities.OrdemProducao;
import com.industria.cafeeira.model.repository.EtiquetaRepository;
import com.industria.cafeeira.model.repository.OrdemProducaoRepository;
import com.industria.cafeeira.util.BeanUtilsHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemProducaoService {

    @Autowired
    private OrdemProducaoRepository ordemProducaoRepository;

    @Autowired
    private EntityManager entityManager;

    public List<OrdemProducao> findAll() {
        return ordemProducaoRepository.findAll();
    }

    public OrdemProducao findById(Long id) throws Exception {
        return ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception ("OrdemProducao com id " + id + " não encontrado"));
    }

    @Transactional
    public OrdemProducao create(OrdemProducao ordem) throws Exception {

        ordemProducaoRepository.saveAndFlush(ordem);

        Optional<OrdemProducao> optionalEntity = ordemProducaoRepository.findById(ordem.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new Exception("ordem com id " + ordem.getId() + " não encontrada"));
    }

    @Transactional
    public OrdemProducao update(Long id, OrdemProducao ordem) throws Exception {

        OrdemProducao existing = ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception("OrdemProducao com id " + id + " não encontrado"));
        BeanUtils.copyProperties(ordem, existing, BeanUtilsHelper.getNullPropertyNames(ordem));

        ordemProducaoRepository.saveAndFlush(existing);

        Optional<OrdemProducao> optionalEntity = ordemProducaoRepository.findById(existing.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new Exception("ordem com id " + ordem.getId() + " não encontrada"));


    }

    public void delete(Long id) throws Exception {
        ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception("OrdemProducao com id " + id + " não encontrado"));
        ordemProducaoRepository.deleteById(id);
    }


}
