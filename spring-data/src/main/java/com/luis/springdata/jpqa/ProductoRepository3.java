package com.luis.springdata.jpqa;

import com.luis.springdata.asociaciones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // El fetch es importante ponerlo si en las entidades tenemos .LAZY que es lo recomendable por los expertos
    @Query("""
            select p 
            from Producto p join fetch p.categoria c
            where c.id = 1           
            """)
    List<Producto> productosDeCategoria1();

    @Query("""
            select p
            from Producto p left join fetch p.categoria
            """)
    List<Producto> productosConCategoriaSiTienen();

    @Query("""
            select max(p.precioVenta) 
            from Producto p join p.categoria c
            where c.nombre = :nombre
            """)
    Double precioMasCaroDeCategoria(String nombre);

    @Query("""
            select p
            from Producto p left join fetch p.tags t
            where t.nombre IN :tags
            """)
    List<Producto> productosConTags(@Param("tags") List<String> tagsName);


    @Query("""
            select p
            from Producto p left join fetch p.categoria c
            where lower(p.nombreProducto) like lower(concat('%', ?1,'%'))
                  and p.precioVenta between ?2 and ?3
            order by p.precioVenta
            """)
    List<Producto> productosPorNombreYRangoPrecio(String nombre, double precioMenor, double precioMayor);
}
