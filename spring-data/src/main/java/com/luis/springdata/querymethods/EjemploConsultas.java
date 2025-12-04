package com.luis.springdata.querymethods;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EjemploConsultas {

    private final ProductoRepository productoRepository;

    @PostConstruct
    public void run() {

        System.out.println("=== 10 productos más caros ===");
        productoRepository.findTop10ByOrderByPrecioVentaDesc()
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));


        System.out.println("=== Cantidad de productos de Electrónica: %d ===".formatted(
                productoRepository.countByCategoriaNombre("Electrónica")
        ));

        System.out.println("=== Producto más barato de la categoría Papelería y Oficina ===");
        productoRepository.findFirstByCategoriaNombreOrderByPrecioVentaAsc("Papelería y Oficina")
                .ifPresentOrElse(
                        p -> {
                            System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta()));
                        },
                        () -> {
                            System.out.println("No hay productos de la categoría Papelería y Oficina");
                        });

        System.out.println("=== Productos que contienen Inalámbrico === ");
        productoRepository.findByNombreProductoContainsIgnoreCase("Inalámbrico")
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        System.out.println("=== Productos con precio venta [20,50]€ ===");
        productoRepository.findByPrecioVentaBetween(20.0, 50.0)
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        System.out.println("=== Productos que contienen Inalámbrico con precio venta [20,50]€ ===");
        productoRepository.findByNombreProductoContainsIgnoreCaseAndPrecioVentaBetween("Inalámbrico", 20.0, 50.0)
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

    }
}
