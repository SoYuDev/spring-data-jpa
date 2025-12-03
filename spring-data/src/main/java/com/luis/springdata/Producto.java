package com.luis.springdata;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    // Por default el varchar es 255, podemos cambiarlo
    @Column(length = 512)
    private String nombreProducto;

    // El tipo TEXT no tienen límite de caracteres
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio")
    private double precioVenta;

    // Cuando ejecutamos Hibernate se encarga de hacer la siguiente sentencia SQL que puede verse por consola:
    // Hibernate: create table producto (precio float(53),
    // id bigint not null, nombre_producto varchar(512), descripcion TEXT, primary key (id))

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Producto producto = (Producto) o;
        return getId() != null && Objects.equals(getId(), producto.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
