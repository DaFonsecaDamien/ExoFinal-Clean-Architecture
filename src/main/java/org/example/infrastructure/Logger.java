package org.example.infrastructure;

import org.example.domain.enums.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Logger {
    public static void error(String message) {
        System.err.println("[err][" + formattedCurrentDate() + "] " + message);
    }

    public static void success(String message) {
        System.out.println("[ok+][" + formattedCurrentDate() + "] " + message);
    }

    private static String formattedCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH"))
                + "h"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm,ss"));
    }

    public static void printHelp() {
        System.out.println("Available commands:");
        printInfoFormat(Command.LIST);
        printInfoFormat(Command.ADD);
        printInfoFormat(Command.UPDATE);
        printInfoFormat(Command.REMOVE);
    }

    private static void printInfoFormat(Command command) {
        System.out.println("- " + format(command));
    }

    private static String format(Command command) {
        return switch (command) {
            case LIST -> "list";
            case ADD -> "add -c <content> [-d <dueDate>]";
            case UPDATE -> "update (UUID) [-c <content>|-d <dueDate>]";
            case REMOVE -> "remove (UUID)";
        };
    }
}
