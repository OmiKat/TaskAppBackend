package com.omi.TaskApp.Repo;

import com.omi.TaskApp.Model.Entity.TaskListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepo extends JpaRepository<TaskListEntity, UUID> {

}
