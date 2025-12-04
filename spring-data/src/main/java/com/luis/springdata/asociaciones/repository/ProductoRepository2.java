package com.luis.springdata.asociaciones.repository;

import com.luis.springdata.asociaciones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository2 extends JpaRepository<Producto, Long> {
}
