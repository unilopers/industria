package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Funcionario;
import com.industria.cafeeira.model.entities.Usuario;
import com.industria.cafeeira.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) throws Exception {
        return usuarioRepository.findById(id).orElseThrow(() -> new Exception ("O Usuário com id " + id + " não foi encontrado"));
    }

    public boolean novoUsuario(Usuario usuario) throws Exception{
        if (usuario.getFuncionario() == null || usuario.getFuncionario().getNome_completo().isEmpty() || usuario.getFuncionario().getNome_completo() == null){
            throw new IllegalArgumentException("O nome do Usuário precisa ser preenchido corretamente!");
        }
        usuarioRepository.save(usuario);
        return true;
    }

    public boolean deletarUsuario(Long id) throws Exception{
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception("o usuário de id: " + id + " não foi encontrado.");
        }
    }

    public boolean atualizarUsuario(Usuario usuario) throws Exception{
        try {
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e){
            throw new Exception("O usuário de id: " + usuario.getId() + " não foi encontrado.");
        }
    }


}
