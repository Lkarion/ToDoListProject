package com.example.todolistproject.dto;

import com.example.todolistproject.entity.Creator;
import com.example.todolistproject.entity.Task;

public class ConverterDTO {

    public static Task convertDTOToTask(TaskRequestDTO request, Creator creator) {
        return Task.builder()
                .title(request.getTitle())
                .isDone(false)
                .creator(creator)
                .build();
    }
    public static TaskResponseDTO convertTaskToDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .isDone(task.getIsDone())
                .creatorName(task.getCreator().getName())
                .build();
    }
    public static Creator convertDTOToCreator(CreatorRequestDTO request) {
        return Creator.builder()
                .name(request.getName())
                .build();
    }
}
