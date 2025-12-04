package com.luis.springdata;

import com.luis.springdata.model.Categoria;
import com.luis.springdata.model.Producto;
import com.luis.springdata.model.ProductoDescripcion;
import com.luis.springdata.repository.CategoriaRepository;
import com.luis.springdata.repository.ProductoDescripcionRepository;
import com.luis.springdata.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MainDeMentira {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoDescripcionRepository productoDescripcionRepository;

    public MainDeMentira(ProductoRepository productoRepository,
                         CategoriaRepository categoriaRepository,
                         ProductoDescripcionRepository productoDescripcionRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoDescripcionRepository = productoDescripcionRepository;
    }

    // Se ejecuta cuando se instancia el Bean
    @PostConstruct
    public void run() {
        System.out.println("Iniciando el proceso");
        Producto p = Producto.builder()
                .nombreProducto("Ratoncito")
                .precioVenta(741)
                .build();

        productoRepository.save(p);
        System.out.println("Product saved");

        // Devuelve un Optional<Producto> que nos permite tener programación funcional
        productoRepository.findById(2L)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("No existe producto con id 2")
                );

        // Añadir una categoria y un producto con lo métodos helper para buenas prácticas
        Categoria c = Categoria.builder()
                .nombre("Coches")
                .build();

        categoriaRepository.save(c);

        Producto coche = Producto.builder()
                .nombreProducto("Audi")
                .precioVenta(100000)
                .build();

        c.addProducto(coche);

        productoRepository.save(coche);

        // Obtenemos los productos que tiene cada ID
        categoriaRepository.findById(1L).ifPresentOrElse(
                categoria -> {
                    System.out.println("ID:%d - %s: %s".formatted(
                            categoria.getId(),
                            categoria.getNombre(),
                            categoria.getProductos()
                                    .stream()
                                    .map(producto -> producto.getNombreProducto())
                                    .collect(Collectors.joining(", "))
                    ));

                },
                () -> System.out.println("No existe una categoria con ID 1")
        );

        ProductoDescripcion descripcion = ProductoDescripcion
                .builder()
                .descripcion("Se trata de un producto de nuestro catálogo")
                .marca("marca")
                .modelo("modelo")
                .url("http://")
                .build();

        p.setProductoDescripcion(descripcion);

        productoDescripcionRepository.save(descripcion);
    }
}
