package com.example.todolistproject.controller;

import com.example.todolistproject.dto.SessionDTO;
import com.example.todolistproject.dto.TaskRequestDTO;
import com.example.todolistproject.dto.TaskResponseDTO;
import com.example.todolistproject.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Authenticated users
     *      * /tasks POST (create a task)
     *      * /tasks GET (see all tasks)
     *      * /tasks/{id} GET (see task by ID)
     *      * /tasks/{id}/complete (set isDone to true)
     */

    @PostMapping("/tasks")
    public void createTask(@RequestBody TaskRequestDTO request,
                           @AuthenticationPrincipal SessionDTO sessionDTO) {
        taskService.createTask(request, sessionDTO);
    }
    @GetMapping("/tasks")
    public List<TaskResponseDTO> getAllTasks(@AuthenticationPrincipal SessionDTO sessionDTO) {
        return taskService.getAllTasks(sessionDTO);
    }
    @GetMapping("/tasks/{id}")
    public TaskResponseDTO getTaskById(@PathVariable(name = "id") Long taskId,
                                       @AuthenticationPrincipal SessionDTO sessionDTO) {
        return taskService.getTaskById(taskId, sessionDTO);
    }
    @PutMapping("/tasks/{id}/complete")
    public void completeTask(@PathVariable(name = "id") Long taskId,
                             @AuthenticationPrincipal SessionDTO sessionDTO) {
        taskService.completeTask(taskId, sessionDTO);
    }
}
