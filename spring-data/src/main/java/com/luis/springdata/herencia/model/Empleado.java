package com.luis.springdata.herencia.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Empleado extends EntidadBase {

    private String nombre;
    private String email;
    private String telefono;
    private String departamento;
    @ManyToOne
    @JoinColumn(name = "jefe_id")
    private Gerente jefe;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Empleado empleado = (Empleado) o;
        return getId() != null && Objects.equals(getId(), empleado.getId());
    }


}
