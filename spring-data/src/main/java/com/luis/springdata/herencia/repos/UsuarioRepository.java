package com.luis.springdata.herencia.repos;

import com.luis.springdata.herencia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
