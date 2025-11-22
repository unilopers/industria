package com.industria.cafeeira.model.service;

import com.industria.cafeeira.model.entities.Usuario;
import com.industria.cafeeira.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean novoUsuario(Usuario usuario) throws Exception{
        if (usuario.getFuncionario() == null || usuario.getFuncionario().getNome_completo().isEmpty() || usuario.getFuncionario().getNome_completo() == null){
            throw new IllegalArgumentException("O nome do Usuário precisa ser preenchido corretamente");
        }
        usuarioRepository.save(usuario);
        return true;
    }


}
