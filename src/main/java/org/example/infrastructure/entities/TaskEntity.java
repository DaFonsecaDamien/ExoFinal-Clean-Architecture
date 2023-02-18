package org.example.infrastructure.entities;

import org.example.domain.entities.Task;
import org.example.domain.enums.TaskState;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskEntity {

    private final UUID uuid;
    private final LocalDateTime creationDate;
    private final LocalDateTime dueDate;
    private final LocalDateTime closeDate;
    private final String description;
    private final Enum<TaskState> state;
    private final Task[] subTasks;

    public TaskEntity(UUID uuid, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, String description, Enum<TaskState> state, Task[] subTasks) {
        this.uuid = uuid;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
        this.state = state;
        this.subTasks = subTasks;
    }

    public UUID getUuid(){
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

    public Task[] getSubTasks() {
        return subTasks;
    }

}
