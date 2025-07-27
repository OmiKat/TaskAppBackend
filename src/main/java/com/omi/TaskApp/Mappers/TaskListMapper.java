package com.omi.TaskApp.Mappers;

import com.omi.TaskApp.Model.Dto.TaskListDto;
import com.omi.TaskApp.Model.Entity.TaskListEntity;

public interface TaskListMapper {

    TaskListDto toDto(TaskListEntity taskListEntity);

    TaskListEntity fromDto(TaskListDto taskListDto);
}
