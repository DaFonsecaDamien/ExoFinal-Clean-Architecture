package org.example.infrastructure.repositories;

import org.example.infrastructure.entities.TaskEntity;

import java.util.List;

public interface TaskRepository {

    List<TaskEntity> getAll();

    void post(List<TaskEntity> taskEntities);

    void delete(List<TaskEntity> taskEntities);

    void update(List<TaskEntity> taskEntities);
}
