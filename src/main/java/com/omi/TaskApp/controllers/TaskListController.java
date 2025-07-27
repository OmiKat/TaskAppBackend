package com.omi.TaskApp.controllers;

import com.omi.TaskApp.Mappers.TaskListMapper;
import com.omi.TaskApp.Model.Dto.TaskListDto;
import com.omi.TaskApp.Model.Entity.TaskListEntity;
import com.omi.TaskApp.service.Impl.TaskListServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/task-lists")
public class TaskListController {


    private final TaskListServiceImpl service;
    private final TaskListMapper taskListMapper;


    public TaskListController(TaskListServiceImpl service, TaskListMapper taskListMapper) {
        this.service = service;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public ResponseEntity<List<TaskListDto>> getAllTaskLists(){
        List<TaskListEntity> allTaskList = service.getAllTaskList();
        List<TaskListDto> taskListDtos = allTaskList
                .stream()
                .map(taskListMapper::toDto)
                .toList();
        return new ResponseEntity<>(taskListDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTasKList(@RequestBody TaskListDto taskListDto){
        TaskListEntity taskListEntity = taskListMapper.fromDto(taskListDto);
        TaskListEntity createdTaskListEntity = service.createTaskList(taskListEntity);
        TaskListDto createdTaskListDto = taskListMapper.toDto(createdTaskListEntity);
        return new ResponseEntity<>(createdTaskListDto,HttpStatus.CREATED);
    }

    @GetMapping("/{task-list-id}")
    public ResponseEntity<TaskListDto> getATaskList(@PathVariable("task-list-id")UUID uuid){
        Optional<TaskListEntity> taskListEntity = service.getTaskList(uuid);
        return taskListEntity.map(taskListEntity1 -> {
                    TaskListDto tasklistDto = taskListMapper.toDto(taskListEntity1);
                    return new ResponseEntity<>(tasklistDto , HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{task-list-id}")
    public ResponseEntity<TaskListDto> updateTaskList(@PathVariable("task-list-id") UUID id , @RequestBody TaskListDto taskListDto){
        TaskListEntity taskListEntity = taskListMapper.fromDto(taskListDto);
        if(service.getTaskList(id).isPresent()){
            TaskListEntity updatedTaskListEntity = service.updateTaskList(id , taskListEntity);
            TaskListDto taskListDto1 = taskListMapper.toDto(updatedTaskListEntity);
            return new ResponseEntity<>(taskListDto1,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{task-list-id}")
    public ResponseEntity<String> deleteTaskList(@PathVariable("task-list-id") UUID id){
        service.deleteTaskListById(id);

        return new ResponseEntity<>("TaskList Entity has been deleted" , HttpStatus.NO_CONTENT);
    }

}
