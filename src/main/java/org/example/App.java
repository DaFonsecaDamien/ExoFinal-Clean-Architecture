package org.example;

import org.example.domain.entities.Task;
import org.example.domain.enums.TaskState;
import org.example.domain.services.TaskService;
import org.example.infrastructure.entities.TaskEntity;
import org.example.infrastructure.entities.TaskMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        TaskMapper taskMapper = new TaskMapper();
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getTasks();
        List<TaskEntity> taskEntities = new ArrayList<>();
        for (Task task : tasks) {
            TaskEntity taskEntity = taskMapper.toEntity(task);
            taskEntities.add(taskEntity);
        }

        Task task = taskService.addTask(LocalDateTime.now(), null, null, "CECI EST UN TEST", null);

        taskService.updateTaskStatus(task, TaskState.DONE);
//        tasks = taskService.orderByCreationDate();
//        for(Task task : tasks){
//            System.out.println(task.toString());
//        }


    }
}
