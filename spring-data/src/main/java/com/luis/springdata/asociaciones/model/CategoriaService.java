package com.luis.springdata.asociaciones.model;

import com.luis.springdata.asociaciones.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    // ¡OJO! Es org.springframework.transaction.annotation.Transactional
    // No jakarta.transaction.Transactional
    @Transactional(readOnly = true)
    public Optional<Categoria> cargarCategoria(Long id) {
        Optional<Categoria> categoria = repository.findById(id);

        if (categoria.isPresent()) {
            // Forzamos la carga de los productos, ya que el fetching está en Lazy
            categoria.get().getProductos().size();
        }

        return categoria;
    }

}
