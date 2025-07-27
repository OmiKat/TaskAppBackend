package com.omi.TaskApp.Model.Dto;

import com.omi.TaskApp.Enums.TaskPriority;
import com.omi.TaskApp.Enums.TaskStatus;


import java.time.LocalDateTime;
import java.util.UUID;


public record TaskDto(

        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
){
}
