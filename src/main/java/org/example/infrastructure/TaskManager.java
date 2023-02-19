package org.example.infrastructure;

import org.example.domain.enums.Command;
import org.example.domain.enums.TaskState;
import org.example.domain.models.Task;
import org.example.domain.services.TaskService;
import org.example.infrastructure.logger.Logger;
import org.javatuples.Pair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class TaskManager {

    private static TaskManager sharedInstance;
    TaskService taskService;

    private TaskManager() {
        taskService = new TaskService();
    }

    public static TaskManager getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TaskManager();
        }
        return sharedInstance;
    }

    public void execute(String initialCommand, Pair<Command, Pair<HashMap<String, Object>, String>> arguments) {
        if (arguments == null) {
            Logger.printHelp();
            return;
        }

        Command command = arguments.getValue0();
        Pair<HashMap<String, Object>, String> result = arguments.getValue1();
        HashMap<String, Object> params = null;
        String error = null;

        if (result != null) {
            params = result.getValue0();
            error = result.getValue1();
        }

        if (error != null) {
            Logger.error(initialCommand + " : " + error);
            return;
        }

        boolean commandSuccess = true;

        switch (command) {
            case LIST -> executeListCommand();
            case ADD -> {
                if (params != null) executeAddCommand(params);
            }
            case ADD_SUBTASK -> {
                if (params != null) executeAddSubtaskCommand(params);
            }
            case UPDATE -> {
                if (params != null) {
                    if (!executeUpdateCommand(params)) {
                        commandSuccess = false;
                        Logger.error(initialCommand + " : Task not found");
                    }
                }
            }
            case REMOVE -> {
                if (params != null) executeRemoveCommand(params);
            }
        }

        if (commandSuccess) Logger.success(initialCommand);
    }

    private void executeListCommand() {
        taskService.getTasks();
        taskService.orderedByCreationDate().forEach(Logger::printTask);
    }

    private void executeAddCommand(HashMap<String, Object> params) {
        taskService.getTasks();
        taskService.addTask(
                LocalDateTime.now(),
                (LocalDateTime) params.getOrDefault("dueDate", null),
                null,
                (String) params.getOrDefault("content", ""),
                null
        );
    }

    private void executeAddSubtaskCommand(HashMap<String, Object> params) {
        taskService.getTasks();
        taskService.addSubTask(
                (UUID) params.getOrDefault("uuid", null),
                LocalDateTime.now(),
                (LocalDateTime) params.getOrDefault("dueDate", null),
                null,
                (String) params.getOrDefault("content", ""),
                null
        );
    }

    private boolean executeUpdateCommand(HashMap<String, Object> params) {
        taskService.getTasks();
        Task newTask = new Task(
                null,
                null,
                (LocalDateTime) params.getOrDefault("dueDate", null),
                null,
                (String) params.getOrDefault("content", ""),
                (TaskState) params.getOrDefault("status", null),
                null
        );
        return taskService.updateTask(
                (UUID) params.getOrDefault("uuid", null),
                newTask
        );
    }

    private void executeRemoveCommand(HashMap<String, Object> params) {
        taskService.getTasks();
        taskService.deleteTask(
                (UUID) params.getOrDefault("uuid", null)
        );
    }
}
