package com.omi.TaskApp.service.Impl;

import com.omi.TaskApp.Model.Entity.TaskListEntity;
import com.omi.TaskApp.Repo.TaskListRepo;
import com.omi.TaskApp.service.TaskListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo repo;

    public TaskListServiceImpl(TaskListRepo repo) {
        this.repo = repo;
    }


    @Override
    public List<TaskListEntity> getAllTaskList() {
        return repo.findAll();
    }

    @Override
    public TaskListEntity createTaskList(TaskListEntity taskListEntity) {
//        if (null != taskListEntity.getId()){
//            throw new IllegalArgumentException("ID is already present");
//        }
        if((taskListEntity.getTitle() == null) || taskListEntity.getTitle().isBlank()){
            throw new IllegalArgumentException("No title in taskList ");
        }

        LocalDateTime now = LocalDateTime.now();
        taskListEntity.setCreated(now);
        taskListEntity.setUpdated(now);
        return repo.save(taskListEntity);
//        return repo.save(new TaskListEntity(
//                null,
//                taskListEntity.getTitle(),
//                taskListEntity.getDescription(),
//                null,
//                now,
//                now
//        ));
    }

    @Override
    public Optional<TaskListEntity> getTaskList(UUID uuid) {
        return repo.findById(uuid);
    }

    @Override
    public TaskListEntity updateTaskList(UUID id, TaskListEntity taskListEntity) {
        TaskListEntity existingTaskList = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task List not Found "));

        existingTaskList.setTitle(taskListEntity.getTitle());
        existingTaskList.setUpdated(LocalDateTime.now());
        existingTaskList.setDescription(taskListEntity.getDescription());

        return repo.save(existingTaskList);

    }

    @Override
    public void deleteTaskListById(UUID id) {
        if(!repo.existsById(id)){
            throw new EntityNotFoundException("The TaskList " + id + " Not found");
        }
        repo.deleteById(id);
    }

    @Override
    public boolean exists(UUID id) {
        return repo.existsById(id);
    }
}
