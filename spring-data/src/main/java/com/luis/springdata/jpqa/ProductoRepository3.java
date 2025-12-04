package com.luis.springdata.jpqa;

import com.luis.springdata.asociaciones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository3 extends JpaRepository<Producto, Long> {

    @Query("""
            select p from Producto p
            """)
    List<Producto> obtenerTodos();

    @Query("""
            select max(p.precioVenta) from Producto p
            """)
    Double precioMasCaro();

    @Query("""
            select new com.luis.springdata.jpqa.GetProductoDto(
               p.id, p.nombreProducto, p.precioVenta
            ) from Producto p          
            """)
    List<GetProductoDto> informacionBasica();
}
