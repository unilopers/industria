package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Etiqueta;
import com.industria.cafeeira.model.entities.Funcionario;
import com.industria.cafeeira.model.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) throws Exception {
        return funcionarioRepository.findById(id).orElseThrow(() -> new Exception ("O gitFuncionário com id " + id + " não encontrado"));
    }

    public boolean novoFuncionario(Funcionario funcionario) throws Exception{
        if (funcionario.getNome_completo() == null || funcionario.getNome_completo().isEmpty()){
            throw new Exception("O nome do Funcionário precisa ser preenchido corretamente");
        }

        funcionarioRepository.save(funcionario);
        return true;
    }

    public boolean deletarFuncionario(Long id) throws Exception{
        try {
            funcionarioRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception("Funcionário de id: " + id + " não encontrado");
        }
    }

    public boolean atualizarFuncionario(Funcionario funcionario) throws Exception{
        try {
            funcionarioRepository.save(funcionario);
            return true;
        } catch (Exception e){
            throw new Exception("Funcionario com o id: " + funcionario.getId() + " não encontrado" );
        }
    }
}
