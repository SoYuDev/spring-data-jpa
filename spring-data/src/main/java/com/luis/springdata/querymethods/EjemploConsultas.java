package com.luis.springdata.querymethods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EjemploConsultas {

    private final ProductoRepository5 productoRepository5;

    //@PostConstruct
    public void run() {

        System.out.println("=== 10 productos más caros ===");
        productoRepository5.findTop10ByOrderByPrecioVentaDesc()
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));


        System.out.println("=== Cantidad de productos de Electrónica: %d ===".formatted(
                productoRepository5.countByCategoriaNombre("Electrónica")
        ));

        System.out.println("=== Producto más barato de la categoría Papelería y Oficina ===");
        productoRepository5.findFirstByCategoriaNombreOrderByPrecioVentaAsc("Papelería y Oficina")
                .ifPresentOrElse(
                        p -> {
                            System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta()));
                        },
                        () -> {
                            System.out.println("No hay productos de la categoría Papelería y Oficina");
                        });

        System.out.println("=== Productos que contienen Inalámbrico === ");
        productoRepository5.findByNombreProductoContainsIgnoreCase("Inalámbrico")
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        System.out.println("=== Productos con precio venta [20,50]€ ===");
        productoRepository5.findByPrecioVentaBetween(20.0, 50.0)
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

        System.out.println("=== Productos que contienen Inalámbrico con precio venta [20,50]€ ===");
        productoRepository5.findByNombreProductoContainsIgnoreCaseAndPrecioVentaBetween("Inalámbrico", 20.0, 50.0)
                .forEach(p -> System.out.println("%s (%.2f€)".formatted(p.getNombreProducto(), p.getPrecioVenta())));

    }
}
