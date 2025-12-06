package com.luis.apprest.repos;


import com.luis.apprest.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("""
            select case when count(t) > 0 then true else false end
            from Task t
            where t.id = :id and type(t) = :type
            """)
    boolean existsByIdAndTaskType(@Param("type") Class taskType, Long id);

    @Modifying
    @Transactional
    @Query(value = """
            update check_list_item
            set checked = not checked
            where task_id = :id and id = :itemId
            """, nativeQuery = true)
    int toggleCheckListItem(Long id, Long itemId);


}
