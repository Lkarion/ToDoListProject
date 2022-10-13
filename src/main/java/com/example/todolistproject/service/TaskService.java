package com.example.todolistproject.service;

import com.example.todolistproject.dto.SessionDTO;
import com.example.todolistproject.dto.TaskRequestDTO;
import com.example.todolistproject.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    void createTask(TaskRequestDTO request, SessionDTO sessionDTO);
    List<TaskResponseDTO> getAllTasks(SessionDTO sessionDTO);
    TaskResponseDTO getTaskById(Long taskId, SessionDTO sessionDTO);
    void completeTask(Long taskId, SessionDTO sessionDTO);
}
