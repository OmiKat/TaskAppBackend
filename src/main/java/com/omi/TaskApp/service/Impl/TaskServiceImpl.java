package com.omi.TaskApp.service.Impl;

import com.omi.TaskApp.Enums.TaskPriority;
import com.omi.TaskApp.Enums.TaskStatus;
import com.omi.TaskApp.Model.Entity.TaskEntity;
import com.omi.TaskApp.Model.Entity.TaskListEntity;
import com.omi.TaskApp.Repo.TaskListRepo;
import com.omi.TaskApp.Repo.TaskRepo;
import com.omi.TaskApp.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo repo;
    private final TaskListRepo listRepo;

    public TaskServiceImpl(TaskRepo repo, TaskListRepo listRepo) {
        this.repo = repo;
        this.listRepo = listRepo;
    }

    @Override
    public List<TaskEntity> getAllTasks(UUID uuid) {
        return repo.findByTaskListId(uuid);
    }


    // this creates method is superior that the create method I used in tasklist service
    @Override
    public TaskEntity createTask(UUID tasKListId, TaskEntity taskEntity) {
        TaskPriority taskPriority = Optional.ofNullable(taskEntity.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskListEntity TaskList = listRepo.findById(tasKListId).orElseThrow( () -> new IllegalArgumentException("task list not found"));

        return repo.save(new TaskEntity(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDueDate(),
                taskStatus,
                taskPriority,
                LocalDateTime.now(),
                LocalDateTime.now(),
                TaskList
        ));
    }

    @Override
    public Optional<TaskEntity> getTaskById(UUID taskListId, UUID taskId) {
        return repo.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public boolean exists(UUID id) {
        return repo.existsById(id);
    }

    @Override
    public TaskEntity updateTask(UUID taskListId, UUID taskId , TaskEntity taskEntity) {
        TaskEntity existingTask = repo.findByTaskListIdAndId(taskListId,taskId).orElseThrow(() -> new IllegalArgumentException(" task not found"));

        existingTask.setTitle(taskEntity.getTitle());
        existingTask.setDescription(taskEntity.getDescription());
        existingTask.setUpdated(LocalDateTime.now());
        existingTask.setPriority(taskEntity.getPriority());
        existingTask.setDueDate(taskEntity.getDueDate());
        existingTask.setStatus(taskEntity.getStatus());

        return repo.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTaskFromTaskList(UUID taskListId, UUID taskId) {
        repo.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
