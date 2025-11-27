package com.industria.cafeeira.model.repository;

import com.industria.cafeeira.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
