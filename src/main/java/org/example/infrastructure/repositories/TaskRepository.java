package org.example.infrastructure.repositories;
import org.example.infrastructure.entities.TaskEntity;

import java.util.List;

public interface TaskRepository {

    List<TaskEntity> getAll();

    void post(TaskEntity taskEntity);
}
