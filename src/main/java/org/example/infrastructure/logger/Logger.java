package org.example.infrastructure.logger;

import org.example.domain.enums.Command;
import org.example.domain.models.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    public static void error(String message) {
        System.err.println("[" + ColoredString.RED.colorize("err") + "][" + formattedCurrentDate() + "] " + message);
    }

    public static void success(String message) {
        System.out.println("[" + ColoredString.GREEN.colorize("ok+") + "][" + formattedCurrentDate() + "] " + message);
    }

    private static String formattedCurrentDate() {
        return ColoredString.CYAN.colorize(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH"))
                        + "h"
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm,ss"))
        );
    }

    public static void printHelp() {
        System.out.println("Available commands:");
        printInfoFormat(Command.LIST);
        printInfoFormat(Command.ADD);
        printInfoFormat(Command.ADD_SUBTASK);
        printInfoFormat(Command.UPDATE);
        printInfoFormat(Command.REMOVE);
    }

    public static void printTask(Task task) {
        System.out.println(
                task.getDescription() + "   (" + ColoredString.YELLOW.colorize(task.getUuid().toString()) + ")"
                        + "\n    dueDate: " + task.getDueDate()
                        + "\n    state: " + task.getState()
                        + "\n"
        );
    }

    private static void printInfoFormat(Command command) {
        System.out.println("- " + format(command));
    }

    private static String format(Command command) {
        return switch (command) {
            case LIST -> "list";
            case ADD -> "add -c <content> [-d <dueDate>]";
            case ADD_SUBTASK -> "addSubtask (UUID) -c <content> [-d <dueDate>]";
            case UPDATE -> "update (UUID) [-c <content>|-d <dueDate>]";
            case REMOVE -> "remove (UUID)";
        };
    }
}
