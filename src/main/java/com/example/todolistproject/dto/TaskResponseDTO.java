package com.example.todolistproject.dto;

import com.example.todolistproject.entity.Creator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private Boolean isDone;
    private String creatorName;
}
