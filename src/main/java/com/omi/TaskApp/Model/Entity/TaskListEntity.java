package com.omi.TaskApp.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tasklists")
public class TaskListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id" , nullable = false , updatable = false)
    UUID id;

    @Column(name = "title" , nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.REMOVE , CascadeType.PERSIST})
    List<TaskEntity> tasks;

    @Column(name = "created" , nullable = false)
    LocalDateTime created;

    @Column(name = "updated" , nullable = false)
    LocalDateTime updated;
}

