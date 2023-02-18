package org.example.infrastructure.entities;

import org.example.domain.entities.Task;

public class TaskMapper {

    public Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getUuid(),
                taskEntity.getCreationDate(),
                taskEntity.getDueDate(),
                taskEntity.getCloseDate(),
                taskEntity.getDescription(),
                taskEntity.getState(),
                taskEntity.getSubTasks()
        );
    }
}
