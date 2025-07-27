package com.omi.TaskApp.service;


import com.omi.TaskApp.Model.Entity.TaskEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

     List<TaskEntity> getAllTasks(UUID uuid);

    TaskEntity createTask(UUID tasKListId, TaskEntity taskEntity);

    boolean exists(UUID id);

    Optional<TaskEntity> getTaskById(UUID taskListId, UUID taskId);


    TaskEntity updateTask(UUID taskListId, UUID taskId, TaskEntity taskEntity);

    void deleteTaskFromTaskList(UUID taskListId, UUID taskId);
}
