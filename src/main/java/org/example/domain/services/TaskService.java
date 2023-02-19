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

    private final List<Task> tasks = new ArrayList<>();
    TaskRepository taskRepository = new TaskFileRepository();
    TaskMapper taskMapper = new TaskMapper();

    public List<Task> getTasks() {
        List<TaskEntity> taskEntities = taskRepository.getAll();
        taskEntities.forEach(taskEntity -> tasks.add(taskMapper.toDomain(taskEntity)));
        return tasks;
    }

    public Task getTask(UUID uuid) {
        return taskMapper.toDomain(taskRepository.getOne(uuid));
    }

    public Task addTask(LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, String description, List<Task> subTasks) {
        Task newTask = new Task(UUID.randomUUID(), created, dueDate, closeDate, description, TaskState.TODO, subTasks);
        tasks.add(newTask);
        List<TaskEntity> taskEntities = toEntityList(tasks);
        taskRepository.post(taskEntities);
        return newTask;
    }

    public Task addSubTask(UUID uuid, LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, String description, List<Task> subTasks) {
        Task newTask = new Task(UUID.randomUUID(), created, dueDate, closeDate, description, TaskState.TODO, subTasks);
        List<Task> newTaskList = addSubTaskToList(tasks, newTask, uuid);
        List<TaskEntity> taskEntities = toEntityList(newTaskList);
        taskRepository.post(taskEntities);
        return newTask;
    }

    private List<Task> addSubTaskToList(List<Task> tasks, Task newTask, UUID uuid) {
        if(tasks == null || tasks.size() == 0) {
            return null;
        }
        tasks.forEach(task -> {
            if (task.getUuid().equals(uuid)) {
                task.addSubTask(newTask);
            } else {
                task.setSubTasks(addSubTaskToList(task.getSubTasks(), newTask, uuid));
            }
        });
        return tasks;
    }
    
    public Task updateTask(Task task, LocalDateTime created, LocalDateTime dueDate, LocalDateTime closeDate, TaskState state, List<Task> subTasks) {
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

    public List<Task> orderedByCreationDate() {
        List<Task> tasks = getTasks();
        tasks.sort((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()));
        return tasks;
    }

    private List<TaskEntity> toEntityList(List<Task> tasks) {
        List<TaskEntity> taskEntities = new ArrayList<>();
        tasks.forEach(task -> taskEntities.add(taskMapper.toEntity(task)));
        return taskEntities;
    }
}
