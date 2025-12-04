package com.luis.springdata.asociaciones.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductoDescripcion {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String marca;

    private String modelo;

    private String url;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_producto_producto_descripcion"))
    private Producto producto;

}
