package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente){
        if(clienteRepository.existsByCodigo(cliente.getCodigo())){
            throw new RuntimeException("Já existe um Cliente com esse código");
        } else if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("Já existe um Cliente com esse Cpf");
        } else if (clienteRepository.existsByCnpj(cliente.getCnpj())) {
            throw new RuntimeException("Já existe um Cliente com esse Cnpj");
        } else if (clienteRepository.existsByRazaoSocial(cliente.getRazaoSocial())) {
            throw new RuntimeException("Já existe um Cliente com essa Razão Social");
        }

        try{
            return clienteRepository.save(cliente);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Cliente já cadastrado");
        }
    }

    public void deletarCliente(String codigo){
        Cliente cliente = clienteRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente com código" + codigo + " não encontrado"
                ));
        clienteRepository.delete(cliente);
    }
}
