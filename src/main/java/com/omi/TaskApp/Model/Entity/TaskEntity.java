package com.omi.TaskApp.Model.Entity;

import com.omi.TaskApp.Enums.TaskPriority;
import com.omi.TaskApp.Enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false , nullable = false)
    UUID id;

    @Column(name = "title" , nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "due_date",nullable = false)
    LocalDateTime dueDate;

    @Column(name = "status",nullable = false)
    TaskStatus status;

    @Column(name = "priority",nullable = false)
    TaskPriority priority;

    @Column(name = "created",nullable = false)
    LocalDateTime created;

    @Column(name = "updated",nullable = false)
    LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    TaskListEntity taskList;
}
