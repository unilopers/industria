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

        cliente.setCpf(normalizarCpfECnpj(cliente.getCpf()));
        cliente.setCnpj(normalizarCpfECnpj(cliente.getCnpj()));
        cliente.setRazaoSocial(normalizarTexto(cliente.getRazaoSocial()));

        if (cliente.getCpf() == null && cliente.getCnpj() == null) {
            throw new RuntimeException("É necessário informar CPF ou CNPJ.");
        }

        if (cliente.getCpf() != null && !validarCPF(cliente.getCpf())) {
            throw new RuntimeException("CPF inválido");
        }

        if (cliente.getCnpj() != null && !validarCNPJ(cliente.getCnpj())) {
            throw new RuntimeException("CNPJ inválido");
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

        if(cliente.getCnpj() != null && clienteRepository.existsByRazaoSocial(cliente.getRazaoSocial())) {
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

    public Cliente atualizarCodigoCliente(Long id, String codigoNovo) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (clienteRepository.existsByCodigo(codigoNovo)) {
            throw new RuntimeException("Já existe um cliente com esse código");
        }

        cliente.setCodigo(codigoNovo);

        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar o código do cliente");
        }
    }

    public Cliente atualizarRazaoSocial(String cnpj, String razaoSocialNovo) {

        Cliente cliente = clienteRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Cnpj não encontrada"));

        if (clienteRepository.existsByRazaoSocial(razaoSocialNovo)) {
            throw new RuntimeException("Já existe um cliente com essa razão social");
        }

        cliente.setRazaoSocial(razaoSocialNovo);

        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar a Razão Social do cliente");
        }
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        // Rejeita CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            if (primeiroDigito != (cpf.charAt(9) - '0')) return false;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            return segundoDigito == (cpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) return false;

        // Rejeita CNPJs com todos os dígitos iguais
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos1[i];
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            if (primeiroDigito != (cnpj.charAt(12) - '0')) return false;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos2[i];
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            return segundoDigito == (cnpj.charAt(13) - '0');

        } catch (Exception e) {
            return false;
        }
    }

    public String normalizarCpfECnpj(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.replaceAll("\\D", "");
    }

    public String normalizarTexto(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.trim();
    }
}
