package com.luis.springdata.herencia.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@SuperBuilder // Nos permite tener un builder consistente en una jerarquía de herencia.
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass // Indica que la clase va a ser la base de una herencia donde la hija sí que podrá ser una Entity
public class EntidadBase {
    @Id
    @GeneratedValue
    private Long id;

    // equals() debe de estar en la Entidad hija para que funcione, hashcode podemos dejarlo aquí aunque también podemos
    // implementar un hashCode en cada clase hija y eliminarlo de la clase padre.

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
