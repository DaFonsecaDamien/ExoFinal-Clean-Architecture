package org.example.domain.enums;

public enum TaskState {
    TODO(0),
    PENDING(1),
    DONE(2),
    CANCELLED(3),
    CLOSED(4);

    private int value;

    TaskState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getStringFromValue(int value) {
        for (TaskState s : TaskState.values()) {
            if (s.getValue() == value) {
                return s.name().toLowerCase();
            }
        }
        return null;
    }
}
