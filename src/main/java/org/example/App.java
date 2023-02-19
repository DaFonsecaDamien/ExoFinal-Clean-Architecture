package org.example;

import org.example.infrastructure.TaskManager;
import org.example.infrastructure.parsers.ArgumentsParser;

public class App {
    public static void main(String[] args) {
        String initialCommand = String.join(" ", args);
        TaskManager.getInstance().execute(initialCommand, ArgumentsParser.parse(args));
    }
}