package com.luis.springdata.asociaciones.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
//    @Column(columnDefinition = "TEXT")
//    private String descripcion;

    @OneToOne(mappedBy = "producto", fetch = FetchType.EAGER)
    private ProductoDescripcion descripcion;

    @Column(name = "precio")
    private double precioVenta;

    // El producto va a tener una categoría y categoría va a tener varios productos
    @ManyToOne()
    // Usamos JoinColumn para establecer el nombre que deseamos a la FK que se genera.
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Categoria categoria;

    @ManyToMany(fetch = FetchType.EAGER)
    // Generamos una tabla que conecta los productos con las etiquetas
    @JoinTable(
            name = "producto_tag",
            joinColumns = @JoinColumn(
                    name = "producto_id",
                    foreignKey = @ForeignKey(name = "fk_producto_tag_producto")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    foreignKey = @ForeignKey(name = "fk_producto_tag_tag")
            )
    )
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    // Helpers One to One
    public void setProductoDescripcion(ProductoDescripcion descripcion) {
        this.setDescripcion(descripcion);
        descripcion.setProducto(this);
    }

    public void removeProductoDescripcion(ProductoDescripcion descripcion) {
        this.setDescripcion(null);
        descripcion.setProducto(null);
    }

    // Helpers Tag
    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getProductos().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getProductos().remove(this);
    }


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
