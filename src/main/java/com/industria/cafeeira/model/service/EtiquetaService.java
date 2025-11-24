package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.repository.EtiquetaRepository;
import com.industria.cafeeira.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta findById(Long id) throws Exception {
        return etiquetaRepository.findById(id).orElseThrow(() -> new Exception ("etiqueta com id " + id + " não encontrado"));
    }

    public Etiqueta create(Etiqueta etiqueta) throws Exception {
        return etiquetaRepository.save(etiqueta);
    }

    public Etiqueta update(Long id, Etiqueta etiqueta) throws Exception {

        Etiqueta existing = etiquetaRepository.findById(id).orElseThrow(() -> new Exception("etiqueta com id " + id + " não encontrado"));
        BeanUtils.copyProperties(etiqueta, existing, BeanUtilsHelper.getNullPropertyNames(etiqueta));

       return etiquetaRepository.save(existing);

    }

    public void delete(Long id) throws Exception {
        etiquetaRepository.findById(id).orElseThrow(() -> new Exception("etiqueta com id " + id + " não encontrado"));
        etiquetaRepository.deleteById(id);
    }

}
