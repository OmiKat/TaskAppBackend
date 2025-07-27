package com.omi.TaskApp.Mappers;

import com.omi.TaskApp.Model.Dto.TaskDto;
import com.omi.TaskApp.Model.Entity.TaskEntity;

public interface TaskMapper {

    TaskDto toDto(TaskEntity taskEntity);

    TaskEntity fromDto(TaskDto taskDto);
}
