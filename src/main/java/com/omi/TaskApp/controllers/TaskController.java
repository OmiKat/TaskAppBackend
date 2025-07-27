package com.omi.TaskApp.controllers;

import com.omi.TaskApp.Mappers.Impl.TaskMapperImpl;
import com.omi.TaskApp.Mappers.TaskListMapper;
import com.omi.TaskApp.Mappers.TaskMapper;
import com.omi.TaskApp.Model.Dto.TaskDto;
import com.omi.TaskApp.Model.Entity.TaskEntity;
import com.omi.TaskApp.service.Impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/task-lists/{task-list-id}/tasks")
public class TaskController {

    private final TaskMapper taskMapper;
    private final TaskServiceImpl service;

    public TaskController(TaskServiceImpl service, TaskMapperImpl taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTask(@PathVariable("task-list-id")UUID taskListId){
        List<TaskEntity> allTasksEntity = service.getAllTasks(taskListId);
        List<TaskDto> allTaskDto = allTasksEntity.stream().map(taskMapper::toDto).toList();
        return new ResponseEntity<>(allTaskDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createATask(@PathVariable("task-list-id") UUID tasKListId , @RequestBody TaskDto taskDto){
        TaskEntity taskEntity = taskMapper.fromDto(taskDto);
        TaskEntity task = service.createTask(tasKListId , taskEntity);
        return new ResponseEntity<>(taskMapper.toDto(task),HttpStatus.CREATED);
    }

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> GetOneTaskFromTaskList(@PathVariable("task-list-id") UUID taskListId , @PathVariable("task-id") UUID taskId){
        Optional<TaskEntity> taskEntity = service.getTaskById(taskListId,taskId);
        return taskEntity.map(taskEntity1 -> {
            TaskDto dto = taskMapper.toDto(taskEntity1);
            return new ResponseEntity<>(dto , HttpStatus.OK);
        })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<TaskDto> updateATask(@PathVariable("task-list-id") UUID taskListId , @PathVariable("task-id") UUID taskId , @RequestBody TaskDto taskDto){
        TaskEntity taskEntity = taskMapper.fromDto(taskDto);
        TaskEntity updatedEntity = service.updateTask(taskListId , taskId , taskEntity);
        return new ResponseEntity<>(taskMapper.toDto(updatedEntity),HttpStatus.OK);
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("task-list-id") UUID taskListId , @PathVariable("task-id") UUID taskId){
        service.deleteTaskFromTaskList(taskListId,taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
