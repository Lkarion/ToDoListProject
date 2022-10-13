package com.example.todolistproject.entity.repository;

import com.example.todolistproject.entity.CreatorSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<CreatorSession, Long> {

    CreatorSession findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
