package org.example.domain.enums;

public enum Command {
    LIST,
    ADD,
    UPDATE,
    REMOVE;

    private String format() {
        return switch (this) {
            case LIST -> "list";
            case ADD -> "add -c <content> [-d <dueDate>]";
            case UPDATE -> "update (UUID) [-c <content>|-d <dueDate>]";
            case REMOVE -> "remove (UUID)";
        };
    }

    public void printErrorFormat() {
        System.err.println("Usage " + format());
    }

    public static void printHelpFormat() {
        System.out.println("Available commands:");
        Command.LIST.printInfoFormat();
        Command.ADD.printInfoFormat();
        Command.UPDATE.printInfoFormat();
        Command.REMOVE.printInfoFormat();
    }

    private void printInfoFormat() {
        System.out.println("- " + format());
    }
}
