package com.luis.apprest.model;

// CLASE ABSTRACTA DE LA QUE HEREDARÁ BasicTask

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// Como va a ser una clase de la que heredamos,
// evita futuros errores a la hora de usar builder con BasicTask
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// Al usar este tipo de estrategia generamos tablas separadas:

/*-- Tabla padre
CREATE TABLE task (
    id BIGINT PRIMARY KEY,
    created_at TIMESTAMP,
    title VARCHAR(255)
);

-- Tabla hija
CREATE TABLE basic_task (
    id BIGINT PRIMARY KEY,  -- FK a task.id
    description TEXT,
    completed BOOLEAN
);*/
public abstract class Task {

    @Id
    @GeneratedValue
    private Long id;

    // Si creando un objeto con Builder no establecemos este atributo, por defecto escogerá este valor.
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private String title;

    @ManyToOne
    private User owner;

    // Genera la tabla intermedia que hay a continuación
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tasks_join_tags", // Nombre tabla intermedia
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<TaskTag> tags = new HashSet<>();

    /*CREATE TABLE tasks_join_tags (
        task_id BIGINT NOT NULL,
        tag_id BIGINT NOT NULL,
        PRIMARY KEY (task_id, tag_id),
        FOREIGN KEY (task_id) REFERENCES task(id),
        FOREIGN KEY (tag_id) REFERENCES task_tag(id)
    );*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
