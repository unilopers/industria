package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Funcionario;
import com.industria.cafeeira.model.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public boolean novoFuncionario(Funcionario funcionario) throws Exception{
        if (funcionario.getNome_completo() == null || funcionario.getNome_completo().isEmpty()){
            throw new Exception("O nome do Funcionário precisa ser preenchido corretamente");
        }

        funcionarioRepository.save(funcionario);
        return true;
    }
}
