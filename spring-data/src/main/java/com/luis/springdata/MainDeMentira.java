package com.luis.springdata;

import com.luis.springdata.model.Producto;
import com.luis.springdata.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MainDeMentira {

    private final ProductoRepository productoRepository;

    public MainDeMentira(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Se ejecuta cuando se instancia el Bean
    @PostConstruct
    public void run() {
        System.out.println("Iniciando el proceso");
        Producto p = Producto.builder()
                .nombreProducto("Ratoncito")
                .descripcion("Un ratoncito muy pequeño...")
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
    }
}
