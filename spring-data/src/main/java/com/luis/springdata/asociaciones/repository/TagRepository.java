package com.luis.springdata.asociaciones.repository;

import com.luis.springdata.asociaciones.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
