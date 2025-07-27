package com.omi.TaskApp.Mappers.Impl;

import com.omi.TaskApp.Mappers.TaskMapper;
import com.omi.TaskApp.Model.Dto.TaskDto;
import com.omi.TaskApp.Model.Entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskEntity fromDto(TaskDto taskDto) {
        return new TaskEntity(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(TaskEntity taskEntity) {
        return new TaskDto(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDueDate(),
                taskEntity.getStatus(),
                taskEntity.getPriority()
        );
    }
}
