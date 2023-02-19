package org.example;

import org.example.domain.entities.Task;
import org.example.domain.enums.Command;
import org.example.domain.enums.TaskState;
import org.example.domain.services.TaskService;
import org.example.infrastructure.Logger;
import org.example.infrastructure.TaskManager;
import org.example.infrastructure.parsers.ArgumentsParser;
import org.example.infrastructure.parsers.TaskJsonParser;
import org.javatuples.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String initialCommand = String.join(" ", args);
        TaskManager.getInstance().execute(initialCommand, ArgumentsParser.parse(args));

//        TaskService taskService = new TaskService();
//        taskService.getTasks();
//        Task task = taskService.addSubTask(UUID.fromString("24f48fb8-1b3a-488c-9442-738d6c8f6a84"), LocalDateTime.now(), null, null, "CECI EST UN TEST", null);

//        TaskService taskService = new TaskService();
//        taskService.getTasks();
//        //Task task = taskService.addSubTask(UUID.fromString("24f48fb8-1b3a-488c-9442-738d6c8f6a84"), LocalDateTime.now(), null, null, "CECI EST UN TEST", null);
//
//        taskService.updateTask(UUID.fromString("24f48fb8-1b3a-488c-9442-738d6c8f6a84"), new Task(null, LocalDateTime.now(), null, null, "CECI EST UN TEST UPDATED", TaskState.DONE, null));
        //Task task = taskService.addTask(LocalDateTime.now(), null, null, "CECI EST UN TEST", null);
//        System.out.println(arguments);
//
//        TaskMapper taskMapper = new TaskMapper();
//        TaskService taskService = new TaskService();
//        List<Task> tasks = taskService.getTasks();
//        List<TaskEntity> taskEntities = new ArrayList<>();
//        tasks.forEach(task -> taskEntities.add(taskMapper.toEntity(task)));
//
//        Task task = taskService.addTask(LocalDateTime.now(), null, null, "CECI EST UN TEST", null);
//
//        taskService.updateTaskStatus(task, TaskState.DONE);
//        tasks = taskService.orderedByCreationDate();
//        tasks.forEach(System.out::println);
    }
}
