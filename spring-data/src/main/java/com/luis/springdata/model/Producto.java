package com.luis.springdata.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "productos") Podemos modificar el nombre de la tabla, hay algunas palabras reservadas como
// "Users" en PostgresSQL con las que si nuestra clase Java se llama igual, debemos de cambiar el nombre.
public class Producto {
    // La estrategia AUTO elige la estrategia en función del gestor de DB y el tipo de dato que sea la PK.
    @Id
    // También podemos establecer nosotros mismos como va a ir incrementando la secuencia de la PK
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "producto_seq", allocationSize = 1)
    private Long id;

    // Por default el varchar es 255, podemos cambiarlo
    @Column(length = 512)
    private String nombreProducto;

    // El tipo TEXT no tienen límite de caracteres
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio")
    private double precioVenta;

    // El producto va a tener una categoría y categoría va a tener varios productos
    @ManyToOne()
    // Usamos JoinColumn para establecer el nombre que deseamos a la FK que se genera.
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Categoria categoria;

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
