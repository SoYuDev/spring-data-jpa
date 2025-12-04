package com.luis.springdata.asociaciones;

import com.luis.springdata.asociaciones.model.LineaPedido;
import com.luis.springdata.asociaciones.model.Pedido;
import com.luis.springdata.asociaciones.model.Producto;
import com.luis.springdata.asociaciones.repository.PedidoRepository;
import com.luis.springdata.asociaciones.repository.ProductoRepository2;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PedidoExample {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository2 productoRepository2;

    @PostConstruct
    public void run() {

        // Seleccionamos varios productos al azar para incluirlos en el pedido.
        List<Producto> todos = productoRepository2.findAll();
        Collections.shuffle(todos);
        Random random = new Random();
        int cantidad = random.nextInt(1, 5);
        List<Producto> aleatorios = todos.stream().limit(cantidad).toList();

        // Creamos el pedido y añadimos Líneas de Pedido
        Pedido pedido = Pedido.builder()
                .cliente("Luisi")
                .build();


        aleatorios.forEach(producto -> {
            pedido.addLineaPedido(
                    LineaPedido.builder()
                            .cantidad(random.nextInt(1, 5))
                            .producto(producto)
                            .precioVenta(producto.getPrecioVenta())
                            .build()
            );
        });

        System.out.println("=== Almacenando el pedido ===");
        pedidoRepository.save(pedido);

        System.out.println("=== Eliminando el pedido ===");
        pedidoRepository.delete(pedido);


    }

}