package org.example.infrastructure.repositories;

import org.example.infrastructure.entities.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {

    List<TaskEntity> getAll();

    TaskEntity getOne(UUID uuid);

    void post(List<TaskEntity> taskEntities);

    void delete(List<TaskEntity> taskEntities);

    void update(List<TaskEntity> taskEntities);
}
