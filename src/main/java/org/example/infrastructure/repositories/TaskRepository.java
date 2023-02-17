package org.example.infrastructure.repositories;
import org.example.domain.entities.TaskEntity;

import java.util.List;

public interface TaskRepository {

    List<TaskEntity> getAll();

    void post(TaskEntity taskEntity);
}
