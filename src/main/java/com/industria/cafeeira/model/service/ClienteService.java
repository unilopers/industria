package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Cliente;
import com.industria.cafeeira.model.entities.Pedido;
import com.industria.cafeeira.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        // normaliza os valores
        cliente.setCpf(normalizar(cliente.getCpf()));
        cliente.setCnpj(normalizar(cliente.getCnpj()));
        cliente.setRazaoSocial(normalizar(cliente.getRazaoSocial()));

        if (cliente.getCpf() == null && cliente.getCnpj() == null) {
            throw new RuntimeException("É necessário informar CPF ou CNPJ.");
        }

        if (cliente.getCnpj() != null && (cliente.getRazaoSocial() == null || cliente.getRazaoSocial().isBlank())) {
            throw new RuntimeException("Razão Social é obrigatória quando CNPJ é informado.");
        }

        if(clienteRepository.existsByCodigo(cliente.getCodigo())){
            throw new RuntimeException("Já existe um Cliente com esse código");
        }
        if(cliente.getCpf() != null && clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("Já existe um Cliente com esse CPF");
        }
        if(cliente.getCnpj() != null && clienteRepository.existsByCnpj(cliente.getCnpj())) {
            throw new RuntimeException("Já existe um Cliente com esse CNPJ");
        }
        if(cliente.getRazaoSocial() != null && clienteRepository.existsByRazaoSocial(cliente.getRazaoSocial())) {
            throw new RuntimeException("Já existe um Cliente com essa Razão Social");
        }

        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
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

        public List<Cliente> consultarClientes() {
        Iterable<Cliente> clientes = clienteRepository.findAll();

        return (List<Cliente>) clientes;
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

    public String normalizar(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.replaceAll("\\D", "");
    }

    public Cliente atualizarTipoOperacao(Long id, String tipoOperacaoNovo) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setTipoOperacao(tipoOperacaoNovo);

        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar tipo de operação do cliente");
        }
    }
}
