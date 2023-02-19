package org.example.infrastructure.entities;

import org.example.domain.enums.TaskState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TaskEntity {
    private final UUID uuid;
    private final LocalDateTime creationDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime closeDate;
    private final String description;
    private final Enum<TaskState> state;
    private final List<TaskEntity> subTasks;

    public TaskEntity(UUID uuid, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, String description, Enum<TaskState> state, List<TaskEntity> subTasks) {
        this.uuid = uuid;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
        this.state = state;
        this.subTasks = subTasks;
    }

    public UUID getUUID() {
        return uuid;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public String getDescription() {
        return description;
    }

    public Enum<TaskState> getState() {
        return state;
    }

    public List<TaskEntity> getSubTasks() {
        return subTasks;
    }

}
