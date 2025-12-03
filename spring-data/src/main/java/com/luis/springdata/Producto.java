package com.luis.springdata;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Genera getters & setters toString, EqualsAndHashCode y RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "productos") Podemos modificar el nombre de la tabla, hay algunas palabras reservadas como
// "Users" en PostgresSQL con las que si nuestra clase Java se llama igual, debemos de cambiar el nombre.
public class Producto {
    // La estrategia AUTO elige la estrategia en función del gestor de DB y el tipo de dato que sea la PK.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private double precio;
}
