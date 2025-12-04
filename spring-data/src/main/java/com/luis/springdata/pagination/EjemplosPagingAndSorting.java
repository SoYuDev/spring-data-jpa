package com.luis.springdata.pagination;

import com.luis.springdata.asociaciones.model.Producto;
import com.luis.springdata.asociaciones.model.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EjemplosPagingAndSorting {

    private final ProductoRepository productoRepository;


    @PostConstruct
    public void run() {

        // 1ª Página de 5 productos
        Pageable firstReq = PageRequest.of(0, 5);
        Page<Producto> primera = productoRepository.findAll(firstReq);
        System.out.println("=== Primera página de resultados (Tam. pág. 5 elementos) ===");
        primera.getContent()
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        // 2ª Página (siguiente) de 5 productos
        System.out.println("=== Siguiente página de resultados (Tam. pág. 5 elementos) ===");
        productoRepository.findAll(firstReq.next())
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));


        // Consulta derivada con paginación
        Page<Producto> porNombre = productoRepository.findByNombreProductoContainsIgnoreCase(
                "n",
                PageRequest.of(0, 3)
        );

        System.out.println("=== Primera página de productos que contienen n ===");
        System.out.println("= Nº página: %d, Tamaño: %d, Nº total elementos: %d, Nº páginas: %d =".formatted(
                porNombre.getNumber(),
                porNombre.getSize(),
                porNombre.getTotalElements(),
                porNombre.getTotalPages()
        ));
        porNombre.getContent()
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));


        // Consulta con @Query con paginación
        System.out.println("=== 3ª página de los productos que contienen el tag 'IA' ===");
        Pageable terceraIA = PageRequest.of(2, 4);
        productoRepository.productosConTags(List.of("IA"), terceraIA)
                .getContent()
                .forEach(p -> System.out.println("%s (%.2f€) (%s)".formatted(
                        p.getNombreProducto(),
                        p.getPrecioVenta(),
                        p.getTags().stream().map(Tag::getNombre).collect(Collectors.joining(","))
                )));

        // Consulta derivada con Limit
        System.out.println("=== 5 primeros resultados de productos que contienen n ===");
        productoRepository.findByNombreProductoContainsIgnoreCase("n", Limit.of(5))
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));


        // Sort
        System.out.println("=== Productos ordenados por nombre descendentemente ===");
        productoRepository.findAll(Sort.by("nombreProducto").descending())
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        // Sort y Limit
        System.out.println("=== 15 primeros productos ordenados por categoría ascendentemente y precio descendentemente ===");
        productoRepository.productosConCategoriaSiTienen(
                        Sort.by("categoria.nombre").ascending()
                                .and(Sort.by("precioVenta").descending()),
                        Limit.of(15))
                .forEach(p -> System.out.println("%s (%.2f€) (Categoría %s)"
                        .formatted(p.getNombreProducto(),
                                p.getPrecioVenta(),
                                p.getCategoria() != null ? p.getCategoria().getNombre() : "Sin categoría"
                        )));


        // Pageable y Sort
        // El objeto de tipo Sort se pasa como 3º argumento de PageRequest.of
        System.out.println("=== 1ª página de productos ordenados por categoría ascendentemente y precio descendentemente ===");
        productoRepository.productosConCategoriaSiTienen(
                        PageRequest.of(0, 15,
                                Sort.by("categoria.nombre").ascending()
                                        .and(Sort.by("precioVenta").descending())))
                .getContent()
                .forEach(p -> System.out.println("%s (%.2f€) (Categoría %s)"
                        .formatted(p.getNombreProducto(),
                                p.getPrecioVenta(),
                                p.getCategoria() != null ? p.getCategoria().getNombre() : "Sin categoría"
                        )));
    }

}
