package org.example.infrastructure.entities;

import org.example.domain.entities.Task;

import java.util.Arrays;
import java.util.Collections;

public class TaskMapper {

    public Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getUuid(),
                taskEntity.getCreationDate(),
                taskEntity.getDueDate(),
                taskEntity.getCloseDate(),
                taskEntity.getDescription(),
                taskEntity.getState(),
                taskEntity.getSubTasks() != null ? Arrays.stream(taskEntity.getSubTasks()).map(this::toDomain).toArray(Task[]::new) : null
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
                task.getSubTasks() != null ? Arrays.stream(task.getSubTasks()).map(this::toEntity).toArray(TaskEntity[]::new) : null
        );
    }
}
