package com.luis.springdata.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "category_generator")
    @SequenceGenerator(name = "category_generator",
            sequenceName = "categoria_seq", allocationSize = 1)
    private Long id;

    private String nombre;

    // La buena práctica es tener en ambas clases que establecen la relación sus anotaciones correspondientes
    // @ManyToOne en Producto y @OneToMany en Categoria
    // El valor que debe de tener mappedBy es el nombre del atributo que tenemos en Producto para la asociación
    //EAGER carga todas las entidades relacionadas, cargará los productos de la categoria seleccionada
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @Builder.Default // Como tenemos @Builder en la Clase, Builder.Default hace que si instanciamos
    // Un objeto con el metodo Builder sin especificar .productos se genere por default.
    @ToString.Exclude // Excluye este atributo del metodo toString() MUY IMPORTANTE PARA EVITAR RECURSIÓN INFINITA
    private List<Producto> productos = new ArrayList<>();

    // Métodos helper -- Recomendados para buenas prácticas de JPA

    public Categoria addProducto(Producto p) {
        productos.add(p);
        p.setCategoria(this);
        return this;
    }

    public Categoria removeProducto(Producto p) {
        productos.remove(p);
        p.setCategoria(null);
        return this;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Categoria categoria = (Categoria) o;
        return getId() != null && Objects.equals(getId(), categoria.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
