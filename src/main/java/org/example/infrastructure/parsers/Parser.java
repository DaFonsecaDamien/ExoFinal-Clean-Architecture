package org.example.infrastructure.parsers;

import org.example.infrastructure.entities.TaskEntity;

import java.util.List;

public interface Parser {

    <T> T parse();

    void writeToFile(List<TaskEntity> tasks);
}
