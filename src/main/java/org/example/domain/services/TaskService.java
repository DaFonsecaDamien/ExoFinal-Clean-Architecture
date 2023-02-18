package org.example.domain.services;


import org.example.domain.entities.Task;
import org.example.domain.enums.TaskState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task addTask(UUID uuid, LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, String description, Task[] subTasks) {
        Task task = new Task(uuid, created, dueDate, closeDate, description, TaskState.TODO, subTasks);
        tasks.add(task);
        return task;
    }

    public Task updateTask(Task task, LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, Task[] subTasks) {
        task.setCreationDate(created);
        task.setDueDate(dueDate);
        task.setCloseDate(closeDate);
        task.setState(state);
        task.setSubTasks(subTasks);
        return task;
    }

    public Task updateTaskStatus(Task task, TaskState state) {
        task.setState(state);
        return task;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> orderByCreationDate() {
        tasks.sort((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()));
        return tasks;
    }
}
