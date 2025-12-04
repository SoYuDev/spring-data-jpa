package com.luis.springdata.pagination;

import com.luis.springdata.asociaciones.model.Producto;
import com.luis.springdata.jpqa.GetProductoDto;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consultas con @Query

    @Query("""
            select p from Producto p
            """)
    List<Producto> obtenerTodos();


    @Query("""
            select max(p.precioVenta) 
            from Producto p
            """)
    Double precioMasCaro();

    @Query("""
            select max(p.precioVenta) 
            from Producto p join p.categoria c
            where c.nombre = :nombre
            """)
    Double precioMasCaroDeCategoria(String nombre);

    @Query("""
            select new com.example.springdata.consultas.GetProductoDto(
               p.id, p.nombreProducto, p.precioVenta
            ) from Producto p          
            """)
    List<GetProductoDto> informacionBasica();


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

    // Consultas derivadas

    List<Producto> findTop10ByOrderByPrecioVentaDesc();

    long countByCategoriaNombre(String nombre);

    Optional<Producto> findFirstByCategoriaNombreOrderByPrecioVentaAsc(String nombre);

    List<Producto> findByNombreProductoContainsIgnoreCase(String nombre);

    List<Producto> findByPrecioVentaBetween(double startPrecioVenta, double endPrecioVenta);

    List<Producto> findByNombreProductoContainsIgnoreCaseAndPrecioVentaBetween(
            String nombre, double startPrecioVenta, double endPrecioVenta
    );

    // Paginación

    Page<Producto> findByNombreProductoContainsIgnoreCase(String nombre, Pageable pageable);

    @Query("""
            select p
            from Producto p left join fetch p.tags t
            where t.nombre IN :tags
            """)
    Page<Producto> productosConTags(@Param("tags") List<String> tagsName, Pageable pageable);

    // Limit
    List<Producto> findByNombreProductoContainsIgnoreCase(String nombre, Limit limit);

    // Sort

    @Query("""
            select p
            from Producto p left join fetch p.categoria
            """)
    List<Producto> productosConCategoriaSiTienen(Sort sort, Limit limit);


    @Query("""
            select p
            from Producto p left join fetch p.categoria
            """)
    Page<Producto> productosConCategoriaSiTienen(Pageable pageable);


}
