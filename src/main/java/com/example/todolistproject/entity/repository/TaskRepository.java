package com.example.todolistproject.entity.repository;

import com.example.todolistproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
