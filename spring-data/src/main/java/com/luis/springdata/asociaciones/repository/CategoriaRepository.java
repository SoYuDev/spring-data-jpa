package com.luis.springdata.asociaciones.repository;

import com.luis.springdata.asociaciones.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("""
            select c from Categoria c
                join fetch c.productos
                where c.id = ?1
            """)
    Optional<Categoria> findCategoriaByIdWithProductos(Long id);
}
