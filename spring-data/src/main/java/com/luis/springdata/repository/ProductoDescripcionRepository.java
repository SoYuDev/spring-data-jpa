package com.luis.springdata.repository;

import com.luis.springdata.model.ProductoDescripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDescripcionRepository extends JpaRepository<ProductoDescripcion, Long> {
}
