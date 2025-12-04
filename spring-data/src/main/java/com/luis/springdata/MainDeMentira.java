package com.luis.springdata;

import com.luis.springdata.model.Categoria;
import com.luis.springdata.model.Producto;
import com.luis.springdata.model.ProductoDescripcion;
import com.luis.springdata.model.Tag;
import com.luis.springdata.repository.CategoriaRepository;
import com.luis.springdata.repository.ProductoDescripcionRepository;
import com.luis.springdata.repository.ProductoRepository;
import com.luis.springdata.repository.TagRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainDeMentira {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoDescripcionRepository productoDescripcionRepository;
    private final TagRepository tagRepository;

    public MainDeMentira(ProductoRepository productoRepository,
                         CategoriaRepository categoriaRepository,
                         ProductoDescripcionRepository productoDescripcionRepository,
                         TagRepository tagRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoDescripcionRepository = productoDescripcionRepository;
        this.tagRepository = tagRepository;
    }

    // Se ejecuta cuando se instancia el Bean
    @PostConstruct
    public void run() {

        Producto p = Producto.builder()
                .nombreProducto("Un producto")
                //.descripcion("Se trata de un producto de nuestro catálogo")
                .precioVenta(123.45)
                .build();

        productoRepository.save(p);


        ProductoDescripcion descripcion = ProductoDescripcion
                .builder()
                .descripcion("Se trata de un producto de nuestro catálogo")
                .marca("marca")
                .modelo("modelo")
                .url("http://")
                .build();

        p.setProductoDescripcion(descripcion);

        productoDescripcionRepository.save(descripcion);

        Tag tag1 = Tag.builder().nombre("Tag 1").build();
        Tag tag2 = Tag.builder().nombre("Tag 2").build();

        tagRepository.saveAll(List.of(tag1, tag2));

        p.addTag(tag1);
        p.addTag(tag2);

        productoRepository.save(p);


        productoRepository.findById(1L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No existe un producto con ID 1")
        );

        Categoria c = Categoria.builder()
                .nombre("Coches")
                .build();

        categoriaRepository
                .save(c);

        Producto coche = Producto.builder()
                .nombreProducto("Audi RS6")
                //.descripcion("Un coche de más de 500 CV")
                .precioVenta(200000)
                .build();

        c.addProducto(coche);

        productoRepository.save(coche);

        categoriaRepository.findById(1L).ifPresentOrElse(
                categoria -> {
                    System.out.println("ID:%d - %s: %s".formatted(
                            categoria.getId(),
                            categoria.getNombre(),
                            categoria.getProductos().stream().map(Producto::getNombreProducto).collect(Collectors.joining(", "))
                    ));

                },
                () -> System.out.println("No existe una categoria con ID 1")
        );

        productoRepository.findAll()
                .forEach(System.out::println);


    }
}
