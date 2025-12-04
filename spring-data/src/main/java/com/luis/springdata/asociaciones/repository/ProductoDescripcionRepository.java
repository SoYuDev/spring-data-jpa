package com.luis.springdata.asociaciones.repository;

import com.luis.springdata.asociaciones.model.ProductoDescripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDescripcionRepository extends JpaRepository<ProductoDescripcion, Long> {
}
