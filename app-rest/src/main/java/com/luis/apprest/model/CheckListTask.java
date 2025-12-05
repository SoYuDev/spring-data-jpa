package com.luis.apprest.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
// Como va a ser una clase de la que heredamos,
// evita futuros errores a la hora de usar builder con BasicTask
@SuperBuilder
@Entity
public class CheckListTask extends Task {

    // OneToMany - Relación bidireccional mapeada por el campo task de CheckListItem
    // Esto implica que la FK está en la tabla check_list_item
    // mappedBy define que él no controla la DB, el otro lado si

    /*    -- Tabla task (padre)
    CREATE TABLE task (
        id BIGINT PRIMARY KEY,
        created_at TIMESTAMP,
        title VARCHAR(255)
    );

    -- Tabla checklist_task (hija de task)
    CREATE TABLE check_list_task (
        id BIGINT PRIMARY KEY  -- FK a task
    );

    -- Tabla check_list_item
    CREATE TABLE check_list_item (
        id BIGINT PRIMARY KEY,
        task_id BIGINT,  -- ← FK: LA RELACIÓN SE GUARDA AQUÍ
        text VARCHAR(255),
        checked BOOLEAN
    );*/

    @OneToMany(
            mappedBy = "task",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, // Las operaciones a CheckListTask de propagan a sus items
            // p.j: taskRepository.delete(task);  ✅ Borra el task Y todos sus items
            orphanRemoval = true
    )
    @Builder.Default
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private List<CheckListItem> items = new ArrayList<>();

    // Helpers Es una relación bidireccional. Tienes que mantener ambos lados sincronizados:
    //

    public void addItem(CheckListItem item) {
        items.add(item);
        item.setTask(this);
    }

    public void removeItem(CheckListItem item) {
        items.remove(item);
    }
}
