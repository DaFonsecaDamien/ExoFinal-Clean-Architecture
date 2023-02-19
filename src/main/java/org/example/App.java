package org.example;

import org.example.domain.entities.Task;
import org.example.domain.enums.Command;
import org.example.domain.services.TaskService;
import org.example.infrastructure.parsers.ArgumentsParser;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Pair<Command, HashMap<String, Object>> arguments =  ArgumentsParser.parse(args);
        if (arguments == null) return;
        if (arguments.getValue0() == Command.LIST) {
            TaskService taskService = new TaskService();
            taskService.getTasks();
            List<Task> tasks = taskService.orderedByCreationDate();
            tasks.forEach(System.out::println);
        } else {
            if (arguments.getValue1() == null) {
                arguments.getValue0().printErrorFormat();
            }
        }

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
