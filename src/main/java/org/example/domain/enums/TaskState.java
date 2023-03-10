package org.example.domain.enums;

public enum TaskState {
    TODO(0),
    PENDING(1),
    DONE(2),
    CANCELLED(3),
    CLOSED(4);

    private final int value;

    TaskState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
