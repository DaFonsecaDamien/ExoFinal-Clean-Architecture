package org.example.infrastructure.entities;

import org.example.domain.entities.Task;

public class TaskMapper {

    public Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getUUID(),
                taskEntity.getCreationDate(),
                taskEntity.getDueDate(),
                taskEntity.getCloseDate(),
                taskEntity.getDescription(),
                taskEntity.getState(),
                taskEntity.getSubTasks() != null ? taskEntity.getSubTasks().stream().map(this::toDomain).toList() : null
        );
    }

    public TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getUuid(),
                task.getCreationDate(),
                task.getDueDate(),
                task.getCloseDate(),
                task.getDescription(),
                task.getState(),
                task.getSubTasks() != null ? task.getSubTasks().stream().map(this::toEntity).toList() : null
        );
    }
}
