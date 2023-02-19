package org.example.infrastructure;

import org.example.domain.entities.Task;
import org.example.domain.enums.Command;
import org.example.domain.services.TaskService;
import org.javatuples.Pair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    TaskService taskService;

    // private field that refers to the object
    private static TaskManager sharedInstance;

    private TaskManager() {
        taskService = new TaskService();
    }

    public static TaskManager getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TaskManager();
        }
        return sharedInstance;
    }
    public void execute (String initialCommand, Pair<Command, Pair<HashMap<String, Object>, String>> arguments) {
        if (arguments == null) {
            Logger.printHelp();
            return;
        }

        Command command = arguments.getValue0();
        Pair<HashMap<String, Object>, String> result = arguments.getValue1();
        HashMap<String, Object> params = result.getValue0();
        String error = result.getValue1();

        if (error != null) {
            Logger.error(initialCommand + " : " + error);
            return;
        }

        switch (command) {
            case LIST -> executeListCommand();
            case ADD -> executeAddCommand(params);
            case UPDATE -> executeUpdateCommand(params);
            case REMOVE -> executeRemoveCommand(params);
        }

        Logger.success(initialCommand);
    }

    private void executeListCommand() {
        taskService.getTasks();
        taskService.orderedByCreationDate().forEach(System.out::println);
    }

    private void executeAddCommand(HashMap<String, Object> params) {
        taskService.getTasks();
        taskService.addTask(
                LocalDateTime.now(),
                (LocalDateTime) params.getOrDefault("dueDate", null),
                null,
                (String)params.getOrDefault("content", ""),
                null
        );
    }

    private void executeUpdateCommand(HashMap<String, Object> params) {
        taskService.getTasks();
    }

    private void executeRemoveCommand(HashMap<String, Object> params) {
        System.out.println(params);
    }
}
