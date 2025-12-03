package com.luis.springdata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Genera getters & setters toString, EqualsAndHashCode y RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {
    
    @Id
    private Long id;

    private String nombre;

    private double precio;
}
