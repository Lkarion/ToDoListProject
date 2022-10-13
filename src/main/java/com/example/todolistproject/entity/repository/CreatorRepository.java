package com.example.todolistproject.entity.repository;

import com.example.todolistproject.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Boolean existsByName(String name);
    Creator findByName(String name);
}
