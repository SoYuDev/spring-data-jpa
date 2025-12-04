package com.luis.springdata.asociaciones.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "tag_generator")
    @SequenceGenerator(name="tag_generator",
            sequenceName = "tag_seq", allocationSize = 1)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    @ToString.Exclude
    private Set<Producto> productos = new HashSet<>();

}
