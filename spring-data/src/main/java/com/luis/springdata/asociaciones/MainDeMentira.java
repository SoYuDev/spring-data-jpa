package com.luis.springdata.asociaciones;

import com.luis.springdata.asociaciones.model.*;
import com.luis.springdata.asociaciones.repository.CategoriaRepository;
import com.luis.springdata.asociaciones.repository.ProductoDescripcionRepository;
import com.luis.springdata.asociaciones.repository.ProductoRepository2;
import com.luis.springdata.asociaciones.repository.TagRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainDeMentira {

    private final ProductoRepository2 productoRepository2;
    private final CategoriaRepository categoriaRepository;
    private final ProductoDescripcionRepository productoDescripcionRepository;
    private final TagRepository tagRepository;
    private final CategoriaService categoriaService;

    public MainDeMentira(ProductoRepository2 productoRepository2,
                         CategoriaRepository categoriaRepository,
                         ProductoDescripcionRepository productoDescripcionRepository,
                         TagRepository tagRepository,
                         CategoriaService categoriaService
    ) {
        this.productoRepository2 = productoRepository2;
        this.categoriaRepository = categoriaRepository;
        this.productoDescripcionRepository = productoDescripcionRepository;
        this.tagRepository = tagRepository;
        this.categoriaService = categoriaService;
    }

    // Se ejecuta cuando se instancia el Bean
    //@PostConstruct
    public void run() {

        Producto p = Producto.builder()
                .nombreProducto("Un producto")
                //.descripcion("Se trata de un producto de nuestro catálogo")
                .precioVenta(123.45)
                .build();

        productoRepository2.save(p);


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

        productoRepository2.save(p);


        /*productoRepository.findById(1L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No existe un producto con ID 1")
        );*/

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

        productoRepository2.save(coche);


        System.out.println("N+1 Consultas");
        System.out.println("==============");
        //categoriaRepository.findById(1L).ifPresentOrElse(
        categoriaService.cargarCategoria(1L).ifPresentOrElse(
                categoria -> {
                    System.out.println("ID:%d - %s: %s".formatted(
                            categoria.getId(),
                            categoria.getNombre(),
                            categoria.getProductos().stream().map(Producto::getNombreProducto).collect(Collectors.joining(", "))
                    ));

                },
                () -> System.out.println("No existe una categoria con ID 1")
        );

        System.out.println("JOIN FETCH");
        categoriaRepository.findCategoriaByIdWithProductos(1L).ifPresentOrElse(
                categoria -> {
                    System.out.println("ID:%d - %s: %s".formatted(
                            categoria.getId(),
                            categoria.getNombre(),
                            categoria.getProductos().stream().map(Producto::getNombreProducto).collect(Collectors.joining(", "))
                    ));

                },
                () -> System.out.println("No existe una categoria con ID 1")
        );

        /*productoRepository.findAll()
                .forEach(System.out::println);
        */

    }
}
