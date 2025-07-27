package com.omi.TaskApp.Mappers.Impl;

import com.omi.TaskApp.Enums.TaskStatus;
import com.omi.TaskApp.Mappers.TaskListMapper;
import com.omi.TaskApp.Model.Dto.TaskListDto;
import com.omi.TaskApp.Model.Entity.TaskEntity;
import com.omi.TaskApp.Model.Entity.TaskListEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapperImpl taskMapper;

    public TaskListMapperImpl(TaskMapperImpl taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskListEntity fromDto(TaskListDto taskListDto) {
        return new TaskListEntity(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto).toList())
                        .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskListEntity taskListEntity) {
        return new TaskListDto(
                taskListEntity.getId(),
                taskListEntity.getTitle(),
                taskListEntity.getDescription(),
                Optional.ofNullable(taskListEntity.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null),
                Optional.ofNullable(taskListEntity.getTasks()).map(List::size).orElse(0),
                calculateTaskListProgress(taskListEntity.getTasks())
        );
    }

    private Double calculateTaskListProgress(List<TaskEntity> tasks){
        if(null ==tasks){
            return null;
        }
        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();

        return (double) closedTaskCount / tasks.size();
    }
}
