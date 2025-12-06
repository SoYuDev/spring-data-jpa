package com.luis.apprest.repos;


import com.luis.apprest.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
            select t from Task t
                left join fetch t.items
                left join fetch t.tags
            """)
    Page<Task> findAllWithItemsAndTags(Pageable pageable);

    @Query("""
            select t from Task t
                left join fetch t.items
                left join fetch t.tags
            where t.id = :id
            """)
    Optional<Task> findByIdWithItemsAndTags(Long id);


}
