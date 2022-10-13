package com.example.todolistproject.service.impl;

import com.example.todolistproject.dto.ConverterDTO;
import com.example.todolistproject.dto.SessionDTO;
import com.example.todolistproject.dto.TaskRequestDTO;
import com.example.todolistproject.dto.TaskResponseDTO;
import com.example.todolistproject.entity.Creator;
import com.example.todolistproject.entity.CreatorSession;
import com.example.todolistproject.entity.Task;
import com.example.todolistproject.entity.repository.SessionRepository;
import com.example.todolistproject.entity.repository.TaskRepository;
import com.example.todolistproject.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final SessionRepository sessionRepository;

    public void createTask(TaskRequestDTO request, SessionDTO sessionDTO) {
        if (!sessionIsValid(sessionDTO)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        CreatorSession session = sessionRepository.findBySessionId(sessionDTO.getSessionId());

        Creator creator = session.getCreator();
        Task task = ConverterDTO.convertDTOToTask(request, creator);
        taskRepository.save(task);
    }
    public List<TaskResponseDTO> getAllTasks(SessionDTO sessionDTO) {
        if (!sessionIsValid(sessionDTO)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return taskRepository.findAll().stream()
                .map(ConverterDTO::convertTaskToDTO)
                .toList();
    }
    public TaskResponseDTO getTaskById(Long taskId, SessionDTO sessionDTO) {
        if (!sessionIsValid(sessionDTO)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ConverterDTO.convertTaskToDTO(task);
    }
    public void completeTask(Long taskId, SessionDTO sessionDTO) {
        if (!sessionIsValid(sessionDTO)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.setIsDone(true);
        taskRepository.save(task);
    }

    private boolean sessionIsValid(SessionDTO sessionDTO) {
        CreatorSession session = sessionRepository.findBySessionId(sessionDTO.getSessionId());
        if (session == null) {
            return false;
        }
        if (session.getExpirationTime().isBefore(LocalDateTime.now())) {
            sessionRepository.delete(session);
            return false;
        }
        return true;
    }
}
