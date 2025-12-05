package com.luis.apprest.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_entity") // Importante que la palabra user está reservada en postgresql
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private String fullname;

    // Usuario puede tener muchas tareas
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @Setter(value = AccessLevel.NONE)
    private List<Task> tasks = new ArrayList<>();

    /*    -- Tabla user_entity
    CREATE TABLE user_entity (
        id BIGINT PRIMARY KEY,
        username VARCHAR(255),
        password VARCHAR(255),
        email VARCHAR(255),
        fullname VARCHAR(255)
    );

    -- Tabla task
    CREATE TABLE task (
        id BIGINT PRIMARY KEY,
        created_at TIMESTAMP,
        title VARCHAR(255),
        owner_id BIGINT  -- ← FK que apunta a user_entity.id
    );*/

    // Helpers

    public void addTask(Task task) {
        tasks.add(task);
        task.setOwner(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setOwner(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
