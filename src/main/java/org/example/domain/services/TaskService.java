package org.example.domain.services;

import org.example.domain.entities.Task;
import org.example.domain.enums.TaskState;
import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.entities.TaskMapper;
import org.example.infrastructure.repositories.TaskFileRepository;
import org.example.infrastructure.repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService {

    TaskRepository taskRepository = new TaskFileRepository();
    TaskMapper taskMapper = new TaskMapper();
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        List<TaskEntity> taskEntities = taskRepository.getAll();
        for (TaskEntity taskEntity : taskEntities) {
            tasks.add(taskMapper.toDomain(taskEntity));
        }
        return tasks;
    }

    public Task addTask(LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, String description, Task[] subTasks) {
        Task newTask = new Task(UUID.randomUUID(), created, dueDate, closeDate, description, TaskState.TODO, subTasks);
        tasks.add(newTask);
        List<TaskEntity> taskEntities = toEntityList(tasks);
        taskRepository.post(taskEntities);
        return newTask;
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
        tasks.set(tasks.indexOf(task), task);
        List<TaskEntity> taskEntities = toEntityList(tasks);
        taskRepository.update(taskEntities);
        return task;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        taskRepository.delete(toEntityList(tasks));
    }

    public List<Task> orderByCreationDate() {
        tasks.sort((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()));
        return tasks;
    }

    private List<TaskEntity> toEntityList(List<Task> tasks){
        List<TaskEntity> taskEntities = new ArrayList<>();
        for (Task task : tasks) {
            TaskEntity taskEntity = taskMapper.toEntity(task);
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }
}
