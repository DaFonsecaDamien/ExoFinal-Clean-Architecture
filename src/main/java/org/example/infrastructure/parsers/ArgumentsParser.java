package org.example.infrastructure.parsers;

import org.example.domain.enums.Command;
import org.example.domain.enums.TaskState;
import org.example.infrastructure.Logger;
import org.javatuples.Pair;

import java.time.LocalDateTime;
import java.util.*;

public class ArgumentsParser {
    public static Pair<Command, Pair<HashMap<String, Object>, String>> parse(String[] args) {
        if (args.length == 0) return null;
        return switch (args[0]) {
            case "list" -> new Pair<>(Command.LIST, null);
            case "add" -> new Pair<>(Command.ADD, parseAdd(args));
            case "update" -> new Pair<>(Command.UPDATE, parseUpdate(args));
            case "remove" -> new Pair<>(Command.REMOVE, parseRemove(args));
            default -> null;
        };
    }

    private static String getStringForArgTag(String tag, List<String> args) {
        return args.get(args.indexOf(tag) + 1);
    }

    private static boolean argsContainsContent(List<String> args) {
        return args.contains("-c") && args.size() - 1 > args.indexOf("-c");
    }

    private static boolean argsContainsDueDate(List<String> args) {
        return args.contains("-d") && args.size() - 1 > args.indexOf("-d");
    }

    private static boolean argsContainsStatus(List<String> args) {
        return args.contains("-s") && args.size() - 1 > args.indexOf("-s");
    }

    private static LocalDateTime getDueDateFromList(List<String> args) throws Exception {
        try {
            return LocalDateTime.parse(getStringForArgTag("-d", args));
        } catch (Exception e) {
            throw new Exception("Invalid date format");
        }
    }

    private static TaskState getStatusFromList(List<String> args) throws Exception {
        try {
            return TaskState.valueOf(getStringForArgTag("-s", args).toUpperCase());
        } catch (Exception e) {
            throw new Exception("Invalid status");
        }
    }

    private static Pair<HashMap<String, Object>, String> parseAdd(String[] args) {
        try {
            if (args.length < 2) throw new Exception("Invalid number of arguments");
            List<String> argsList = Arrays.asList(args).subList(1, args.length);
            HashMap<String, Object> map = new HashMap<>();

            if (!(argsList.size() % 2 == 0)) throw new Exception("Invalid number of arguments");

            if (!argsContainsContent(argsList)) return null;
            map.put("content", getStringForArgTag("-c", argsList));

            if (argsContainsDueDate(argsList)) map.put("dueDate", getDueDateFromList(argsList));

            return new Pair<>(map, null);
        } catch (Exception e) {
            return new Pair<>(null, e.getMessage());
        }
    }

    private static Pair<HashMap<String, Object>, String> parseUpdate(String[] args) {
        try {
            if (args.length < 3) throw new Exception("Invalid number of arguments");
            List<String> argsList = Arrays.asList(args).subList(1, args.length);
            HashMap<String, Object> map = new HashMap<>();

            UUID uuid = UUID.fromString(argsList.get(0));
            argsList = argsList.subList(1, argsList.size());
            map.put("uuid", uuid);

            if (!(argsList.size() % 2 == 0)) throw new Exception("Invalid number of arguments");

            if (argsContainsContent(argsList)) map.put("content", getStringForArgTag("-c", argsList));
            if (argsContainsDueDate(argsList)) map.put("dueDate", getDueDateFromList(argsList));
            if (argsContainsStatus(argsList)) map.put("status", getStatusFromList(argsList));

            return new Pair<>(map, null);
        } catch (Exception e) {
            return new Pair<>(null, e.getMessage());
        }
    }

    private static Pair<HashMap<String, Object>, String> parseRemove(String[] args) {
        try {
            if (args.length < 2) throw new Exception("Invalid number of arguments");

            HashMap<String, Object> map = new HashMap<>();
            map.put("uuid", UUID.fromString(args[1]));

            return new Pair<>(map, null);
        } catch (Exception e) {
            return new Pair<>(null, e.getMessage());
        }
    }
}

