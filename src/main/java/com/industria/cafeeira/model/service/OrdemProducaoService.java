package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.entities.OrdemProducao;
import com.industria.cafeeira.model.repository.EtiquetaRepository;
import com.industria.cafeeira.model.repository.OrdemProducaoRepository;
import com.industria.cafeeira.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemProducaoService {

    @Autowired
    private OrdemProducaoRepository ordemProducaoRepository;

    public List<OrdemProducao> findAll() {
        return ordemProducaoRepository.findAll();
    }

    public OrdemProducao findById(Long id) throws Exception {
        return ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception ("OrdemProducao com id " + id + " não encontrado"));
    }

    public OrdemProducao create(OrdemProducao ordem) throws Exception {
        return ordemProducaoRepository.save(ordem);
    }

    public OrdemProducao update(Long id, OrdemProducao ordem) throws Exception {

        OrdemProducao existing = ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception("OrdemProducao com id " + id + " não encontrado"));
        BeanUtils.copyProperties(ordem, existing, BeanUtilsHelper.getNullPropertyNames(ordem));

        return ordemProducaoRepository.save(existing);

    }

    public void delete(Long id) throws Exception {
        ordemProducaoRepository.findById(id).orElseThrow(() -> new Exception("OrdemProducao com id " + id + " não encontrado"));
        ordemProducaoRepository.deleteById(id);
    }


}
