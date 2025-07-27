package com.omi.TaskApp.service;


import com.omi.TaskApp.Model.Entity.TaskListEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {

    List<TaskListEntity> getAllTaskList();

    TaskListEntity createTaskList(TaskListEntity taskListEntity);

    Optional<TaskListEntity> getTaskList(UUID uuid);

    TaskListEntity updateTaskList(UUID id, TaskListEntity taskListEntity);

    void deleteTaskListById(UUID id);

    boolean exists(UUID id);
}
