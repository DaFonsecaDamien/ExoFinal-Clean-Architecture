package org.example.domain.entities;

import org.example.domain.enums.TaskState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    private UUID uuid;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private LocalDateTime closeDate;
    private String description;
    private Enum<TaskState> state;
    private List<Task> subTasks;

    public Task(UUID uuid, LocalDateTime creationDate, LocalDateTime dueDate, LocalDateTime closeDate, String description, Enum<TaskState> state, List<Task> subTasks) {
        this.uuid = uuid;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.closeDate = closeDate;
        this.description = description;
        this.state = state;
        this.subTasks = subTasks;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(Task subTask) {
        if (this.subTasks == null) {
            List<Task> subTasksList = new ArrayList<>();
            subTasksList.add(subTask);
            this.subTasks = subTasksList;
        } else {
            this.subTasks.add(subTask);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid + "\n" +
                "creationDate=" + creationDate + "\n" +
                "dueDate=" + dueDate + "\n" +
                "closeDate=" + closeDate + "\n" +
                "description='" + description + '\'' + "\n" +
                "state=" + state + "\n" +
                "subTasks=" + subTasks + "\n" +
                '}';
    }
}
