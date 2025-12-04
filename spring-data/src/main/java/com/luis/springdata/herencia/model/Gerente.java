package com.luis.springdata.herencia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Gerente extends Empleado {

  private boolean tieneMBA;

  @OneToMany(mappedBy = "jefe", fetch = FetchType.EAGER)
  @Builder.Default
  @ToString.Exclude
  private List<Empleado> subordinados = new ArrayList<>();

  public void addSubordinado(Empleado e) {
    subordinados.add(e);
    e.setJefe(this);
  }

  public void removeSubordinado(Empleado e) {
    subordinados.remove(e);
    e.setJefe(null);
  }


  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Gerente gerente = (Gerente) o;
    return getId() != null && Objects.equals(getId(), gerente.getId());
  }


}
