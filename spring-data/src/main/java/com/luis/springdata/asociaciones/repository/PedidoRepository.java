package com.luis.springdata.asociaciones.repository;

import com.luis.springdata.asociaciones.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}
