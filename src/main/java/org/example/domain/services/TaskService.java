package org.example.domain.services;


import org.example.domain.entities.TaskEntity;
import org.example.domain.enums.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskService {
    private final List<TaskEntity> tasks = new ArrayList<>();

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity addTask(UUID uuid, Date created, Date dueDate, Date closeDate, String description, TaskEntity[] subTasks) {
        TaskEntity task = new TaskEntity(uuid, created, dueDate, closeDate, description, TaskState.TODO, subTasks);
        tasks.add(task);
        return task;
    }

    public TaskEntity updateTask(TaskEntity task, Date created, Date dueDate, Date closeDate, TaskState state, TaskEntity[] subTasks) {
        task.setCreationDate(created);
        task.setDueDate(dueDate);
        task.setCloseDate(closeDate);
        task.setState(state);
        task.setSubTasks(subTasks);
        return task;
    }

    public TaskEntity updateTaskStatus(TaskEntity task, TaskState state) {
        task.setState(state);
        return task;
    }

    public void deleteTask(TaskEntity task) {
        tasks.remove(task);
    }

    public List<TaskEntity> orderByCreationDate() {
        tasks.sort((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()));
        return tasks;
    }
}
