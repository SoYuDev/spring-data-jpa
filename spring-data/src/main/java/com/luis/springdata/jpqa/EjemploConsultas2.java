package com.luis.springdata.jpqa;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EjemploConsultas2 {

    private final ProductoRepository3 productoRepository3;

    @PostConstruct
    public void run() {

        System.out.println("=== Obtener todos los productos ===");
        productoRepository3.obtenerTodos()
                .forEach(p -> System.out.println("%s (%.2f€)"
                        .formatted(p.getNombreProducto(), p.getPrecioVenta())));

        System.out.println("=== Obtener el precio de venta más caro ===");
        System.out.println(productoRepository3.precioMasCaro());

        System.out.println("=== Obtener la información más básica de los productos ===");
        productoRepository3.informacionBasica()
                .forEach(dto -> System.out.println("%d: %s"
                        .formatted(dto.id(), dto.nombre())));


    }
}
