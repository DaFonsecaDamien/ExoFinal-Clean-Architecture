package org.example.domain.entities;



import org.example.domain.enums.TaskState;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private UUID uuid;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private LocalDateTime closeDate;
    private String description;
    private Enum<TaskState> state;
    private Task[] subTasks;

    public Task(UUID uuid, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, String description, Enum<TaskState> state, Task[] subTasks) {
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

    public void setUuid(){
        this.uuid = uuid;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enum<TaskState> getState() {
        return state;
    }

    public void setState(Enum<TaskState> state) {
        this.state = state;
    }

    public Task[] getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Task[] subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public String toString() {
        return String.format("Task: %s,\n State: %s,\n Created: %s,\n Due: %s,\n Closed: %s\n",
                description,
                state,
                creationDate.toString(),
                dueDate == null ? "N/A" : dueDate.toString(),
                closeDate == null ? "N/A" : closeDate.toString());
    }
}
