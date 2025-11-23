package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                        "Cliente com código " + codigo + " não encontrado"
                ));
        clienteRepository.delete(cliente);
    }

    public Cliente buscarPorCodigo(String codigo){
        return clienteRepository.findByCodigo(codigo)
                .orElseThrow(() ->
                        new RuntimeException("Usuário com código "+codigo+" não encontrado"));
    }

    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() ->
                        new RuntimeException("Usuário com Cpf "+cpf+" não encontrado"));
    }

    public Cliente buscarPorCnpj(String cnpj){
        return clienteRepository.findByCnpj(cnpj)
                .orElseThrow(() ->
                        new RuntimeException("Usuário com Cpnj "+cnpj+" não encontrado"));
    }

    public Cliente buscarRazaoSocial(String razaoSocial){
        return clienteRepository.findByRazaoSocial(razaoSocial)
                .orElseThrow(() ->
                        new RuntimeException("Usuário com razão social "+razaoSocial+" não encontrado"));
    }

    public List<Cliente> buscarPorOperacao(String tipoOperacao){
        return clienteRepository.findByTipoOperacao(tipoOperacao);
    }

    private String normalizar(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.replaceAll("\\D", "");
    }
}
