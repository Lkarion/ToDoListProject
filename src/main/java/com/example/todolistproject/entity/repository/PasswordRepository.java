package com.example.todolistproject.entity.repository;

import com.example.todolistproject.entity.Creator;
import com.example.todolistproject.entity.CreatorPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<CreatorPassword, Long> {

    CreatorPassword findByCreator(Creator creator);
}
