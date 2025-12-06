package com.luis.apprest.repos;

import com.luis.apprest.model.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
    Optional<TaskTag> findByName(String name);
}
