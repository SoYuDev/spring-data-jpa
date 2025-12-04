package com.luis.springdata.herencia;

import com.luis.springdata.herencia.model.Administrador;
import com.luis.springdata.herencia.model.Usuario;
import com.luis.springdata.herencia.repos.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EjemploHerencia {

    private final UsuarioRepository usuarioRepository;

    @PostConstruct
    public void run() {

        Usuario usuario = Usuario.builder()
                .username("luisi")
                .password("12345")
                .email("luisi@luisi.com")
                .build();

        usuarioRepository.save(usuario);

        Administrador administrador = Administrador.builder()
                .username("admin")
                .password("admin")
                .email("admin@asd.net")
                .superadmin(true)
                .build();

        usuarioRepository.save(administrador);


    }

}
